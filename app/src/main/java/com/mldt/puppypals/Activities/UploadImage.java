package com.mldt.puppypals.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dog;
import com.mldt.puppypals.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadImage extends AppCompatActivity {
    public static final String TAG = "ImageUpload";
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String s3ImageKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        setupAddImageButton();
    }

    private void setupAddImageButton(){
       findViewById(R.id.saveUploadImage).setOnClickListener(view ->{
           saveDog(s3ImageKey);
       });
    }

    private void saveDog(String s3Key){
        Dog dogToSave = Dog.builder()
                .dogName("dogName")
                .dogBio("dogBio")
                .dogBreed("dogBreed")
                .profileImageUrl("profileImageUrl")
                .build();

        Amplify.API.mutate(
                ModelMutation.create(dogToSave),
                success -> Log.i(TAG, "Successfully created new dog with s3ImageKey:" + s3Key),
                failure -> Log.i(TAG, "Failed to create a new Dog with message: " + failure.getMessage())
        );

    }
    private void launchImageSelectionIntent(){
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageFilePickingIntent.setType("/");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});

        activityResultLauncher.launch(imageFilePickingIntent);
    }

    private ActivityResultLauncher<Intent> getImagePickingActivityResultsLauncher(){
        ActivityResultLauncher<Intent> imagePickingActivityResultsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Uri pickedImageFileUri = result.getData().getData();
                        try{
                            InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                            String pickedImageFilename = getFileNameFromUri(pickedImageFileUri);
                           uploadInputStreamTos3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
                        } catch (FileNotFoundException fnfe){
                            Log.e(TAG, "Could not get file from file picker!" + fnfe.getMessage(),fnfe);
                        }
                    }
                }
        );
        return imagePickingActivityResultsLauncher;
    }
    private void uploadInputStreamTos3(InputStream pickedImageInputStream, String pickedImageFileName, Uri pickedImageFileUri){
     Amplify.Storage.uploadInputStream(
             pickedImageFileName,
             pickedImageInputStream,
             success -> {
                 Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
                 s3ImageKey = success.getKey();
                 ImageView uploadImageView = findViewById(R.id.upLoadImageView);
                 InputStream pickedImageInputStreamCopy = null;
                 try {
                     pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);

                 }catch (FileNotFoundException fnfe){
                     Log.e(TAG, "Could not get file stream from URI " + fnfe.getMessage(), fnfe);
                 } uploadImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
             },
             failure -> Log.e(TAG,"Failure in uploading file to s3 with filename: " + pickedImageFileName + "with error: " +failure.getMessage())

     );
    }
    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

}
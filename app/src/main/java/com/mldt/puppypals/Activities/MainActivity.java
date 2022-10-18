package com.mldt.puppypals.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.mldt.puppypals.R;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    public AuthUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        currentUser = Amplify.Auth.getCurrentUser();
    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        if(currentUser == null) {
            inflater.inflate(R.menu.dropdown_logged_out, popup.getMenu());
        } else {
            inflater.inflate(R.menu.dropdown_logged_in, popup.getMenu());
        }
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainActivityDropDownSignUp:
                goToSignUpActivity();
                return true;
            case R.id.mainActivityDropDownLogin:
                goToLoginActivity();
                return true;
            case R.id.mainActivityDropDownSettings:
                goToProfileActivity();
                return true;
            case R.id.mainActivityDropDownAbout:
                goToAboutActivity();
                return true;
            case R.id.mainActivityDropDownLogOut:
                Amplify.Auth.signOut(
                        () -> Log.i(TAG, "Signed out successfully"),
                        error -> Log.e(TAG, error.toString())
                );
                Intent goToLoginActivity = new Intent(MainActivity.this, LogIn.class);
                startActivity(goToLoginActivity);
                return true;
            default:
        }
        return false;
    }

    public void goToSignUpActivity(){
        Intent goToSignUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(goToSignUp);
    }
    public void goToLoginActivity(){
        Intent goToLogIn = new Intent(MainActivity.this, LogIn.class);
        startActivity(goToLogIn);
    }
    public void goToAboutActivity(){
        Intent goToAbout = new Intent(MainActivity.this, AboutPage.class);
        startActivity(goToAbout);
    }
    public void goToProfileActivity(){
        Intent goToProfile = new Intent(MainActivity.this, OwnProfileSettings.class);
        startActivity(goToProfile);
    }
}
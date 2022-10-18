package com.mldt.puppypals;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class PuppyPalsAmplifyApplication extends Application {
    public static final String TAG = "PuppyPalsApplication";

    @Override
    public void onCreate(){
        super.onCreate();
        try{

            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
        } catch (AmplifyException ae){
            Log.e(TAG, "Error initializing Amplify: " + ae.getMessage(), ae);
        }
    }

}

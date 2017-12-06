package cl.cutiko.pokemongo.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import cl.cutiko.pokemongo.R;
import cl.cutiko.pokemongo.views.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 343;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO add Firebase to your project
        //The best way to add Firebase is using the assitant go to Tools/Firebase and follow that wizard

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            goToMain();
        } else {
            signUp();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode) {
            if (RESULT_OK == resultCode) {
                goToMain();
            } else {
                signUp();
            }
        }
    }

    private void signUp(){
        //Dont forget to configure the email login in the firebase web console
        //TODO check docs https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md
    }

    private void goToMain(){
        startActivity(new Intent(this, MainActivity.class));
    }
}

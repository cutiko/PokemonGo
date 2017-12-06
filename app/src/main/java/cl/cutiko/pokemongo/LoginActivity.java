package cl.cutiko.pokemongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 343;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().build(),
                RC_SIGN_IN
        );
    }

    private void goToMain(){
        startActivity(new Intent(this, MainActivity.class));
    }
}

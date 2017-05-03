package swevoq.ebread.com.Chat.View.Utility;

import swevoq.ebread.com.Chat.Presenter.Utility.RegistrationPresenter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ProgressBar;
import swevoq.ebread.com.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;

public class RegistrationActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private RegistrationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        presenter = new RegistrationPresenter();


        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               startActivity(new Intent(RegistrationActivity.this,PasswordRecoveryActivity.class));
           }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RegistrationActivity.this,AuthActivity.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                System.out.print(presenter.checkData(email,password));

                if(!TextUtils.isEmpty(presenter.checkData(email,password))){
                    Toast.makeText(getApplicationContext(),presenter.checkData(email,password), Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                presenter.signUp(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Registrazione fallita.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            presenter.saveUser();
                            startActivity(new Intent(RegistrationActivity.this, ChatListActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RegistrationActivity.this, AuthActivity.class);
        RegistrationActivity.this.startActivity(intent);
        finish();
    }
}

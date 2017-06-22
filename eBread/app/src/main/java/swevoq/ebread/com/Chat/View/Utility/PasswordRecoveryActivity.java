package swevoq.ebread.com.Chat.View.Utility;

import swevoq.ebread.com.Chat.Presenter.Utility.PasswordRecoveryPresenter;
import swevoq.ebread.com.Chat.View.Settings.VoiceSettingsActivity;
import swevoq.ebread.com.R;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class PasswordRecoveryActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset;
    private ProgressBar progressBar;
    private PasswordRecoveryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        presenter = new PasswordRecoveryPresenter();

        inputEmail = (EditText) findViewById(R.id.email);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if(!TextUtils.isEmpty(presenter.checkData(email))){
                    Toast.makeText(getApplicationContext(),presenter.checkData(email), Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                presenter.passwordRecovery(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PasswordRecoveryActivity.this, "E' stata inviata una mail con le istruzione per cambiare password!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PasswordRecoveryActivity.this,AuthActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(PasswordRecoveryActivity.this, "Errore. Controlla la mail inserita!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PasswordRecoveryActivity.this, AuthActivity.class);
        PasswordRecoveryActivity.this.startActivity(intent);
        finish();
    }
}

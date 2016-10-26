package gersondeveloper.com.br.challengev2.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import gersondeveloper.com.br.challengev2.Connection.RestClient;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    public static final String TAG = CadastroActivity.class.getName();

    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText confirmPassword;
    private EditText phone;
    private EditText email;
    private Button btnRegistrar;
    private View focusView;
    private boolean cancel;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        root = (RelativeLayout) findViewById(R.id.activity_cadastro);

        initializeComponents();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()))
                {
                    username.setError(getString(R.string.error_field_required));
                    focusView = username;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(firstName.getText().toString()))
                {
                    firstName.setError(getString(R.string.error_field_required));
                    focusView = firstName;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(lastName.getText().toString()))
                {
                    lastName.setError(getString(R.string.error_field_required));
                    focusView = lastName;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError(getString(R.string.error_field_required));
                    focusView = email;
                    cancel = true;
                }
                else if(!ChallengeUtil.emailValidator(email.getText().toString()))
                {
                    email.setError(getString(R.string.error_invalid_email));
                    focusView = email;
                    cancel = true;
                }
                else if(!password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    email.setError(getString(R.string.confirmacao_email));
                    focusView = email;
                    cancel = true;

                }
                else if(TextUtils.isEmpty(phone.getText().toString()))
                {
                    phone.setError(getString(R.string.error_field_required));
                    focusView = phone;
                    cancel = true;
                }
                if(cancel)
                {
                    focusView.requestFocus();
                }
                else
                {
                    User user = new User();
                    user.setUsername(username.getText().toString());
                    user.setFirstName(firstName.getText().toString());
                    user.setLastName(lastName.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setPhone(phone.getText().toString());

                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    progressBar.setVisibility(View.VISIBLE);
                    if(ChallengeUtil.isNetworkAvailable(getApplicationContext()))
                    {
                        RestClient.getInstance().registerUser(user, registerCallback);
                    }
                    else
                    {
                        Snackbar.make(root, R.string.sem_conexao, Snackbar.LENGTH_LONG).setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                    }



                }
            }
        });

    }


    private Callback<User> registerCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            Log.d(TAG,"registerCallback response:" + response.body().toString());
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            Log.d(TAG,"registerCallback error: " + t.getMessage().toString());
        }
    };


    private void initializeComponents()
    {
        this.progressBar = (ProgressBar) findViewById(R.id.cadastro_progress_bar);
        this.username =(EditText) findViewById(R.id.editTextUsername);
        this.firstName = (EditText) findViewById(R.id.editTextFirstName);
        this.lastName = (EditText) findViewById(R.id.editTextLastName);
        this.password = (EditText) findViewById(R.id.editTextPassword);
        this.confirmPassword = (EditText) findViewById(R.id.editTextPasswordConfirm);
        this.phone = (EditText) findViewById(R.id.editTextPhone);
        this.email = (EditText) findViewById(R.id.editTextEmail);
        this.btnRegistrar = (Button) findViewById(R.id.buttonSalvar);
        progressBar = (ProgressBar) findViewById(R.id.cadastro_progress_bar);
    }
}

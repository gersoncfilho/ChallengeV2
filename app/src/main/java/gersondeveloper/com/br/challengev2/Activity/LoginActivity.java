package gersondeveloper.com.br.challengev2.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import gersondeveloper.com.br.challengev2.Connection.RestClient;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

    private AutoCompleteTextView usernameEditText;
    private AutoCompleteTextView passwordEditText;
    private Button loginButton, registerButton;
    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initializeComponents();

        //Logout da aplicação, intent vindo da MainActivity
        if(getIntent().getBooleanExtra("logout",false))
        {
            finish();
        }

        //Verifica se usuário já existe, se existir vai direto para a tela principal, caso contrário é direcionado a página de login
        if(ChallengeUtil.isUserResgistered(LoginActivity.this))
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
        else
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptLogin();
                }
            });
        }
    }

    private void attemptLogin()
    {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(username))
        {
            usernameEditText.setError(getString(R.string.error_field_required));
            focusView = usernameEditText;
            cancel = true;
        }

        else if(TextUtils.isEmpty(password))
        {
            passwordEditText.setError(getString(R.string.error_field_required));
            focusView = passwordEditText;
            cancel = true;
        }

        if(cancel)
        {
            focusView.requestFocus();
        }
        else
        {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            progressbar.setVisibility(View.VISIBLE);

            Map<String,String> data = new HashMap<>();
            data.put("username", username);
            data.put("password", password);

            RestClient.getInstance().getUserLogin(data, loginCallback);
        }

    }

    public void initializeComponents()
    {
        usernameEditText = (AutoCompleteTextView) findViewById(R.id.login_edit_text_username);
        passwordEditText = (AutoCompleteTextView) findViewById(R.id.login_edit_text_password);
        loginButton = (Button) findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_button);
        progressbar = (ProgressBar) findViewById(R.id.login_progress);
    }

    private Callback<User> loginCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            //login succeeded logic
            int statusCode = response.code();
            User user = response.body();
            //status code 200 = success operation
            //open mainactivity passing user
            if(statusCode == 200)
            {
                Log.d(TAG,"Login realizado com sucesso");
               progressbar.setVisibility(View.GONE);
                ChallengeUtil.saveUser(getApplicationContext(), user);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
            //status code 400 = invalid username/password suplied
            //launch snackbar with invalid username/password message
            else if(statusCode == 404)
            {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, R.string.usuario_invalido, Toast.LENGTH_LONG).show();

            }
            else
            {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, R.string.falha_internet, Toast.LENGTH_LONG).show();
                return;
            }

        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            //login failed logic
            Log.d(TAG, "Login mal sucedido" + t.getMessage());
        }
    };


}

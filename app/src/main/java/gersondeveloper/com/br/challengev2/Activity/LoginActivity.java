package gersondeveloper.com.br.challengev2.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gersondeveloper.com.br.challengev2.Connection.RestClient;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();
    private Button loginButton, registerButton;

    View root;

    @BindView(R.id.login_edit_text_username)
    AutoCompleteTextView usernameEditText;

    @BindView(R.id.login_edit_text_password)
    AutoCompleteTextView passwordEditText;

    @BindView(R.id.login_progress)
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        root = findViewById(R.id.activity_login_new);

        //Verifica se usuário já existe, se existir vai direto para a tela principal, caso contrário é direcionado a página de login
        if(ChallengeUtil.isUserResgistered(LoginActivity.this))
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    @OnClick(R.id.login_button)
    public void login()
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);

        if(ChallengeUtil.isNetworkAvailable(getApplicationContext()))
        {
            attemptLogin();
        }
        else
        {
            Snackbar.make(root, R.string.sem_conexao, Snackbar.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.register_button)
    public void register(View view)
    {
        getWindow().setExitTransition(new Explode());
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
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


            progressbar.setVisibility(View.VISIBLE);

            Map<String,String> data = new HashMap<>();
            data.put("username", username);
            data.put("password", password);

            RestClient.getInstance().getUserLogin(data, loginCallback);


        }

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

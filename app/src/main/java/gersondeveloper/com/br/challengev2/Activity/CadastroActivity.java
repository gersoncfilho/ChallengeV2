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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gersondeveloper.com.br.challengev2.Connection.RestClient;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;
import io.realm.Realm;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    public static final String TAG = CadastroActivity.class.getName();

    private View focusView;
    private boolean cancel;
    private Realm realm;
    private User user;

    @BindView(R.id.cadastro_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.editTextUsername)
    EditText username;

    @BindView(R.id.editTextFirstName)
    EditText firstName;

    @BindView(R.id.editTextLastName)
    EditText lastName;

    @BindView(R.id.editTextPassword)
    EditText password;

    @BindView(R.id.editTextPasswordConfirm)
    EditText confirmPassword;

    @BindView(R.id.editTextPhone)
    EditText phone;

    @BindView(R.id.editTextEmail)
    EditText email;

    @BindView(R.id.activity_cadastro)
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();


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

    @OnClick(R.id.buttonSalvar)
    public void registrar()
    {
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
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    user = realm.createObject(User.class);
                    user.setUsername(username.getText().toString());
                    user.setFirstName(firstName.getText().toString());
                    user.setLastName(lastName.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setPhone(phone.getText().toString());
                }
            });




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


}

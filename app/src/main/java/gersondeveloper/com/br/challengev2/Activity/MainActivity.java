package gersondeveloper.com.br.challengev2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;

import com.google.gson.Gson;

import gersondeveloper.com.br.challengev2.Fragment.FragmentPrincipal;
import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;

public class MainActivity extends FragmentActivity {

    private Fragment contentFragment;
    FragmentPrincipal fragmentPrincipal;
    FragmentProductDetail fragmentProductDetail;
    Toolbar toolbar;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        String userString = intent.getStringExtra("user");
        bundle = new Bundle();
        bundle.putString("user", userString);

        if(savedInstanceState != null)
        {
            if(savedInstanceState.containsKey("content"))
            {
                String content = savedInstanceState.getString("content");
                if(content.equals(FragmentProductDetail.FRAG_ID))
                {
                    if(fragmentManager.findFragmentByTag(FragmentProductDetail.FRAG_ID) != null)
                    {
                        contentFragment = fragmentManager.findFragmentByTag(FragmentProductDetail.FRAG_ID);
                    }
                }
            }
            if(fragmentManager.findFragmentByTag(FragmentPrincipal.FRAG_ID) != null)
            {
                contentFragment = fragmentManager.findFragmentByTag(FragmentPrincipal.FRAG_ID);
            }
        }
        else
        {
            contentFragment = new FragmentPrincipal();
            contentFragment.setArguments(bundle);
            switchContent(contentFragment, FragmentPrincipal.FRAG_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(contentFragment instanceof FragmentPrincipal)
        {
            outState.putString("content",FragmentPrincipal.FRAG_ID);
        }
        else
        {
            outState.putString("content", FragmentProductDetail.FRAG_ID);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount() > 0)
        {
            super.onBackPressed();
        }
        else if(contentFragment instanceof FragmentPrincipal || fragmentManager.getBackStackEntryCount() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton(R.string.sair, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("logout", true);
                    startActivity(intent);

                }
            });

            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });

            builder.setMessage(R.string.sair_aplicacao);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void switchContent(Fragment fragment, String tag)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        while(fragmentManager.popBackStackImmediate());

        if(fragment != null)
        {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment, tag);

            //Adiciona apenas o product detail ao backstack
            if(!(fragment instanceof FragmentPrincipal))
            {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
            contentFragment = fragment;
        }
    }
}

package gersondeveloper.com.br.challengev2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import gersondeveloper.com.br.challengev2.Fragment.FragmentCompras;
import gersondeveloper.com.br.challengev2.Fragment.FragmentPrincipal;
import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    Toolbar toolbar;
    Bundle bundle;
    Fragment fragment;
    FragmentActivity activity;
    private Fragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.white, this.getTheme()));
        }else{
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_minhas_compras:
                //Starts details fragment
                fragment = new FragmentCompras();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                Fragment currentFrag = fragmentManager.findFragmentById(R.id.content_frame);
                if(currentFrag != null && currentFrag.getClass().equals(fragment.getClass()))
                {
                    transaction.addToBackStack(null);
                }
                else
                {
                    transaction.replace(R.id.content_frame, fragment,FragmentCompras.FRAG_ID).addToBackStack(null).commit();
                }

                return true;

            case R.id.action_logout:
                logout();
                return true;

            //debug
            /*case R.id.action_debug:
                Intent intent = new Intent(MainActivity.this, AndroidDatabaseManager.class);
                startActivity(intent);
                return true;*/

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void logout()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.sair, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ChallengeUtil.logout(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //clear backstack on logout
                clearBackStack();
                startActivity(intent);
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        builder.setMessage(R.string.logout_message);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void clearBackStack() {
        FragmentManager fm = this.getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0)
        {
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
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

        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("Backstack count: ", "" + count);

        if(count == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton(R.string.sair, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();

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
        else
        {
            getSupportFragmentManager().popBackStack();
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

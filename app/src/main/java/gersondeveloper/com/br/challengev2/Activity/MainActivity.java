package gersondeveloper.com.br.challengev2.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import gersondeveloper.com.br.challengev2.Fragment.FragmentPrincipal;
import gersondeveloper.com.br.challengev2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState != null)
            {
                return;
            }


            FragmentPrincipal fragmentPrincipal = new FragmentPrincipal();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragmentPrincipal).commit();
        }
    }



}

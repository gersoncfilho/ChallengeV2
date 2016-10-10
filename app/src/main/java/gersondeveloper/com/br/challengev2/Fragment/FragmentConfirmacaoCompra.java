package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import gersondeveloper.com.br.challengev2.Data.DatabaseHelper;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 09/10/2016.
 */

public class FragmentConfirmacaoCompra extends FragmentActivity implements View.OnClickListener {

    private DatabaseHelper databaseHelper;
    private TextView textViewProductName, textViewIdPayment, textViewProductValue;
    private Button buttonCancel, buttonSubmit;
    private ImageView imageViewProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confirma_compra);

        textViewProductName = (TextView) findViewById(R.id.confirmaTextViewProductName);
        textViewIdPayment = (TextView) findViewById(R.id.confirmaTextViewIdPayment);
        textViewProductValue = (TextView) findViewById(R.id.confirmaTextViewProductValue);
        imageViewProduct = (ImageView) findViewById(R.id.confirmacaoImageViewProduto);
        buttonSubmit = (Button) findViewById(R.id.confirmaButtonConfirma);
        buttonCancel = (Button) findViewById(R.id.confirmaButtonCancela);

        buttonCancel.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    //Initialize DatabaseHelper
    private DatabaseHelper getDatabaseHelper()
    {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit)
        {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Release helper when finish
        if(databaseHelper != null)
        {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}

package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;

import gersondeveloper.com.br.challengev2.Data.DatabaseHelper;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 09/10/2016.
 */

public class FragmentConfirmacaoCompra extends FragmentActivity {

    private DatabaseHelper databaseHelper;
    private TextView textViewUsername, textViewProductName, textViewIdPayment, textViewProductValue;
    private Button buttonCancel, buttonSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confirma_compra);



    }
}

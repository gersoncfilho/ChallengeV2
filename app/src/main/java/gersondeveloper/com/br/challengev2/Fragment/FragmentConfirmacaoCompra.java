package gersondeveloper.com.br.challengev2.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import gersondeveloper.com.br.challengev2.Connection.RestClient;
import gersondeveloper.com.br.challengev2.Data.DBHelper;
import gersondeveloper.com.br.challengev2.Model.Payment;
import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gerso on 09/10/2016.
 */

public class FragmentConfirmacaoCompra extends Fragment implements View.OnClickListener {

    public static final String TAG = FragmentConfirmacaoCompra.class.getName();
    public static final String FRAG_ID = "fragment_confirmacao_compra";
    private TextView textViewProductName, textViewIdPayment, textViewProductValue;
    private Button buttonCancel, buttonSubmit;
    private ImageView imageViewProduct;
    Activity activity;
    Transaction transaction;
    private static final String transactionSender = "Challenge Games";
    Calendar calendar;
    private String date;
    SimpleDateFormat sf;
    ProgressBar progressBar;

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        RestClient.initialize();
        DBHelper.
        calendar = Calendar.getInstance();
        sf = new SimpleDateFormat("dd-mm-yyyy");



    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirma_compra, container, false);

        textViewProductName = (TextView) view.findViewById(R.id.confirmaTextViewProductName);
        textViewIdPayment = (TextView) view.findViewById(R.id.confirmaTextViewIdPayment);
        textViewProductValue = (TextView) view.findViewById(R.id.confirmaTextViewProductValue);
        imageViewProduct = (ImageView) view.findViewById(R.id.confirmacaoImageViewProduto);
        buttonSubmit = (Button) view.findViewById(R.id.confirmaButtonConfirma);
        buttonCancel = (Button) view.findViewById(R.id.confirmaButtonCancela);
        progressBar = (ProgressBar) view.findViewById(R.id.confirma_progress_bar);

        buttonCancel.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            User user = new User();
            user = ChallengeUtil.getUser(activity);
            transaction = bundle.getParcelable("produto_a_confirmar");
            transaction.setUsername(user.getUsername());
            transaction.setEmail(user.getEmail());
            textViewProductName.setText(transaction.getProductName());
            textViewIdPayment.setText(String.valueOf(transaction.getIdPayment()));
            textViewProductValue.setText(String.valueOf(transaction.getProductValue()));
            imageViewProduct.setImageResource(transaction.getProdutImage());
        }
        
        return view;
    }

    //Initialize DatabaseHelper
    /*private DatabaseHelper getDatabaseHelper()
    {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(activity, DatabaseHelper.class);
        }
        return databaseHelper;
    }*/

    @Override
    public void onClick(View view) {

        if(view == buttonCancel)
        {
            getFragmentManager().popBackStack();
        }

        if(view == buttonSubmit)
        {
            progressBar.setVisibility(View.VISIBLE);
            //grava dados o banco para futuro cancelamento de compra se necessario
                try
                {
                    DBHelper.getInstance(activity.getApplicationContext()).getTransactionDAO();

                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            //envia transacao para o webservice

            Payment payment = Payment.Create(transactionSender, transaction, calendar, sf);

            RestClient.getInstance().registerPayment(payment, registerPayment);
        }

    }

    private Callback<Payment> registerPayment = new Callback<Payment>() {
        @Override
        public void onResponse(Call<Payment> call, Response<Payment> response) {
            Log.d(TAG, "Successful response.");
            Log.d(TAG, "Response code: " + String.valueOf(response.code()));
            progressBar.setVisibility(View.GONE);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setPositiveButton(R.string.fechar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Fragment fragment = new FragmentPrincipal();

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content_frame, fragment);

                    clearBackStack();

                    fragmentTransaction.commit();
                }
            });

            builder.setMessage(R.string.compra_sucesso);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        @Override
        public void onFailure(Call<Payment> call, Throwable t) {
            Log.e(TAG, t.toString());
        }
    };

    //Clear the fragment history after concludes the transaction
    public void clearBackStack()
    {
        FragmentManager fm = getFragmentManager();
        for(int i = 0;i<fm.getBackStackEntryCount();i++)
        {
            fm.popBackStack();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                    clearBackStack();
                }
                return false;
            }
        });
    }
}

package gersondeveloper.com.br.challengev2.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

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

    DBHelper dbHelper;

    private static final int REQUEST_EXTERNAL_STORAGE = 0;

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_EXTERNAL_STORAGE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    confirmaCompra();
                }
                else
                {
                    Fragment fragment = new FragmentPrincipal();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    clearBackStack();
                    fragmentTransaction.commit();
                }
                return;
        }
    }

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
            textViewProductValue.setText(ChallengeUtil.formatPrice(transaction.getProductValue()));
            imageViewProduct.setImageResource(transaction.getProdutImage());
        }
        
        return view;
    }

    private DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(activity.getApplicationContext(),DBHelper.class);
        }
        return dbHelper;
    }

    @Override
    public void onClick(View view) {

        if(view == buttonCancel)
        {
            getFragmentManager().popBackStack();
        }

        if(view == buttonSubmit)
        {
            confirmaCompraWrapper();
        }

    }

    private void confirmaCompraWrapper()
    {
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                Snackbar.make(getView().findViewById(R.id.content_frame), R.string.permission_internet, Snackbar.LENGTH_SHORT)
                        .setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
                            }
                        }).show();

            }
            else
            {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);

            }
        }
    }

    private void confirmaCompra()
    {
        progressBar.setVisibility(View.VISIBLE);
        //grava dados o banco para futuro cancelamento de compra se necessario
        try
        {
            final Dao<Transaction, Integer> transactionDAO = getHelper().getTransactionDAO();
            transactionDAO.create(transaction);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //envia transacao para o webservice
        Payment payment = Payment.Create(transactionSender, transaction, calendar, sf);
        RestClient.getInstance().registerPayment(payment, registerPayment);
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
    public void onDestroy() {
        super.onDestroy();
        if(dbHelper != null)
        {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
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

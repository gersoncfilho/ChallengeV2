package gersondeveloper.com.br.challengev2.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import gersondeveloper.com.br.challengev2.Data.DatabaseHelper;
import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 09/10/2016.
 */

public class FragmentConfirmacaoCompra extends Fragment implements View.OnClickListener {

    public static final String FRAG_ID = "fragment_confirmacao_compra";
    private DatabaseHelper databaseHelper;
    private TextView textViewProductName, textViewIdPayment, textViewProductValue;
    private Button buttonCancel, buttonSubmit;
    private ImageView imageViewProduct;
    Activity activity;
    Transaction transaction;

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

        buttonCancel.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            transaction = bundle.getParcelable("produto_a_confirmar");
            textViewProductName.setText(transaction.getProductName());
            textViewIdPayment.setText(String.valueOf(transaction.getIdPayment()));
            textViewProductValue.setText(String.valueOf(transaction.getProductValue()));
            imageViewProduct.setImageResource(transaction.getProdutImage());
        }
        
        return view;
    }

    //Initialize DatabaseHelper
    private DatabaseHelper getDatabaseHelper()
    {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(getActivity(),DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Release helper when finish
        if(databaseHelper != null)
        {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}

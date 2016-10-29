package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.Model.User;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;

/**
 * Created by gerso on 08/10/2016.
 */

public class FragmentProductDetail extends Fragment implements View.OnClickListener {

    public static final String FRAG_ID = "fragment_poduct_detail";
    private static final String TAG = FragmentProductDetail.class.getName();
    Product product;
    FragmentActivity activity;

    Transaction transaction;
    User user;
    Random random = new Random();

    @BindView(R.id.detailTextViewName)
    TextView textViewProductName;

    @BindView(R.id.detailTextViewPrice)
    TextView textViewProductValue;

    @BindView(R.id.detailTextViewDescription)
    TextView textViewProductDescription;

    @BindView(R.id.detailImageProduct)
    ImageView productImageView;

    @BindView(R.id.detailButtonComprar)
    Button buttonComprar;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);

        buttonComprar.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            product = bundle.getParcelable("product");
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewProductValue.setText(ChallengeUtil.formatPrice(product.getProductValue()));
            productImageView.setImageResource(product.getProductImage());
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == buttonComprar)
        {
            Log.d(TAG,getActivity().toString());
            int idPay = random.nextInt((10000000 - 100) + 1) + 100;
            String idPayment = String.valueOf(idPay);
            Bundle args = new Bundle();
            Fragment fragment = null;

            user = ChallengeUtil.getUser(activity);
            transaction = new Transaction(user.getUsername(), user.getEmail(), product.getName(), idPayment, product.getProductValue(), product.getProductImage());
            args.putParcelable("produto_a_confirmar", transaction);

            //Starts details fragment
            fragment = new FragmentConfirmacaoCompra();
            fragment.setArguments(args);

            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment, FragmentConfirmacaoCompra.FRAG_ID);
            transaction.addToBackStack(FragmentConfirmacaoCompra.FRAG_ID);
            transaction.commit();

        }
    }
}

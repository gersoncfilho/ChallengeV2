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

import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;

/**
 * Created by gerso on 08/10/2016.
 */

public class FragmentProductDetail extends Fragment {

    public static final String FRAG_ID = "fragment_poduct_detail";
    Product product;
    Activity activity;
    TextView textViewProductName, textViewProductValue, textViewProductDescription;
    ImageView productImageView;
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

        textViewProductName = (TextView) view.findViewById(R.id.detailTextViewName);
        textViewProductValue = (TextView) view.findViewById(R.id.detailTextViewPrice);
        textViewProductDescription = (TextView) view.findViewById(R.id.detailTextViewDescription);
        productImageView = (ImageView) view.findViewById(R.id.detailImageProduct);


        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            product = bundle.getParcelable("product");
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewProductValue.setText(String.valueOf(product.getProductValue()));
            productImageView.setImageResource(product.getProductImage());
        }

        return view;
    }
}

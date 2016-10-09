package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 08/10/2016.
 */

public class FragmentProductDetail extends Fragment {

    public static final String FRAG_ID = "fragment_poduct_detail";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }
}

package gersondeveloper.com.br.challengev2.Fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gersondeveloper.com.br.challengev2.Adapter.CardsAdapter;
import gersondeveloper.com.br.challengev2.Adapter.OpcoesAdapter;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.MySeedData;


/**
 * Created by gerso on 02/10/2016.
 */

public class FragmentPrincipal extends Fragment {


    private static final String TAG = FragmentPrincipal.class.getName();
    public static final String FRAG_ID = "fragment_principal";

    ArrayList<Product> data, data_cards;
    ViewPager viewPager;
    FragmentActivity activity;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    View view;
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, ("FragmentPincipal_onCreateView"));


        view = inflater.inflate(R.layout.fragment_principal, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.opcoes_view_pager);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cards);




        //if orientation landscape, configures gridlayoutmanager to only one card per column, otherwise two
        if (getActivity().getResources().getConfiguration().smallestScreenWidthDp <= 320 || getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Log.d("TAG", "small screen");
            gridLayoutManager = new GridLayoutManager(getActivity(),1);
        }
        else
        {
            Log.d("TAG", "big screen");
            gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        }

        //if screen size small, set layoutmanager to linear, otherwise, set layoutmanager gridlayout
        if (getActivity().getResources().getConfiguration().smallestScreenWidthDp <= 320)
        {
            //TODO: revisar layout
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        else
        {
            recyclerView.setLayoutManager(gridLayoutManager);
        }



        data = new ArrayList<Product>();
        for(int i = 0; i< MySeedData.nameArray.length; i++)
        {
            data.add(
                    new Product(
                            MySeedData.typeArray[i], MySeedData.nameArray[i], MySeedData.productValueArray[i], MySeedData.productDescriptionArray[i], MySeedData.drawableArray[i]
                    )
            );
        }

        viewPager.setAdapter(new OpcoesAdapter(activity, data));

        data_cards = new ArrayList<Product>();
        for(int i = 0; i< MySeedData.nameArray.length; i++)
        {
            data_cards.add(
                    new Product(
                            MySeedData.typeArray[i], MySeedData.nameArray[i], MySeedData.productValueArray[i], MySeedData.productDescriptionArray[i], MySeedData.drawableArrayCards[i]
                    )
            );
        }


        recyclerView.setAdapter(new CardsAdapter(activity, data_cards));

        return view;

    }


}

package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gersondeveloper.com.br.challengev2.Adapter.OpcoesAdapter;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.MySeedData;

/**
 * Created by gersoncardoso on 04/10/2016.
 */

public class OpcoesFragment extends Fragment {

    private ArrayList<Product> data;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opcoes, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.opcoes_view_pager);

        data = new ArrayList<Product>();
        for(int i=0;i< MySeedData.nameArray.length; i++)
        {
            data.add(
                    new Product(
                            MySeedData.typeArray[i], MySeedData.nameArray[i], MySeedData.productValueArray[i], MySeedData.productDescriptionArray[i], MySeedData.drawableArray[i]
                    )
            );
        }

        viewPager.setAdapter(new OpcoesAdapter(getContext(),data));

        return view;
    }
}

package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gersondeveloper.com.br.challengev2.Adapter.CardsAdapter;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.MySeedData;

/**
 * Created by gerso on 02/10/2016.
 */

public class CardsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Product> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cards);
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

       data = new ArrayList<Product>();

        for (int i = 0; i < MySeedData.nameArray.length;i++)
        {
            data.add(new Product(
                    MySeedData.typeArray[i],
                    MySeedData.nameArray[i],
                    MySeedData.productValueArray[i],
                    MySeedData.productDescriptionArray[i],
                    MySeedData.drawableArrayCards[i]
            ));
        }

        adapter = new CardsAdapter(data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

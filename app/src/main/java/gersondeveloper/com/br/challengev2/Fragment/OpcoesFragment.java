package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Product> data;
    private RecyclerView.Adapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_opcoes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_teste_fragment);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        data = new ArrayList<Product>();
        for(int i=0;i< MySeedData.nameArray.length; i++)
        {
            data.add(
                    new Product(
                            MySeedData.typeArray[i], MySeedData.nameArray[i], MySeedData.productValueArray[i], MySeedData.productDescriptionArray[i], MySeedData.drawableArray[i]
                    )
            );
        }

        adapter = new OpcoesAdapter(data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

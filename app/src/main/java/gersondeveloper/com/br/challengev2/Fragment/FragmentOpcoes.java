package gersondeveloper.com.br.challengev2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gersondeveloper.com.br.challengev2.Adapter.CardsAdapter;
import gersondeveloper.com.br.challengev2.Adapter.FragmentOpcoesAdapter;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.MySeedData;

/**
 * Created by gerso on 10/10/2016.
 */

public class FragmentOpcoes extends Fragment{

    public static final String FRAG_ID = "fragment_opcoes";
    public static final String TAG = FragmentOpcoes.class.getName();

    GridLayoutManager gridLayoutManager;
    FragmentActivity activity;
    ArrayList<Product> data_cards;
    String tipoProduto;

    @BindView(R.id.opcoes_rv_cards)
    RecyclerView recyclerView;


    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "FragmentOpcoes onAttach called");
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
        View view  = inflater.inflate(R.layout.fragment_opcoes, container, false);
        ButterKnife.bind(this, view);

        gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            tipoProduto = bundle.getString("tipo_produto");
        }

        createDataCards(tipoProduto);

        recyclerView.setAdapter(new FragmentOpcoesAdapter(activity, data_cards));

        return view;
    }

    private void createDataCards(String tipoProduto)
    {
        data_cards = new ArrayList<Product>();

        switch (tipoProduto)
        {
            case "consolePS4":
                for(int i=0; i< MySeedData.ps4NameArray.length; i++)
                {
                    data_cards.add(new Product(
                            tipoProduto, MySeedData.ps4NameArray[i], MySeedData.ps4ValueArray[i], MySeedData.ps4ProductDescriptionArray[i], MySeedData.ps4DrawableArrayCards[i])
                    );
                }
                break;
            case "consoleXbox":
                for(int i=0; i< MySeedData.xboxNameArray.length; i++)
                {
                    data_cards.add(new Product(
                            tipoProduto, MySeedData.xboxNameArray[i], MySeedData.xboxValueArray[i], MySeedData.xboxProductDescriptionArray[i], MySeedData.xboxDrawableArrayCards[i])
                    );
                }
                break;
            case "accessories":
                for(int i=0; i< MySeedData.acessoriosNameArray.length; i++)
                {
                    data_cards.add(new Product(
                            tipoProduto, MySeedData.acessoriosNameArray[i], MySeedData.acessoriosValueArray[i], MySeedData.acessoriosProductDescriptionArray[i], MySeedData.acessoriosDrawableArrayCards[i])
                    );
                }
                break;
        }
    }
}

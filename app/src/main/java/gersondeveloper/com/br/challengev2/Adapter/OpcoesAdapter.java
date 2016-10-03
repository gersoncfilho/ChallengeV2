package gersondeveloper.com.br.challengev2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;

import java.util.List;

import gersondeveloper.com.br.challengev2.Model.Product;

/**
 * Created by gerso on 03/10/2016.
 */

public class OpcoesAdapter extends RecyclerView<OpcoesAdapter.MyViewHolder> {

    private List<Product> products;
    private  CardView cardView;

    public OpcoesAdapter(Context context, Product product, ProductOnClickListener productOnClickListener)
    {

    }


}

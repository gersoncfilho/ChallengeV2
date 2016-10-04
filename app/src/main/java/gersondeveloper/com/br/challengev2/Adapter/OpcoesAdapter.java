package gersondeveloper.com.br.challengev2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gersoncardoso on 03/10/2016.
 */

public class OpcoesAdapter extends RecyclerView.Adapter<OpcoesAdapter.MyViewHolder> {

    private ArrayList<Product> products;

    public static class MyViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView imageView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.image_view_opcoes);
        }
    }

    public OpcoesAdapter(ArrayList<Product> data)
    {
        this.products = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.opcoes_layout, parent, false);

        //TODO create click listener

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        imageView.setImageResource(products.get(position).getProductImage());
    }



    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return products.size();
    }
}

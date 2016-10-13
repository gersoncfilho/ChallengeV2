package gersondeveloper.com.br.challengev2.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gersondeveloper.com.br.challengev2.Fragment.FragmentOpcoes;
import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 10/10/2016.
 */

public class FragmentOpcoesAdapter extends RecyclerView.Adapter<FragmentOpcoesAdapter.MyViewHolder> {

    private ArrayList<Product> dataSet;
    FragmentActivity activity;
    LayoutInflater inflater;
    CardView cardView;
    List<Product> products;



    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewPrice;
        TextView textViewDescription;
        ImageView imageViewProduct;
        TextView textViewName;
        CardView cardview;

        public MyViewHolder (View viewItem)
        {
            super(viewItem);
            this.textViewName = (TextView) viewItem.findViewById(R.id.opcoesProductName);
            this.textViewPrice = (TextView) viewItem.findViewById(R.id.opcoesProductPrice);
            this.textViewDescription = (TextView) viewItem.findViewById(R.id.opcoesProductDescription);
            this.imageViewProduct = (ImageView) viewItem.findViewById(R.id.opcoesImageView);
            this.cardview = (CardView) viewItem.findViewById(R.id.opcoes_card_cardview);
        }
    }

    public FragmentOpcoesAdapter(FragmentActivity fragmentActivity, ArrayList<Product> dataset)
    {
        this.dataSet = dataset;
        this.activity = fragmentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.opcoes_card_layout, parent, false);

        FragmentOpcoesAdapter.MyViewHolder myViewHolder = new FragmentOpcoesAdapter.MyViewHolder(view);
        return myViewHolder;

        }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewPrice = holder.textViewPrice;
        TextView textViewDescription = holder.textViewDescription;
        ImageView imageViewProduct = holder.imageViewProduct;
        CardView cardView = holder.cardview;

        textViewName.setText(dataSet.get(position).getName());
        textViewPrice.setText(String.valueOf(dataSet.get(position).getProductValue()));
        textViewDescription.setText(dataSet.get(position).getDescription());
        imageViewProduct.setImageResource(dataSet.get(position).getProductImage());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                Fragment fragment = null;

                Product product = (Product) dataSet.get(position);
                args.putParcelable("product", product);

                //Starts details fragment
                fragment = new FragmentProductDetail();
                fragment.setArguments(args);

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, fragment, FragmentProductDetail.FRAG_ID);
                transaction.addToBackStack(FragmentOpcoes.FRAG_ID);
                transaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

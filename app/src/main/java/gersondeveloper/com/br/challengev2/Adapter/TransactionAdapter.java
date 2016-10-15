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
import java.util.zip.Inflater;

import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerson on 10/15/2016.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private ArrayList<Transaction> dataSet;
    FragmentActivity activity;
    LayoutInflater inflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIdPayment;
        TextView textViewProductName;
        ImageView imageViewProduct;
        TextView textViewProductValue;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewIdPayment = (TextView) itemView.findViewById(R.id.compras_idPayment_textView);
            this.textViewProductName = (TextView) itemView.findViewById(R.id.compras_product_name_textView);
            this.textViewProductValue = (TextView) itemView.findViewById(R.id.compras_product_value_TextView);
            this.imageViewProduct = (ImageView) itemView.findViewById(R.id.compras_product_image_view);
            this.cardview = (CardView) itemView.findViewById(R.id.opcoes_card_view);
        }
    }

    public TransactionAdapter(FragmentActivity activity, ArrayList<Transaction> data)
    {
        this.activity = activity;
        this.dataSet = data;
    }

    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.compras_layout, parent, false);

        TransactionAdapter.MyViewHolder myViewHolder = new TransactionAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final TransactionAdapter.MyViewHolder holder, final int listPosition) {

        TextView textViewIdPayment = holder.textViewIdPayment;
        TextView textViewProductName = holder.textViewProductName;
        TextView textViewProductValue = holder.textViewProductValue;
        ImageView imageViewProduct = holder.imageViewProduct;
        CardView cardView = holder.cardview;

        textViewIdPayment.setText(String.valueOf(dataSet.get(listPosition).getProductValue()));
        textViewProductName.setText(dataSet.get(listPosition).getProductName());
        textViewProductValue.setText(String.valueOf(dataSet.get(listPosition).getProductValue()));
        imageViewProduct.setImageResource(dataSet.get(listPosition).getProdutImage());
/*

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                Fragment fragment = null;

                Product product = (Product) dataSet.get(listPosition);
                args.putParcelable("product", product);

                //Starts details fragment
                fragment = new FragmentProductDetail();
                fragment.setArguments(args);

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, fragment, FragmentProductDetail.FRAG_ID);
                transaction.addToBackStack(FragmentProductDetail.FRAG_ID);
                transaction.commit();
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

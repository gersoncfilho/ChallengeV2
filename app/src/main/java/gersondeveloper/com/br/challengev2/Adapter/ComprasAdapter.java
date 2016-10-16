package gersondeveloper.com.br.challengev2.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gersondeveloper.com.br.challengev2.Activity.LoginActivity;
import gersondeveloper.com.br.challengev2.Activity.MainActivity;
import gersondeveloper.com.br.challengev2.Fragment.FragmentConfirmacaoCompra;
import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;

/**
 * Created by gerso on 13/10/2016.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.MyViewHolder> {

    private ArrayList<Transaction> dataset;
    private FragmentActivity activity;
    LayoutInflater layoutInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewPrice;
        TextView textViewName;
        ImageView imageViewProduct;
        TextView textIdPayment;
        Button cancelaCompraButton;
        CardView cardview;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.compras_product_value_TextView);
            this.textIdPayment = (TextView) itemView.findViewById(R.id.compras_idPayment_textView);
            this.textViewName = (TextView) itemView.findViewById(R.id.compras_product_name_textView);
            this.imageViewProduct = (ImageView) itemView.findViewById(R.id.compras_product_image_view);
            this.cancelaCompraButton = (Button) itemView.findViewById(R.id.compras_cancela_compra_button);
        }
    }

    public ComprasAdapter(FragmentActivity activity, ArrayList<Transaction> data)
    {
        this.activity = activity;
        this.dataset = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.compras_layout, parent, false);

       MyViewHolder myViewHolder = new ComprasAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewPrice = holder.textViewPrice;
        TextView textIdPayment = holder.textIdPayment;
        ImageView imageViewProduct = holder.imageViewProduct;

        textViewName.setText(dataset.get(position).getProductName());
        textViewPrice.setText(ChallengeUtil.formatPrice(dataset.get(position).getProductValue()));
        textIdPayment.setText(String.valueOf(dataset.get(position).getIdPayment()));
        imageViewProduct.setImageResource(dataset.get(position).getProdutImage());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

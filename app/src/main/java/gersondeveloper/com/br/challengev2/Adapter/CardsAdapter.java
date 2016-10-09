package gersondeveloper.com.br.challengev2.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import gersondeveloper.com.br.challengev2.Fragment.FragmentPrincipal;
import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 05/10/2016.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {

    private ArrayList<Product> dataSet;
    FragmentActivity activity;
    LayoutInflater inflater;
    CardView cardView;
    List<Product> products;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textViewPrice;
            TextView textViewDescription;
            ImageView imageViewProduct;
            TextView textViewName;
            CardView cardview;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.textViewName = (TextView) itemView.findViewById(R.id.detailTextViewName);
                this.textViewPrice = (TextView) itemView.findViewById(R.id.detailTextViewPrice);
                this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
                this.imageViewProduct = (ImageView) itemView.findViewById(R.id.imageViewProduct);
                this.cardview = (CardView) itemView.findViewById(R.id.opcoes_card_view);
            }
        }

        public CardsAdapter(FragmentActivity activity, ArrayList<Product> data) {
            this.dataSet = data;
            this.activity = activity;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cards_layout, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

            TextView textViewName = holder.textViewName;
            TextView textViewPrice = holder.textViewPrice;
            TextView textViewDescription = holder.textViewDescription;
            ImageView imageViewProduct = holder.imageViewProduct;
            CardView cardView = holder.cardview;

            textViewName.setText(dataSet.get(listPosition).getName());
            textViewPrice.setText(String.valueOf(dataSet.get(listPosition).getProductValue()));
            textViewDescription.setText(dataSet.get(listPosition).getDescription());
            imageViewProduct.setImageResource(dataSet.get(listPosition).getProductImage());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle args = new Bundle();
                    Fragment fragment = null;

                    /*Product product = (Product) dataSet.get(listPosition);
                    args.putParcelable(product);*/

                    fragment = new FragmentProductDetail();
                    Product product = new Product();
                    product.setName(dataSet.get(listPosition).getName().toString());
                    product.setProductValue(dataSet.get(listPosition).getProductValue());
                    product.setDescription(dataSet.get(listPosition).getDescription());
                    product.setProductImage(dataSet.get(listPosition).getProductImage());
                    Bundle bundle = new Bundle();
                    Gson gson = new Gson();
                    String productString = gson.toJson(product);
                    bundle.putString("product", productString);
                    fragment.setArguments(bundle);




                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, fragment, FragmentProductDetail.FRAG_ID);
                    transaction.addToBackStack(FragmentProductDetail.FRAG_ID);
                    transaction.commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

    }

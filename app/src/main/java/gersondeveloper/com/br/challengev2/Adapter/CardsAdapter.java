package gersondeveloper.com.br.challengev2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 05/10/2016.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {

        private ArrayList<Product> dataSet;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textViewPrice;
            TextView textViewDescription;
            ImageView imageViewProduct;
            TextView textViewName;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
                this.textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
                this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
                this.imageViewProduct = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            }
        }

        public CardsAdapter(ArrayList<Product> data) {
            this.dataSet = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
        int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cards_layout, parent, false);

            //view.setOnClickListener(MainActivity.myOnClickListener);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

            TextView textViewName = holder.textViewName;
            TextView textViewPrice = holder.textViewPrice;
            TextView textViewDescription = holder.textViewDescription;
            ImageView imageViewProduct = holder.imageViewProduct;

            textViewName.setText(dataSet.get(listPosition).getName());
            textViewPrice.setText(dataSet.get(listPosition).getProductValue().toString());
            textViewDescription.setText(dataSet.get(listPosition).getDescription());
            imageViewProduct.setImageResource(dataSet.get(listPosition).getProductImage());
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

    }

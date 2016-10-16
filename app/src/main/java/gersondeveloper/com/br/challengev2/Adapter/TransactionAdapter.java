package gersondeveloper.com.br.challengev2.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;

import gersondeveloper.com.br.challengev2.Connection.RestClient;
import gersondeveloper.com.br.challengev2.Data.DBHelper;
import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.Model.Payment;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.R;
import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gerson on 10/15/2016.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private ArrayList<Transaction> dataSet;
    FragmentActivity activity;
    LayoutInflater inflater;

    private Dao<Transaction, Integer> transactionDAO;
    private DBHelper dbHelper;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIdPayment;
        TextView textViewProductName;
        ImageView imageViewProduct;
        TextView textViewProductValue;
        Button cancelaCompraButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewIdPayment = (TextView) itemView.findViewById(R.id.compras_idPayment_textView);
            this.textViewProductName = (TextView) itemView.findViewById(R.id.compras_product_name_textView);
            this.textViewProductValue = (TextView) itemView.findViewById(R.id.compras_product_value_TextView);
            this.imageViewProduct = (ImageView) itemView.findViewById(R.id.compras_product_image_view);
            this.cancelaCompraButton = (Button) itemView.findViewById(R.id.compras_cancela_compra_button);
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
        Button cancelaCompraButton = holder.cancelaCompraButton;

        textViewIdPayment.setText(String.valueOf(dataSet.get(listPosition).getIdPayment()));
        textViewProductName.setText(dataSet.get(listPosition).getProductName());
        textViewProductValue.setText(ChallengeUtil.formatPrice(dataSet.get(listPosition).getProductValue()));
        imageViewProduct.setImageResource(dataSet.get(listPosition).getProdutImage());

        cancelaCompraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int idPayment = dataSet.get(listPosition).getIdPayment();

                        RestClient.getInstance().deletePayment(idPayment, cancelaTransacao);
                    }
                });

                builder.setNegativeButton(R.string.cancela, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });

                builder.setMessage(R.string.cancelar_compra_message);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private Callback<Payment> cancelaTransacao = new Callback<Payment>() {
        @Override
        public void onResponse(Call<Payment> call, Response<Payment> response) {
            /*try {
                transactionDAO = getHelper().getTransactionDAO();
                final QueryBuilder<Transaction, Integer> queryBuilder = transactionDAO.queryBuilder();
                queryBuilder.where().eq(Transaction.ID_TRANSACTION, dataSet.get());
                final PreparedQuery<Transaction> preparedQuery = queryBuilder.prepare();
                final Iterator<Transaction> transactionIterator = transactionDAO.query(preparedQuery).iterator();
                while(transactionIterator.hasNext())
                {
                    final Transaction transaction1 = transactionIterator.next();
                    transactions.add(transaction1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }

        @Override
        public void onFailure(Call<Payment> call, Throwable t) {

        }
    };

    private DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(activity.getApplicationContext(), DBHelper.class);
        }
        return dbHelper;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(dbHelper != null)
        {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

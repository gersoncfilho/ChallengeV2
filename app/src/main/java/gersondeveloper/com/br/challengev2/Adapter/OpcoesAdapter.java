package gersondeveloper.com.br.challengev2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import gersondeveloper.com.br.challengev2.Fragment.FragmentPrincipal;
import gersondeveloper.com.br.challengev2.Fragment.FragmentProductDetail;
import gersondeveloper.com.br.challengev2.Model.Product;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gersoncardoso on 03/10/2016.
 */

public class OpcoesAdapter extends PagerAdapter {

    LayoutInflater inflater;
    ArrayList<Product> products;

    FragmentActivity activity;

    public OpcoesAdapter(FragmentActivity activity, ArrayList<Product> data)
    {
        this.activity = activity;
        this.products = data;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.opcoes_layout, container, false);
        imageView = (ImageView) itemView.findViewById(R.id.image_view_opcoes);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Log.d("Position adapter-->", "" + position);
                fragment = new FragmentProductDetail();

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, fragment, FragmentProductDetail.FRAG_ID);
                transaction.addToBackStack(FragmentPrincipal.FRAG_ID);
                transaction.commit();
            }
        });
        imageView.setImageResource(products.get(position).getProductImage());

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((RelativeLayout)object);
    }
}

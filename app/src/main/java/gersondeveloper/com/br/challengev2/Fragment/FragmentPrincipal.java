package gersondeveloper.com.br.challengev2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 02/10/2016.
 */

public class FragmentPrincipal extends Fragment {

    View a,b,c;
    private static final String TAG = FragmentPrincipal.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, ("onCreateView"));
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        a = view.findViewById(R.id.fragment_opcoes);
        b = view.findViewById(R.id.fragment_botoes);
        c = view.findViewById(R.id.fragment_cards);
        return view;
    }


}

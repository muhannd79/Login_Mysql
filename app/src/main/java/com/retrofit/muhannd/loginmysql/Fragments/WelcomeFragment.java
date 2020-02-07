package com.retrofit.muhannd.loginmysql.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.retrofit.muhannd.loginmysql.MainActivity;
import com.retrofit.muhannd.loginmysql.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {


    TextView textView;
    Button btnLogout;
    logoutListener logoutListener;

    public interface logoutListener{

        public void logout();

    }

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        textView = view.findViewById(R.id.txtwel);
        btnLogout = view.findViewById(R.id.button);


        textView.setText("Welcome "+MainActivity.prefConfig.readName());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutListener.logout();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        logoutListener = (logoutListener) context;
    }
}

package com.retrofit.muhannd.loginmysql.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.retrofit.muhannd.loginmysql.MainActivity;
import com.retrofit.muhannd.loginmysql.Models.User;
import com.retrofit.muhannd.loginmysql.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.retrofit.muhannd.loginmysql.MainActivity.apiInterface;


public class RegistrationFragment extends Fragment {



    private EditText Name, userName , Paswwrod;
    private Button btnReg;
    private reLoadLoginListener reLoadLoginListener;
    public interface reLoadLoginListener {

      public void   reLoginFragment();
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_registration, container, false);

        Name = view.findViewById(R.id.name);
        Paswwrod =view.findViewById(R.id.txt_user_password);
        userName = view.findViewById(R.id.txt_user_name);

        btnReg = view.findViewById(R.id.register_btn);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfromRegistration ();

            }
        });
        return view;
    }

    public void  perfromRegistration (){
        String name  = Name.getText().toString();
        String passwrord = Paswwrod.getText().toString();
        String UserName = userName.getText().toString();

        Call<User> call = apiInterface.performRegister(name,UserName,passwrord);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok")){

                    MainActivity.prefConfig.displayToast("Registration successfully....!");
                    reLoadLoginListener.reLoginFragment();

                }  else if (response.body().getResponse().equals("exist")) {

                    MainActivity.prefConfig.displayToast("User ALready exists....!");
                }  else if (response.body().getResponse().equals("error")) {

                    MainActivity.prefConfig.displayToast("Error Try Again....!");
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                MainActivity.prefConfig.displayToast("Errrrorrrr");
            }
        });

        Name.setText("");
        Paswwrod.setText("");
        userName.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity)context;
        reLoadLoginListener = (reLoadLoginListener) activity;
    }
}

package com.retrofit.muhannd.loginmysql.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.retrofit.muhannd.loginmysql.MainActivity;
import com.retrofit.muhannd.loginmysql.Models.User;
import com.retrofit.muhannd.loginmysql.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.retrofit.muhannd.loginmysql.MainActivity.apiInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView regText;

    private EditText userName , Paswwrod;
    private Button btnLogin;

    onloginFromActivityLisner onloginFromActivityLisner;

    public interface  onloginFromActivityLisner {

        public void  performRegister();
        public void  performLogin(String name);
    }


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_login, container, false);

        regText = view.findViewById(R.id.register_txt);

        userName = view.findViewById(R.id.user_name);
        Paswwrod = view.findViewById(R.id.user_password);

        btnLogin = view.findViewById(R.id.login_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               perfomLogin();
            }
        });

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onloginFromActivityLisner.performRegister();


            }
        });

        return  view;
    }

    private void perfomLogin(){


        String passwrord = Paswwrod.getText().toString();
        String UserName = userName.getText().toString();

        Call<User> call = apiInterface.performLogin(UserName,passwrord);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.d("tmz",response.body().toString());

                if(response.body().getResponse().equals("ok")){

                    MainActivity.prefConfig.writeLoginStatus(true);
                    onloginFromActivityLisner.performLogin(response.body().getName());

                }  else     if(response.body().getResponse().equals("faild")){
                    MainActivity.prefConfig.displayToast("Login Falied");


                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        Paswwrod.setText("");
        userName.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        onloginFromActivityLisner = (onloginFromActivityLisner) activity;

    }
}

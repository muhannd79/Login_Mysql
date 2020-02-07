package com.retrofit.muhannd.loginmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.retrofit.muhannd.loginmysql.Fragments.LoginFragment;
import com.retrofit.muhannd.loginmysql.Fragments.RegistrationFragment;
import com.retrofit.muhannd.loginmysql.Fragments.WelcomeFragment;
import com.retrofit.muhannd.loginmysql.Retrofit.ApiClient;
import com.retrofit.muhannd.loginmysql.Retrofit.ApiInterface;
import com.retrofit.muhannd.loginmysql.Utits.PrefConfig;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements LoginFragment.onloginFromActivityLisner,WelcomeFragment.logoutListener,RegistrationFragment.reLoadLoginListener {

    public static PrefConfig prefConfig;

    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig = new PrefConfig(MainActivity.this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        if(findViewById(R.id.fragment_container)!=null){

            if(savedInstanceState!=null){
                return;
            }

            if(prefConfig.readLoginStatus()){

               getSupportFragmentManager()
                       .beginTransaction()
                       .add(R.id.fragment_container,new WelcomeFragment())
                       .commit();
            }

            else {
               getSupportFragmentManager()
                       .beginTransaction()
                       .add(R.id.fragment_container,new LoginFragment())
                       .commit();

            }
        }
    }

    @Override
    public void performRegister() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new RegistrationFragment())
            //    .addToBackStack(null)
                .commit();


    }

    @Override
    public void performLogin(String name) {

        prefConfig.writeName(name);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new WelcomeFragment()).commit();

    }

    @Override
    public void logout() {
        prefConfig.writeName("");
        prefConfig.writeLoginStatus(false);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new LoginFragment()).commit();
    }

    @Override
    public void reLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new LoginFragment())
                .commit();
    }
}

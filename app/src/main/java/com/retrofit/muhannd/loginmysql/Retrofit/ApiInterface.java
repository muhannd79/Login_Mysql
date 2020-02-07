package com.retrofit.muhannd.loginmysql.Retrofit;

import com.retrofit.muhannd.loginmysql.Models.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("register.php")
    Call<User> performRegister(@Query("name")String Name,@Query("user_name") String userName,@Query("user_password") String userPassword);

    @GET("login.php")
    Call<User> performLogin(@Query("user_name") String userName,@Query("user_password") String userPassword);
}



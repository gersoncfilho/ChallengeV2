package gersondeveloper.com.br.challengev2.Interface;

import java.util.Map;

import gersondeveloper.com.br.challengev2.Model.Payment;
import gersondeveloper.com.br.challengev2.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by gerso on 01/10/2016.
 */


public interface MyAPIInterface {

    //USERS
    /**
     * Get user from API
     * @return code 200 - success operation
     */
    //login
    @GET("api/users/login/")
    Call<User> getUserLogin(@QueryMap Map<String,String> data);

    //logout
    @GET("api/users/logout")
    Call<User> getUserLogout();

    //insert user
    @POST("api/users")
    Call<User> postUser(@Body User user);

    //edit user
    @PUT("api/users/{username}")
    Call<User> putUser(@Path("username")String username);

    //delete user
    @DELETE("api/users/{username}")
    Call<User> deleteUser(@Path("username")String username);


    //PAYMENTS
    //insert payment
    @POST("api/payments")
    Call<Payment> postPayment(@Body Payment payment);

    //delete payment
    @DELETE("api/payments/reference")
    Call<Payment> deletePayment(@Query("idPayment")String idPayment);


}

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
    @GET("users/login/")
    Call<User> getUserLogin(@QueryMap Map<String,String> data);

    //logout
    @GET("users/logout")
    Call<User> getUserLogout();

    //insert user
    @POST("api/users")
    Call<User> postUser(@Body User user);

    //edit user
    @PUT("users/{username}")
    Call<User> putUser(@Path("username")String username);

    //delete user
    @DELETE("users/{username}")
    Call<User> deleteUser(@Path("username")String username);


    //PAYMENTS
    //insert payment
    @POST("payment")
    Call<Payment> postPayment(Payment payment);

    //delete payment
    @DELETE("payment/{paymentId}")
    Call<Payment> deletePayment(@Path("paymentId")int paymentId);


}

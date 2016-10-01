package gersondeveloper.com.br.challengev2.Interface;

import gersondeveloper.com.br.challengev2.Model.Payment;
import gersondeveloper.com.br.challengev2.Model.User;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by gerso on 01/10/2016.
 */


public interface MyAPIInterface {

    //USERS
    /**
     * Get user from API
     * @param username
     * @param password
     * @return code 200 - success operation
     */
    //login
    @GET("users/login/")
    Call<User> getUserLogin(@Path("username")String username, @Path("password")String password);

    //logout
    @GET("users/logout")
    Call<User> getUserLogout();

    //getUser
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    //insert user
    @POST("users/")
    Call<User> postUser(User user);

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

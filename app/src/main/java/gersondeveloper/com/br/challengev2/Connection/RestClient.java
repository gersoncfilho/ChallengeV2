package gersondeveloper.com.br.challengev2.Connection;


import java.util.Map;
import gersondeveloper.com.br.challengev2.Interface.MyAPIInterface;
import gersondeveloper.com.br.challengev2.Model.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gerso on 01/10/2016.
 */

public class RestClient {

    private static final String TAG = "RestClient";
    private static final String BASE_URL = "http://apis.gersondeveloper.com/Clientes/";
    private static final int TIMEOUT = 60000;

    private static RestClient instance;
    private Retrofit retrofit;
    MyAPIInterface apiService;



    //Singleton Initializer
    public static void initialize()
    {
        if(instance == null)
        {
            instance = new RestClient();
        }
    }

    //Singleton Instance Getter
    public static RestClient getInstance()
    {
        initialize();

        return instance;
    }

    //Constructor
    private RestClient()
    {


        this.retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

       this.apiService = retrofit.create(MyAPIInterface.class);
    }

    public void getUserLogin(Map<String,String> data, Callback<User> callback)
    {
        Call<User> call = apiService.getUserLogin(data);
        call.enqueue(callback);
    }

}

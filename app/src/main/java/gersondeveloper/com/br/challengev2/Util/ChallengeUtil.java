package gersondeveloper.com.br.challengev2.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gersondeveloper.com.br.challengev2.Model.Payment;
import gersondeveloper.com.br.challengev2.Model.Sender;
import gersondeveloper.com.br.challengev2.Model.User;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by gerso on 02/10/2016.
 */

/**
 * Class check if user is valid
 * Saves and retrieves user data
 */
public class ChallengeUtil {
    //User
    public static final String PREFERENCE_NAME = "ChanllengePrefs";
    public static final String USERNAME = "username";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lasttName";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final List<Payment> PAYMENTS = new ArrayList<Payment>();

    //Payment
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String REFERENCE = "reference";
    public static final String DATE = "date";
    public static final String SENDER = "sender";



    /**
     *
     * @param context
     * @return boolean if user have a valid login
     */
    public static boolean isLoginStored(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ChallengeUtil.PREFERENCE_NAME, context.MODE_PRIVATE);
        Map<String,?> map = sharedPreferences.getAll();
        if(map.size()>0)
        {
            String username = (String) map.get(ChallengeUtil.USERNAME);
            if(username == null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param context
     * @return user
     */
    public static User getUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,0);
        User user = new User();
        user.setUsername(sharedPreferences.getString(USERNAME,""));
        user.setFirstName(sharedPreferences.getString(FIRST_NAME,""));
        user.setLastName(sharedPreferences.getString(LAST_NAME,""));
        user.setEmail(sharedPreferences.getString(EMAIL,""));
        user.setPhone(sharedPreferences.getString(PHONE,""));
        return user;
    }

    public static void saveUser(Context context, User user)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,0);
        sharedPreferences.edit().putString(USERNAME, user.getUsername()).apply();
        sharedPreferences.edit().putString(FIRST_NAME, user.getFirstName()).apply();
        sharedPreferences.edit().putString(LAST_NAME, user.getLastName()).apply();
        sharedPreferences.edit().putString(EMAIL, user.getEmail()).apply();
        sharedPreferences.edit().putString(PHONE, user.getPhone()).apply();
    }

    public static void saveTransaction(Context context, Payment payment, Sender sender)
    {
        Gson gson = new Gson();
        String objetctJson = gson.toJson(sender);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,0);
        sharedPreferences.edit().putString(NAME, payment.getName()).apply();
        sharedPreferences.edit().putString(DESCRIPTION, payment.getDescription()).apply();
        sharedPreferences.edit().putString(DESCRIPTION, payment.getDescription()).apply();
        sharedPreferences.edit().putString(DESCRIPTION, payment.getDescription()).apply();
        sharedPreferences.edit().putString(SENDER,sender.toString()).apply();
    }




}

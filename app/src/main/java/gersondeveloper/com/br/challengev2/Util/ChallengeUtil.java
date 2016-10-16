package gersondeveloper.com.br.challengev2.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gersondeveloper.com.br.challengev2.Model.Payment;
import gersondeveloper.com.br.challengev2.Model.Product;
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

    //Product
    public static final String PRODUCT_TYPE = "type";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_VALUE = "product_value";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_IMAGE = "product_image";

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

/*    public static void saveTransaction(Context context, Payment payment, Sender sender)
    {
        Gson gson = new Gson();
        String objetctJson = gson.toJson(sender);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,0);
        sharedPreferences.edit().putString(NAME, payment.getName()).apply();
        sharedPreferences.edit().putString(DESCRIPTION, payment.getDescription()).apply();
        sharedPreferences.edit().putString(DESCRIPTION, payment.getDescription()).apply();
        sharedPreferences.edit().putString(DESCRIPTION, payment.getDescription()).apply();
        sharedPreferences.edit().putString(SENDER,sender.toString()).apply();
    }*/

    public static void logout(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, context.MODE_PRIVATE);
        sharedPreferences.edit().remove(USERNAME).apply();
        sharedPreferences.edit().remove(FIRST_NAME).apply();
        sharedPreferences.edit().remove(LAST_NAME).apply();
        sharedPreferences.edit().remove(EMAIL).apply();
        sharedPreferences.edit().remove(PHONE).apply();
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

    public static boolean emailValidator (String email)
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static String formatPrice(Double price)
    {
        Locale locale = new Locale("pt","BR");
        Currency currency = Currency.getInstance(locale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String priceFormatted = currencyFormatter.format(price);

        return priceFormatted;
    }

    public static boolean isUserResgistered(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,0);
        Map<String, ?> map = sharedPreferences.getAll();
        if(map.size()>0)
        {
            String username = (String) map.get(USERNAME);
            if(username != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

}

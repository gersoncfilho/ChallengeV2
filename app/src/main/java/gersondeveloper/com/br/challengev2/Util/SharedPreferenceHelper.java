package gersondeveloper.com.br.challengev2.Util;

/**
 * Created by gerso on 18/10/2016.
 */
import android.content.SharedPreferences;

import java.util.Calendar;

import gersondeveloper.com.br.challengev2.Model.SharedPreferenceEntry;

/**
 *  Helper class to manage access to {@link SharedPreferences}.
 */
public class SharedPreferenceHelper {

    // Keys for saving values in SharedPreferences.
    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_LAST_NAME = "key_last_name";
    public static final String KEY_EMAIL = "key_email";
    public static final String KEY_PHONE = "key_phone";

    // The injected SharedPreferences implementation to use for persistence.
    private final SharedPreferences mSharedPreferences;

    /**
     * Constructor with dependency injection.
     *
     * @param sharedPreferences The {@link SharedPreferences} that will be used in this DAO.
     */
    public SharedPreferenceHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public boolean savePersonalInfo(SharedPreferenceEntry sharedPreferenceEntry){
        // Start a SharedPreferences transaction.
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_USERNAME, sharedPreferenceEntry.getUsername());
        editor.putString(KEY_NAME, sharedPreferenceEntry.getName());
        editor.putString(KEY_LAST_NAME, sharedPreferenceEntry.getLastName());
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.getEmail());
        editor.putString(KEY_PHONE, sharedPreferenceEntry.getPhone());

        // Commit changes to SharedPreferences.
        return editor.commit();
    }

    public SharedPreferenceEntry getPersonalInfo() {
        // Get data from the SharedPreferences.
        String username = mSharedPreferences.getString(KEY_USERNAME, "");
        String name = mSharedPreferences.getString(KEY_NAME, "");
        String lastName = mSharedPreferences.getString(KEY_LAST_NAME, "");
        String email = mSharedPreferences.getString(KEY_EMAIL, "");
        String phone = mSharedPreferences.getString(KEY_PHONE, "");

        // Create and fill a SharedPreferenceEntry model object.
        return new SharedPreferenceEntry(username, name, lastName, email, phone);
    }
}
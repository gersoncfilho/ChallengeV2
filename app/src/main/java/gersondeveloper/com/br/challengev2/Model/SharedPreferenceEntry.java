package gersondeveloper.com.br.challengev2.Model;

import android.content.SharedPreferences;

/**
 * Created by gerso on 18/10/2016.
 */

public class SharedPreferenceEntry {

    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SharedPreferenceEntry(String username, String name, String lastName, String email, String phone)
    {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

}

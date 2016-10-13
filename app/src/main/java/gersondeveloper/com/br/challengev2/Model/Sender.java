package gersondeveloper.com.br.challengev2.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gerso on 01/10/2016.
 */

public class Sender implements Serializable {

    @SerializedName("Id")
    int id;

    @SerializedName("Name")
    String name;

    @SerializedName("Email")
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

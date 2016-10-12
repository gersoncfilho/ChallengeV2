package gersondeveloper.com.br.challengev2.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gerso on 01/10/2016.
 */

public class Payment implements Serializable {


    @SerializedName("Id")
    int id;

    @SerializedName("Name")
    String name;

    @SerializedName("Description")
    String description;

    @SerializedName("Reference")
    String reference;

    @SerializedName("Date")
    String date;

    @SerializedName("Sender")
    Sender sender;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


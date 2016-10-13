package gersondeveloper.com.br.challengev2.Model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    String sender;

    public Sender getSender() {
        Gson gson = new Gson();
        return gson.fromJson(sender, Sender.class);

    }

    public void setSender(Sender sender)
    {
        Gson gson = new Gson();
        this.sender = gson.toJson(sender);
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

    public Payment()
    {

    }

    public Payment(String transactionSender, Transaction transaction, Sender sender, String date)
    {
        this.setSender(sender);
        this.setName(transactionSender);
        this.setDate(date);
        this.setDescription(transaction.getProductName());
        this.setReference(String.valueOf(transaction.getIdPayment()));
    }

    public static Payment Create(String transactionSender, Transaction transaction, Calendar calendar, SimpleDateFormat dateFormat)
    {
        String date = dateFormat.format(calendar.getTime());

        Sender sender = new Sender();
        sender.setName(transaction.getUsername());
        sender.setEmail(transaction.getEmail());

        Payment payment = new Payment(transactionSender, transaction, sender, date);

        return payment;
    }
}


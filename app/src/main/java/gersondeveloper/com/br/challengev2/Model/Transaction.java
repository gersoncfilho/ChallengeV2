package gersondeveloper.com.br.challengev2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by gerso on 09/10/2016.
 */

public class Transaction implements Parcelable {

    @DatabaseField(generatedId = true, columnName = "id_transaction")
    public int idTransaction;

    @DatabaseField(columnName = "username")
    public String username;

    @DatabaseField(columnName = "product_name")
    public String productName;

    @DatabaseField(columnName = "id_payment")
    public int idPayment;

    @DatabaseField(columnName = "product_value")
    public Double productValue;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Double getProductValue() {
        return productValue;
    }

    public void setProductValue(Double productValue) {
        this.productValue = productValue;
    }

    //Constructor
    public Transaction(String username, String productName, int idPayment, double productValue)
    {
        this.username = username;
        this.productName = productName;
        this.idPayment = idPayment;
        this.productValue = productValue;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(productName);
        parcel.writeInt(idPayment);
        parcel.writeDouble(productValue);
    }

    //constructor for createFromParcel
    public Transaction(Parcel in)
    {
        this.username = in.readString();
        this.productName = in.readString();
        this.idPayment = in.readInt();
        this.productValue = in.readDouble();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}

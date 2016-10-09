package gersondeveloper.com.br.challengev2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by gerso on 01/10/2016.
 */

public class Product implements Parcelable {
    String productId;
    String productType;
    String productName;
    double productValue;
    String productDescription;
    int productImage;

    public Product(String productType, String productName, double productValue, String productDescription, int productImage)
    {
        this.productType = productType;
        this.productName = productName;
        this.productValue = productValue;
        this.productDescription = productDescription;
        this.productImage = productImage;
    }

    public String getType() {
        return productType;
    }

    public void setType(String type) {
        this.productType = productType;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public double getProductValue() {
        return productValue;
    }

    public void setProductValue(double productValue) {
        this.productValue = productValue;
    }

    public String getDescription() {
        return productDescription;
    }

    public void setDescription(String description) {
        this.productDescription = description;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }


    //Parcelling


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productType);
        parcel.writeString(productName);
        parcel.writeDouble(productValue);
        parcel.writeString(productDescription);
        parcel.writeInt(productImage);
    }

    //constructor for createFromParcel
    public Product(Parcel in)
    {
        this.productType = in.readString();
        this.productName = in.readString();
        this.productValue = in.readDouble();
        this.productDescription = in.readString();
        this.productImage = in.readInt();
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

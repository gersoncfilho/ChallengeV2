package gersondeveloper.com.br.challengev2.Model;

import java.util.Currency;

/**
 * Created by gerso on 01/10/2016.
 */

public class Product {
    String id;
    String type;
    String name;
    Currency productValue;
    String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getProductValue() {
        return productValue;
    }

    public void setProductValue(Currency productValue) {
        this.productValue = productValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

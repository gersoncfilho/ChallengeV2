package gersondeveloper.com.br.challengev2.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gerso on 01/10/2016.
 */

public class User implements Serializable {

   @SerializedName("Id")
   int id;

   @SerializedName("Username")
   String username;

   @SerializedName("FirstName")
   String firstName;

   @SerializedName("LastName")
   String lastName;

   @SerializedName("Email")
   String email;

   @SerializedName("Password")
   String password;

   @SerializedName("Phone")
   String phone;
   //String userStatus;

   public User(){}

   public User(int id, String username, String firstName, String lastName, String email, String password, String phone)
   {
      this.id=id;
      this.username=username;
      this.firstName=firstName;
      this.lastName=lastName;
      this.email=email;
      this.password=password;
      this.phone=phone;
   }


   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   /*public String getUserStatus() {
      return userStatus;
   }

   public void setUserStatus(String userStatus) {
      this.userStatus = userStatus;
   }*/
}

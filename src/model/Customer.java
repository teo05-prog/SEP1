package model;

import java.io.Serializable;

public class Customer implements Serializable
{
  private String firstName;
  private String lastName;
  private String phone;
  private String email;

  public Customer(String firstName, String lastName, String phone, String email)
  {
    setFirstName(firstName);
    setLastName(lastName);
    setPhone(phone);
    setEmail(email);
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setFirstName(String firstName)
  {
    if(firstName == null)
    {
      throw new IllegalArgumentException("First name must not be null");
    }
    if(firstName.length() < 2)
    {
      throw new IllegalArgumentException("First name must be a string of at least 2 characters");
    }
    this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
  }

  public void setLastName(String lastName)
  {
    if(lastName == null)
    {
      throw new IllegalArgumentException("Last name must not be null");
    }
    if(lastName.length() < 2)
    {
      throw new IllegalArgumentException("Last name must be a string of at least 2 characters");
    }
    this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    if(phone == null)
    {
      throw new IllegalArgumentException("Phone must not be null");
    }
    if(phone.length() != 8){
      throw new IllegalArgumentException("Phone number must be 8 digits long");
    }
    for (int i = 0; i < 8; i++)
    {
      if(phone.charAt(i) != '0' && phone.charAt(i) != '1'
          && phone.charAt(i) != '2' && phone.charAt(i) != '3'
          && phone.charAt(i) != '4' && phone.charAt(i) != '5'
          && phone.charAt(i) != '6' && phone.charAt(i) != '7'
          && phone.charAt(i) != '8' && phone.charAt(i) != '9'){
        throw new IllegalArgumentException("Phone number must contain only digits");
      }
    }
    this.phone = phone;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    if(email == null)
    {
      throw new IllegalArgumentException("Email must not be null");
    }
    if(!email.contains("@") || !email.contains(".")){
      throw new IllegalArgumentException("Email must contain '@' and '.'");
    }
    this.email = email;
  }

  public String toString()
  {
    return "First name: " + firstName + ", Last name: " + lastName + ", Phone: " + phone + ", Email: " + email;
  }

  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Customer other = (Customer)obj;
    return firstName.equals(other.getFirstName()) && lastName.equals(other.getLastName()) && phone.equals(other.getPhone()) && email.equals(other.getEmail());
  }
}
package model;

import java.io.Serializable;

/**
 * A class that manages personal information about a customer
 *
 * @author Jan Lewek
 */
public class Customer implements Serializable
{
  private static final long serialVersionUID = 5898788688332424564L;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;

  /**
   * A constructor that makes a new Customer with specified personal details
   *
   * @param firstName The customer's first name
   * @param lastName  The customer's last name
   * @param phone     The customer's phone number
   * @param email     The customer's email address
   */
  public Customer(String firstName, String lastName, String phone, String email)
  {
    setFirstName(firstName);
    setLastName(lastName);
    setPhone(phone);
    setEmail(email);
  }

  /**
   * Returns the customer's first name
   *
   * @return the first name of the customer
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Returns the customer's last name
   *
   * @return the last name of the customer
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Sets the customer's first name with validation
   *
   * @param firstName the first name to set
   * @throws IllegalArgumentException if the first name is null or less than 2 characters
   */
  public void setFirstName(String firstName)
  {
    if (firstName == null)
    {
      throw new IllegalArgumentException("First name must not be null");
    }
    if (firstName.length() < 2)
    {
      throw new IllegalArgumentException(
          "First name must be a string of at least 2 characters");
    }
    this.firstName =
        firstName.substring(0, 1).toUpperCase() + firstName.substring(1)
            .toLowerCase();
  }

  /**
   * Sets the customer's last name with validation
   *
   * @param lastName the last name to set
   * @throws IllegalArgumentException if the last name is null or less than 2 characters
   */
  public void setLastName(String lastName)
  {
    if (lastName == null)
    {
      throw new IllegalArgumentException("Last name must not be null");
    }
    if (lastName.length() < 2)
    {
      throw new IllegalArgumentException(
          "Last name must be a string of at least 2 characters");
    }
    this.lastName =
        lastName.substring(0, 1).toUpperCase() + lastName.substring(1)
            .toLowerCase();
  }

  /**
   * Returns the customer's phone number
   *
   * @return the phone number of the customer
   */
  public String getPhone()
  {
    return phone;
  }

  /**
   * Sets the customer's phone number with validation
   *
   * @param phone the phone number to set
   * @throws IllegalArgumentException if the phone number is null, not 8 digits long, or contains non-digit characters
   */
  public void setPhone(String phone)
  {
    if (phone == null)
    {
      throw new IllegalArgumentException("Phone must not be null");
    }
    if (phone.length() != 8)
    {
      throw new IllegalArgumentException("Phone number must be 8 digits long");
    }
    for (int i = 0; i < 8; i++)
    {
      if (phone.charAt(i) != '0' && phone.charAt(i) != '1'
          && phone.charAt(i) != '2' && phone.charAt(i) != '3'
          && phone.charAt(i) != '4' && phone.charAt(i) != '5'
          && phone.charAt(i) != '6' && phone.charAt(i) != '7'
          && phone.charAt(i) != '8' && phone.charAt(i) != '9')
      {
        throw new IllegalArgumentException(
            "Phone number must contain only digits");
      }
    }
    this.phone = phone;
  }

  /**
   * Returns the customer's email address
   *
   * @return the email address of the customer
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Sets the customer's email address with basic validation
   *
   * @param email the email address to set
   * @throws IllegalArgumentException if the email is null or does not contain '@' and '.'
   */
  public void setEmail(String email)
  {
    if (email == null)
    {
      throw new IllegalArgumentException("Email must not be null");
    }
    if (!email.contains("@") || !email.contains("."))
    {
      throw new IllegalArgumentException("Email must contain '@' and '.'");
    }
    this.email = email;
  }

  /**
   * Provides a String representation of the customer's details
   *
   * @return a formatted String with customer's first name, last name, phone, and email
   */
  public String toString()
  {
    return "First name: " + firstName + ", Last name: " + lastName + ", Phone: "
        + phone + ", Email: " + email;
  }

  /**
   * Compares this customer with another object for equality
   *
   * @param obj the object to compare with this customer
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Customer other = (Customer) obj;
    return firstName.equals(other.getFirstName()) && lastName.equals(
        other.getLastName()) && phone.equals(other.getPhone()) && email.equals(
        other.getEmail());
  }
}
package model;

import model.Pets.Pet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A class that manages the purchase of a pet
 *
 * @author Bianca Buzdugan
 * @author Teodora Stoicescu
 */
public class Purchase implements Serializable
{
  private static final long serialVersionUID = 6525559988221736930L;

  private int discount;
  private LocalTime time;
  private MyDate date;
  private Customer customer;
  private Pet pet;

  /**
   * A constructor that makes a new purchase for a pet using information about the customer, taking the date and time.
   * If no time is provided (time is null), defaults to 09:00.
   *
   * @param customer The customer that buys the pet
   * @param pet      The pet that is bought
   * @param date     The date of the purchase
   * @param time     The time of purchase
   * @param discount The discount of the purchase
   */
  public Purchase(Customer customer, Pet pet, MyDate date, LocalTime time,
      int discount)
  {
    this.discount = discount;
    this.pet = pet;
    this.date = date;
    this.time = time != null ? time : LocalTime.of(9, 0);
    this.customer = customer;
  }

  /**
   * Returns the percentage discount applied to this purchase.
   *
   * @return The discount percentage as an integer
   */
  public int getDiscount()
  {
    return discount;
  }

  /**
   * Sets the discount for this purchase.
   *
   * @param discount the discount to apply
   */
  public void setDiscount(int discount)
  {
    this.discount = discount;
  }

  /**
   * Returns the customer who made the purchase.
   *
   * @return The Customer object representing the buyer
   */
  public Customer getCustomer()
  {
    return customer;
  }

  /**
   * Sets the customer associated with this purchase.
   *
   * @param customer The new Customer object to set
   */
  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  /**
   * Returns the pet that was purchased.
   *
   * @return The Pet object that was bought
   */
  public Pet getPet()
  {
    return pet;
  }

  /**
   * Sets the pet associated with this purchase.
   *
   * @param pet The new Pet object to set
   */
  public void setPet(Pet pet)
  {
    this.pet = pet;
  }

  /**
   * Returns the date when the purchase was made.
   *
   * @return The MyDate object representing the purchase date
   */
  public MyDate getDateOfPurchase()
  {
    return date;
  }

  /**
   * Sets the date of the purchase.
   *
   * @param date The new MyDate object representing the purchase date
   */
  public void setDateOfPurchase(MyDate date)
  {
    this.date = date;
  }

  /**
   * Returns the time when the purchase was made.
   *
   * @return The LocalTime object representing the purchase time
   */
  public LocalTime getTimeOfPurchase()
  {
    return time;
  }

  /**
   * Sets the time of the purchase.
   *
   * @param time The new LocalTime object representing the purchase time
   */
  public void setTimeOfPurchase(LocalTime time)
  {
    this.time = time;
  }

  /**
   * Returns a string representation of the purchase, including customer details,
   * pet information, discount, date, and time. Time is formatted as HH:MM.
   *
   * @return A formatted String containing all purchase details
   */
  public String toString()
  {
    LocalTime currentTime = getTimeOfPurchase();
    String timeStr = currentTime != null ?
        String.format("%02d:%02d", currentTime.getHour(),
            currentTime.getMinute()) :
        "00:00";
    return "Customer: " + customer + ", Pet: " + pet + ", Discount: " + discount
        + "%, Date: " + date + ", Time: " + timeStr;
  }

  /**
   * Compares this purchase with another object for equality.
   *
   * @param obj The object to compare with this purchase
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Purchase other = (Purchase) obj;
    LocalTime thisTime = getTimeOfPurchase();
    LocalTime otherTime = other.getTimeOfPurchase();
    return other.getDiscount() == discount && other.getDateOfPurchase()
        .equals(date) && (Objects.equals(thisTime, otherTime))
        && other.customer.equals(customer) && other.pet.equals(pet);
  }
}

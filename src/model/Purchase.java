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
   * A constructor that makes a new purchase for a pet using information about the customer, taking the date and time
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
   * Returns the applied discount
   *
   * @return the discount
   */
  public int getDiscount()
  {
    return discount;
  }

  /**
   * Sets the discount that will be applied
   *
   * @param discount the discount to set
   */
  public void setDiscount(int discount)
  {
    this.discount = discount;
  }

  /**
   * Returns the buying customer
   *
   * @return the customer
   */
  public Customer getCustomer()
  {
    return customer;
  }

  /**
   * Sets the buying customer
   *
   * @param customer the customer that will buy the pet
   */
  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  /**
   * Returns the bought pet
   *
   * @return the pet
   */
  public Pet getPet()
  {
    return pet;
  }

  /**
   * Sets the pet that will be bought
   *
   * @param pet the pet that will be bought
   */
  public void setPet(Pet pet)
  {
    this.pet = pet;
  }

  /**
   * Returns the purchase date
   *
   * @return the date of purchase
   */
  public MyDate getDateOfPurchase()
  {
    return date;
  }

  /**
   * Sets the date of the purchase
   *
   * @param date the date of the purchase
   */
  public void setDateOfPurchase(MyDate date)
  {
    this.date = date;
  }

  /**
   * Returns the time of purchase
   *
   * @return the time of purchase
   */
  public LocalTime getTimeOfPurchase()
  {
    return time;
  }

  /**
   * Sets the time of the purchase
   *
   * @param time the time of the purchase
   */
  public void setTimeOfPurchase(LocalTime time)
  {
    this.time = time;
  }

  /**
   *
   * @return
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
   *
   * @param obj
   * @return
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
        .equals(date) && (Objects.equals(thisTime, otherTime)) && other.customer.equals(customer)
        && other.pet.equals(pet);
  }
}

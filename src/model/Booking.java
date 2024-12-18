package model;

import model.Pets.Pet;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * A class that manages pet boarding reservations
 *
 * @author Teodora Stoicescu
 */
public class Booking implements Serializable
{
  private static final long serialVersionUID = 5898788688332424564L;
  private Pet petInfo;
  private Customer customer;
  private MyDate startDate;
  private MyDate endDate;
  private int pricePerDay;
  private Room room;

  /**
   * A constructor that initializes a Booking object
   *
   * @param customer  The customer making the booking
   * @param petInfo   The pet being booked for kennel
   * @param startDate The start date of the booking
   * @param endDate   The end date of the booking
   */
  public Booking(Customer customer, Pet petInfo, MyDate startDate,
      MyDate endDate)
  {
    this.customer = customer;
    this.petInfo = petInfo;
    this.startDate = startDate;
    this.endDate = endDate;
    this.pricePerDay = 20;
    this.room = null;
  }

  /**
   * Returns the start date of the booking
   *
   * @return the startDate of the booking
   */
  public MyDate getStartDate()
  {
    return startDate;
  }

  /**
   * Updates the start date of the booking
   *
   * @param startDate The start date of the booking
   */
  public void setStartDate(MyDate startDate)
  {
    this.startDate = startDate;
  }

  /**
   * Returns the end date of the booking
   *
   * @return the endDate of the booking
   */
  public MyDate getEndDate()
  {
    return endDate;
  }

  /**
   * Updates the end date of the booking
   *
   * @param endDate The end date of the booking
   */
  public void setEndDate(MyDate endDate)
  {
    this.endDate = endDate;
  }

  /**
   * Returns the customer associated with this booking
   *
   * @return The Customer object for this booking
   */
  public Customer getCustomer()
  {
    return customer;
  }

  /**
   * Updates the customer associated with this booking
   *
   * @param customer The new Customer object to be set for this booking
   */
  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  /**
   * Returns the pet information as a string representation
   *
   * @return a String description of the pet in this booking
   */
  public String getPetInfo()
  {
    return petInfo.toString();
  }

  /**
   * Updates the pet information for this booking
   *
   * @param petInfo the new Pet object to be set for this booking
   */
  public void setPetInfo(Pet petInfo)
  {
    this.petInfo = petInfo;
  }

  /**
   * Calculates the total price for the entire booking period
   *
   * @return the total price calculated by multiplying the price per day with the booking duration
   */
  public double getPrice()
  {
    return pricePerDay * getDuration();
  }

  /**
   * Returns the daily cost for the booking
   *
   * @return the price charged per day
   */
  public int getPricePerDay()
  {
    return pricePerDay;
  }

  /**
   * Returns the end date of the booking as a formatted string
   *
   * @return the end date converted to a string
   */
  public String getEndDateString()
  {
    return getEndDate().toString();
  }

  /**
   * Returns the first name of the customer who made the booking
   *
   * @return the customer's first name
   */
  public String getCustomerFirstName()
  {
    return getCustomer().getFirstName();
  }

  /**
   * Returns the last name of the customer who made the booking
   *
   * @return the customer's last name
   */
  public String getCustomerLastName()
  {
    return getCustomer().getLastName();
  }

  /**
   * Determines the type of pet in this booking
   *
   * @return the simple class name of the pet, or "Unknown" if pet is null
   */
  public String getPetType()
  {
    return petInfo != null ? petInfo.getClass().getSimpleName() : "Unknown";
  }

  /**
   * Returns the name of the pet in this booking
   *
   * @return the pet's name, or "Unknown" if pet is null
   */
  public String getPetName()
  {
    return petInfo != null ? petInfo.getName() : "Unknown";
  }

  /**
   * Returns the start date of the booking as a formatted String
   *
   * @return the start date converted to a string
   */
  public String getStartDateString()
  {
    return getStartDate().toString();
  }

  /**
   * Calculates the duration of the booking in days
   *
   * @return the number of days between the start and end dates, with a minimum of 1 day
   */
  public double getDuration()
  {
    try
    {
      LocalDate date1 = LocalDate.of(startDate.getYear(), startDate.getMonth(),
          startDate.getDay());
      LocalDate date2 = LocalDate.of(endDate.getYear(), endDate.getMonth(),
          endDate.getDay());
      return Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }
    catch (DateTimeException e)
    {
      return 1;
    }
  }

  /**
   * Assigns a room to this booking
   *
   * @param room the room to assign
   */
  public void setRoom(Room room) {
    this.room = room;
  }

  /**
   * Gets the room assigned to this booking
   *
   * @return the assigned room, or null if no room is assigned
   */
  public Room getRoom() {
    return room;
  }

  /**
   * Checks if the assigned room is available during the specified date range.
   * If no room is assigned, returns true since it can still be booked.
   *
   * @param startDate the proposed start date to check availability
   * @param endDate   the proposed end date to check availability
   * @return true if the room is available or no room is assigned, false if the room is occupied
   */
  public boolean isAvailable(MyDate startDate, MyDate endDate) {
    if (room == null) {
      return true;  // No room assigned yet, so it's available for booking
    }
    return room.isAvailableDuring(startDate, endDate);
  }

  /**
   * Updates the booking with new customer, pet, start, and end date information
   *
   * @param customer  the new customer for the booking
   * @param petInfo   the new pet for the booking
   * @param startDate the new start date for the booking
   * @param endDate   the new end date for the booking
   */
  public void bookTo(Customer customer, Pet petInfo, MyDate startDate,
      MyDate endDate)
  {
    this.customer = customer;
    this.petInfo = petInfo;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Provides a String representation of the booking
   *
   * @return a formatted String with customer name, pet name, and booking dates
   */
  public String toString()
  {
    String customerName = customer != null ?
        customer.getFirstName() + " " + customer.getLastName() :
        "Unknown Customer";

    String petName = petInfo != null ? petInfo.getName() : "Unknown Pet";

    return "Customer: " + customerName + ", Pet: " + petName + ", Booked from: "
        + startDate + " Until: " + endDate;
  }

  /**
   * Compares this booking with another object for equality
   *
   * @param obj the object to compare with this booking
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    Booking other = (Booking) obj;
    return (Objects.equals(petInfo, other.petInfo)) && customer.equals(
        other.customer) && startDate.equals(other.startDate) && endDate.equals(
        other.endDate) && pricePerDay == other.pricePerDay;
  }
}

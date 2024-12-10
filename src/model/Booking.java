package model;

import model.Pets.Pet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking
{
  private Pet petInfo;
  private Customer customer;
  private MyDate startDate;
  private MyDate endDate;
  private int pricePerDay;
  private int numberOfRooms;
  private Room[] rooms;

  public Booking(int numberOfRooms)
  {
    this.customer = null;
    this.petInfo = null;
    this.startDate = null;
    this.endDate = null;
    this.pricePerDay = 20;
    this.numberOfRooms = numberOfRooms;
    rooms = new Room[numberOfRooms];
    for (int i = 0; i < numberOfRooms; i++) {
      rooms[i] = new Room();
    }
  }

  public MyDate getStartDate()
  {
    return startDate;
  }

  public void setStartDate(MyDate startDate)
  {
    this.startDate = startDate;
  }

  public MyDate getEndDate()
  {
    return endDate;
  }

  public void setEndDate(MyDate endDate)
  {
    this.endDate = endDate;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  public long getDuration()
  {
    LocalDate date1 = LocalDate.of(startDate.getYear(), startDate.getMonth(),
        startDate.getDay());
    LocalDate date2 = LocalDate.of(endDate.getYear(), endDate.getMonth(),
        endDate.getDay());

    return ChronoUnit.DAYS.between(date1, date2);
  }

  public String getPetInfo()
  {
    return petInfo.toString();
  }

  public long getPrice()
  {
    return pricePerDay * getDuration();
  }

  public boolean isAvailable(MyDate startDate, MyDate endDate)
  {
    for (int i = 0; i < numberOfRooms; i++)
    {
      if (rooms[i].isAvailableDuring(startDate, endDate))
        return true;
    }
    return false;
    //we need to go through each one of the 10 rooms in the kennel
    //we need to check the availability during the specified time
    //we need to return false if we find everything occupied
    //we need to keep searching through all 10 rooms even if the first ones are occupied
    //we need to return true only if we found a free room and mark the room as occupied
  }

  public void bookTo(Customer customer, Pet petInfo, MyDate startDate,
      MyDate endDate)
  {
    for (int i = 0; i < numberOfRooms; i++)
    {
      if (rooms[i].isAvailableDuring(startDate, endDate)
          && this.customer == null)
      {
        this.customer = customer;
        this.petInfo = petInfo;
      }
    }
  }

  public String toString()
  {
    return customer.getFirstName() + " " + customer.getLastName() + " Booked from: " + startDate + " Until: "
        + endDate + " For: " + petInfo.getName();
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    Booking other = (Booking) obj;
    return petInfo.equals(other.petInfo) && customer.equals(other.customer)
        && startDate.equals(other.startDate) && endDate.equals(other.endDate)
        && pricePerDay == other.pricePerDay;
  }
}

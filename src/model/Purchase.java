package model;

import model.Pets.Pet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Random;

public class Purchase implements Serializable
{
  private static final long serialVersionUID = 6525559988221736930L;

  private int discount;
  private LocalTime time;
  private MyDate date;
  private Customer customer;
  private Pet pet;

  public Purchase(Customer customer, Pet pet, MyDate date, LocalTime time,
      int discount)
  {
    this.discount = discount;
    this.pet = pet;
    this.date = date;
    this.time = time != null ? time : LocalTime.of(9, 0);
    this.customer = customer;
  }

  private MyDate generateRandomDateAfter()
  {
    Random rand = new java.util.Random();
    int maxDaysInMonth = 31;
    int randomDay = rand.nextInt(maxDaysInMonth) + 1;
    int randomMonth = rand.nextInt(8) + 5;
    return new MyDate(randomDay, randomMonth, 2024);
  }

  public int getDiscount()
  {
    return discount;
  }

  public void setDiscount(int discount)
  {
    this.discount = discount;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public Pet getPet()
  {
    return pet;
  }

  public MyDate getDateOfPurchase()
  {
    return date;
  }

  public LocalTime getTimeOfPurchase()
  {
    return time;
  }

  public void setDateOfPurchase(MyDate date)
  {
    this.date = date;
  }

  public void setTimeOfPurchase(LocalTime time)
  {
    this.time = time;
  }

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
        .equals(date) && (thisTime == null ?
        otherTime == null :
        thisTime.equals(otherTime)) && other.customer.equals(customer)
        && other.pet.equals(pet);
  }

  private void writeObject(ObjectOutputStream out) throws IOException
  {
    out.defaultWriteObject();
    if (time != null)
    {
      out.writeInt(time.getHour());
      out.writeInt(time.getMinute());
    }
    else
    {
      out.writeInt(-1);
      out.writeInt(-1);
    }
  }

  private void readObject(ObjectInputStream in)
      throws IOException, ClassNotFoundException
  {
    in.defaultReadObject();
    int hour = in.readInt();
    int minute = in.readInt();
    if (hour >= 0 && minute >= 0)
    {
      this.time = LocalTime.of(hour, minute);
    }
  }
}

package model;

import model.Pets.Pet;

import java.io.Serializable;

public class Purchase implements Serializable
{
  private int discount;
  private MyDate date;
  private Customer customer;
  private Pet pet;

  public Purchase(Customer customer, Pet pet, int discount)
  {
    this.discount = discount;
    this.pet = pet;
    this.date = MyDate.today();
    this.customer = customer;
  }

  public int getDiscount()
  {
    return discount;
  }

  public void setDiscount(int discount)
  {
    this.discount = discount;

  }

  public MyDate getDateOfPurchase()
  {
    return date;
  }

  public void setDateOfPurchase(MyDate date)
  {
    this.date = date;
  }

  public String toString()
  {
    return "Customer: " + customer + ", Pet: " + pet + ", Discount: " + discount
        + "%" + " ,Date: " + date;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Purchase other = (Purchase) obj;
    return other.getDiscount() == discount && other.getDateOfPurchase()
        .equals(date) && other.customer.equals(customer) && other.pet.equals(
        pet);
  }
}

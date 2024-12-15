package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList implements Serializable
{   private static final long serialVersionUID = -8680388145644152994L;
  private ArrayList<Customer> customers;

  public CustomerList()
  {
    customers = new ArrayList<Customer>();
  }

  public void addCustomer(Customer customer)
  {
    customers.add(customer);
  }

  public void removeCustomer(Customer customer)
  {
    customers.remove(customer);
  }

  public Customer get(int index)
  {
    if(index<customers.size())
    {
      return customers.get(index);
    }
    else
    {
      return null;
    }
  }

  public int size()
  {
    return customers.size();
  }

  public Customer getCustomerByPhone(String phone){
    for (Customer customer : customers)
    {
      if (customer.getPhone().equals(phone))
      {
        return customer;
      }
    }
    return null;
  }

  public String toString()
  {
    String result = "";
    for (Customer customer : customers)
    {
      result += customer + "\n";
    }
    return result;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    CustomerList other = (CustomerList) obj;
    return customers.equals(other.customers);
  }

  public void add(Customer customer)
  {
    customers.add(customer);
  }

  public void remove(Customer customer)
  {
    customers.remove(customer);
  }
}

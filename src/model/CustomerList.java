package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Customer objects.
 *
 * @author Jan Lewek
 */
public class CustomerList implements Serializable
{
  private static final long serialVersionUID = -8680388145644152994L;
  private ArrayList<Customer> customers;

  /**
   * No-argument constructor initializing the CustomerList.
   */
  public CustomerList()
  {
    customers = new ArrayList<Customer>();
  }

  /**
   * Gets a Customer object from position index from the list.
   *
   * @param index the position in the list of the Customer object
   * @return the Customer at index if one exists, else null
   */
  public Customer get(int index)
  {
    if (index < customers.size())
    {
      return customers.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * Gets how many Customers objects are in the list.
   *
   * @return the number of Customer objects in the list
   */
  public int size()
  {
    return customers.size();
  }

  /**
   * Adds a Customer to the list.
   *
   * @param customer the Customer to add to the list
   */
  public void add(Customer customer)
  {
    customers.add(customer);
  }

  /**
   * Removes a Customer from the list.
   *
   * @param customer the Customer that will be removed from the list
   */
  public void remove(Customer customer)
  {
    customers.remove(customer);
  }

  /**
   * Gets a String representation of the CustomerList.
   *
   * @return a String containing information about all Customer objects in the list
   */
  public String toString()
  {
    String result = "";
    for (Customer customer : customers)
    {
      result += customer + "\n";
    }
    return result;
  }

  /**
   * Compares this CustomerList with another object for equality.
   *
   * @param obj The object to compare with this CustomerList
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    CustomerList other = (CustomerList) obj;
    return customers.equals(other.customers);
  }
}

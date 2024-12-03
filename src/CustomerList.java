import java.util.ArrayList;

public class CustomerList
{
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

  public Customer getCustomer(String phone){
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
}

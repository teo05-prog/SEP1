package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ModelManager
{
  private String fileName;

  public ModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  public CustomerList getAllCustomers()
  {
    CustomerList allCustomers = new CustomerList();

    try
    {
      allCustomers = (CustomerList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allCustomers;
  }

  public KennelList getAllBookings()
  {
    KennelList allBookings = new KennelList();

    try
    {
      allBookings = (KennelList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allBookings;
  }

  public void saveCustomers(CustomerList customers)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, customers);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public void manageCustomers(String firstName, String lastName, String phone, String email)
  {
    CustomerList allCustomers = getAllCustomers();

    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName))
      {
        customer.setPhone(phone);
        customer.setEmail(email);
      }
    }

    saveCustomers(allCustomers);
  }
}

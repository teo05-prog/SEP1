package utils;

import model.Customer;
import model.CustomerList;
import model.*;
import model.Pets.Pet;
import model.Pets.PetList;

import java.io.*;

public class FileReader
{
  public static void main(String[] args)
  {
    // Read Customers
    try
    {
      FileInputStream customersFileIn = new FileInputStream("customers.bin");
      ObjectInputStream readCustomers = new ObjectInputStream(customersFileIn);
      try
      {
        CustomerList customerList = (CustomerList) readCustomers.readObject();
        for(int i = 0; i < customerList.size(); i++)
        {
          System.out.println(customerList.get(i));
        }
      }
      catch (EOFException eof)
      {
        System.out.println("End of file");
      }
      readCustomers.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }

    // Read Pets
    try
    {
      FileInputStream petsFileIn = new FileInputStream("pets.bin");
      ObjectInputStream readPets = new ObjectInputStream(petsFileIn);
      try
      {
        PetList petList = (PetList) readPets.readObject();
        for(int i = 0; i < petList.size(); i++)
        {
          System.out.println(petList.get(i));
        }
      }
      catch (EOFException eof)
      {
        System.out.println("End of file");
      }
      readPets.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }

    // Read Purchases
    try
    {
      FileInputStream purchasesFileIn = new FileInputStream("purchases.bin");
      ObjectInputStream readPurchases = new ObjectInputStream(purchasesFileIn);
      while (true)
      {
        try
        {
          Purchase purchase = (Purchase) readPurchases.readObject();
          System.out.println(purchase);
        }
        catch (EOFException eof)
        {
          System.out.println("End of file");
          break;
        }
      }
      readPurchases.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }

    // Read Bookings
    try
    {
      FileInputStream bookingsFileIn = new FileInputStream("bookings.bin");
      ObjectInputStream readBookings = new ObjectInputStream(bookingsFileIn);
      while (true)
      {
        try
        {
          Booking booking = (Booking) readBookings.readObject();
          System.out.println(booking);
        }
        catch (EOFException eof)
        {
          System.out.println("End of file");
          break;
        }
      }
      readBookings.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
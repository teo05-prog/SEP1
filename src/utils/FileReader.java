package utils;

import model.Customer;

import model.*;
import model.Pets.Pet;

import java.io.*;

public class FileReader
{
  public static void main(String[] args)
  {
    try
    {
      FileInputStream customersFileIn = new FileInputStream("customers.bin");
      ObjectInputStream readCustomers = new ObjectInputStream(customersFileIn);
      while (true)
      {
        try
        {
          Customer customer = (Customer) readCustomers.readObject();
          System.out.println(customer);
        }
        catch (EOFException eof)
        {
          System.out.println("End of file");
          break;
        }
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

    try
    {
      FileInputStream petsFileIn = new FileInputStream("pets.bin");
      ObjectInputStream readPets = new ObjectInputStream(petsFileIn);
      while (true)
      {
        try
        {
          Pet pet = (Pet) readPets.readObject();
          System.out.println(pet);
        }
        catch (EOFException eof)
        {
          System.out.println("End of file");
          break;
        }
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


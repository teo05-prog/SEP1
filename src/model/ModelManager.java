package model;

import model.Pets.*;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ModelManager
{
  private String fileName;
  private double price;

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

  public void manageCustomers(String firstName, String lastName, String phone,
      String email)
  {
    CustomerList allCustomers = getAllCustomers();

    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (customer.getFirstName().equals(firstName) && customer.getLastName()
          .equals(lastName))
      {
        customer.setPhone(phone);
        customer.setEmail(email);
      }
    }
    saveCustomers(allCustomers);
  }

  public PetList getAllDogs(PetList petList)
  {
    PetList dogs = new PetList();

    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.getPets(i);
        if (pet.getClass() == Dog.class)
        {
          dogs.addPet(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return dogs;
  }

  public PetList getAllCats(PetList petList)
  {
    PetList cats = new PetList();

    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.getPets(i);
        if (pet.getClass() == Cat.class)
        {
          cats.addPet(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return cats;
  }

  public PetList getAllBirds(PetList petList)
  {
    PetList birds = new PetList();

    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.getPets(i);
        if (pet.getClass() == Bird.class)
        {
          birds.addPet(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return birds;
  }

  public PetList getAllFish(PetList petList)
  {
    PetList fish = new PetList();

    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.getPets(i);
        if (pet.getClass() == Fish.class)
        {
          fish.addPet(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return fish;
  }

  public PetList getAllRodents(PetList petList)
  {
    PetList rodents = new PetList();

    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.getPets(i);
        if (pet.getClass() == Rodent.class)
        {
          rodents.addPet(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return rodents;
  }

  public PetList getAllVarious(PetList petList)
  {
    PetList various = new PetList();

    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.getPets(i);
        if (pet.getClass() == Various.class)
        {
          various.addPet(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return various;
  }

  public void updatePrice(double newPrice)
  {
    this.price = newPrice;
    KennelList currentBookings = getAllBookings();
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, currentBookings);
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
}

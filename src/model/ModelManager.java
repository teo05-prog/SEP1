package model;

import model.Pets.*;
import utils.MyFileHandler;
import view.main.MainViewController;

import java.io.*;
import java.util.ArrayList;

public class ModelManager
{
  private String customersFileName;
  private String petsFileName;
  private String kennelFileName;
  private String purchasesFileName;
  private double price;

  private MainViewController mainViewController;

  public ModelManager(String customersFileName, String petsFileName,
      String kennelFileName, String purchasesFileName)
  {
    this.customersFileName = customersFileName;
    this.petsFileName = petsFileName;
    this.kennelFileName = kennelFileName;
    this.purchasesFileName = purchasesFileName;
  }

  public CustomerList getAllCustomers()
  {
    CustomerList allCustomers = new CustomerList();

    try
    {
      ArrayList<Object> objects = new ArrayList<>();
      ObjectInputStream readFromFile = null;

      try
      {
        FileInputStream fileInStream = new FileInputStream(customersFileName);
        readFromFile = new ObjectInputStream(fileInStream);

        while (true)
        {
          try
          {
            Object obj = readFromFile.readObject();
            if (obj instanceof CustomerList)
            {
              return (CustomerList) obj;
            }
            else if (obj instanceof Customer)
            {
              allCustomers.add((Customer) obj);
            }
          }
          catch (EOFException eof)
          {
            break;
          }
        }
      }
      finally
      {
        if (readFromFile != null)
        {
          readFromFile.close();
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found - Creating new customer list");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
    }

    return allCustomers;
  }

  public KennelList getAllBookings()
  {
    KennelList allBookings = new KennelList();

    try
    {
      allBookings = (KennelList) MyFileHandler.readFromBinaryFile(
          kennelFileName);
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

  public PurchaseList getAllPurchases()
  {
    PurchaseList allPurchases = new PurchaseList();

    try
    {
      allPurchases = (PurchaseList) MyFileHandler.readFromBinaryFile(
          purchasesFileName);
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
    return allPurchases;
  }

  public void saveCustomers(CustomerList customers)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(customersFileName, customers);
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
      e.printStackTrace();
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

  public PetList getAllPets()
  {
    PetList allPets = new PetList();

    try
    {
      allPets = (PetList) MyFileHandler.readFromBinaryFile(petsFileName);
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
    return allPets;
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
      MyFileHandler.writeToBinaryFile(kennelFileName, currentBookings);
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

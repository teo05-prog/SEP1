package model;

import model.Pets.*;
import utils.MyFileHandler;

import java.io.*;
import java.util.ArrayList;

/**
 * The class handles all data management operations for the pet shop system.
 * It manages file operations for customers, pets, kennel bookings, and purchases.
 */
public class ModelManager
{
  private String customersFileName;
  private String petsFileName;
  private String kennelFileName;
  private String purchasesFileName;
  private double price;

  /**
   * Creates a new ModelManager with specified file paths for data storage.
   *
   * @param customersFileName The file path for storing customer data
   * @param petsFileName      The file path for storing pet data
   * @param kennelFileName    The file path for storing kennel booking data
   * @param purchasesFileName The file path for storing purchase records
   */
  public ModelManager(String customersFileName, String petsFileName,
      String kennelFileName, String purchasesFileName)
  {
    this.customersFileName = customersFileName;
    this.petsFileName = petsFileName;
    this.kennelFileName = kennelFileName;
    this.purchasesFileName = purchasesFileName;
  }

  /**
   * Retrieves all customers from the binary file.
   * If the file doesn't exist, creates a new empty customer list.
   *
   * @return A CustomerList object containing all stored customers
   */
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

  /**
   * Retrieves all kennel bookings from the binary file.
   * If the file doesn't exist, returns an empty booking list.
   *
   * @return A KennelList object containing all stored bookings
   */
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
      System.out.println("File not found: " + kennelFileName);
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
    return allBookings;
  }

  /**
   * Retrieves all purchases from the binary file.
   *
   * @return A PurchaseList object containing all stored purchases
   */
  public PurchaseList getAllPurchases()
  {
    PurchaseList allPurchases = new PurchaseList();
    try
    {
      Object obj = MyFileHandler.readFromBinaryFile(purchasesFileName);
      if (obj instanceof PurchaseList)
      {
        allPurchases = (PurchaseList) obj;
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found: " + purchasesFileName
          + ". Creating new purchase list.");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file: " + purchasesFileName);
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
    }
    return allPurchases;
  }

  /**
   * Retrieves all pets from the binary file.
   *
   * @return A PetList object containing all stored pets
   */
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

  /**
   * Filters the provided pet list to return only dogs.
   *
   * @param petList The complete list of pets to filter
   * @return A new PetList containing only Dog objects
   */
  public PetList getAllDogs(PetList petList)
  {
    PetList dogs = new PetList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.get(i);
        if (pet.getClass() == Dog.class)
        {
          dogs.add(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return dogs;
  }

  /**
   * Filters the provided pet list to return only cats.
   *
   * @param petList The complete list of pets to filter
   * @return A new PetList containing only Cat objects
   */
  public PetList getAllCats(PetList petList)
  {
    PetList cats = new PetList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.get(i);
        if (pet.getClass() == Cat.class)
        {
          cats.add(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return cats;
  }

  /**
   * Filters the provided pet list to return only birds.
   *
   * @param petList The complete list of pets to filter
   * @return A new PetList containing only Bird objects
   */
  public PetList getAllBirds(PetList petList)
  {
    PetList birds = new PetList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.get(i);
        if (pet.getClass() == Bird.class)
        {
          birds.add(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return birds;
  }

  /**
   * Filters the provided pet list to return only fish.
   *
   * @param petList The complete list of pets to filter
   * @return A new PetList containing only Fish objects
   */
  public PetList getAllFish(PetList petList)
  {
    PetList fish = new PetList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.get(i);
        if (pet.getClass() == Fish.class)
        {
          fish.add(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return fish;
  }

  /**
   * Filters the provided pet list to return only rodents.
   *
   * @param petList The complete list of pets to filter
   * @return A new PetList containing only Rodent objects
   */
  public PetList getAllRodents(PetList petList)
  {
    PetList rodents = new PetList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.get(i);
        if (pet.getClass() == Rodent.class)
        {
          rodents.add(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return rodents;
  }

  /**
   * Filters the provided pet list to return only various pets.
   *
   * @param petList The complete list of pets to filter
   * @return A new PetList containing only Various pet objects
   */
  public PetList getAllVarious(PetList petList)
  {
    PetList various = new PetList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        Pet pet = petList.get(i);
        if (pet.getClass() == Various.class)
        {
          various.add(pet);
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
    return various;
  }

  /**
   * Updates the price setting and saves it with the current bookings.
   *
   * @param newPrice The new price to be set for the kennel
   */
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

  /**
   * Saves the provided CustomerList to the binary file.
   *
   * @param customers The CustomerList to be saved
   */
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

  /**
   * Saves the provided pet list to the binary file.
   *
   * @param pets The PetList to be saved
   */
  public void savePets(PetList pets)
  {
    try
    {
      MyFileHandler.writeToBinaryFile("pets.bin", pets);
    }
    catch (Exception e)
    {
      System.out.println("Error saving pets: " + e.getMessage());
    }
  }

  /**
   * Saves the provided purchase list to the binary file.
   *
   * @param purchases The PurchaseList to be saved
   */
  public void savePurchases(PurchaseList purchases)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(purchasesFileName, purchases);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found: " + purchasesFileName);
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file: " + purchasesFileName);
      e.printStackTrace();
    }
  }
}

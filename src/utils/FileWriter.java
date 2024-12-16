package utils;

import model.*;
import model.Pets.*;

import java.io.*;

public class FileWriter
{
  public static void main(String[] args)
  {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("customers.bin")))
    {
      CustomerList customerList = new CustomerList();
      Customer[] customers = new Customer[10];

      customers[0] = new Customer("John", "Doe", "12345678",
          "john.doe@mail.com");
      customers[1] = new Customer("Jane", "Smith", "87654321",
          "jane.smith@mail.com");
      customers[2] = new Customer("James", "Brown", "23456789",
          "james.brown@mail.com");
      customers[3] = new Customer("Emily", "Johnson", "98765432",
          "emily.johnson@mail.com");
      customers[4] = new Customer("Michael", "Williams", "34567890",
          "michael.williams@mail.com");
      customers[5] = new Customer("Sarah", "Taylor", "45678901",
          "sarah.taylor@mail.com");
      customers[6] = new Customer("David", "Miller", "56789012",
          "david.miller@mail.com");
      customers[7] = new Customer("Emma", "Davis", "67890123",
          "emma.davis@mail.com");
      customers[8] = new Customer("Daniel", "Wilson", "78901234",
          "daniel.wilson@mail.com");
      customers[9] = new Customer("Olivia", "Martinez", "89012345",
          "olivia.martinez@mail.com");

      for (Customer customer : customers)
      {
        customerList.add(customer);
      }

      out.writeObject(customerList);
      System.out.println("Customers serialized successfully");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("pets.bin")))
    {
      PetList petList = new PetList();
      Pet[] pets = new Pet[20];

      pets[0] = new Dog("Buddy", 5, "Brown", 'm', "Friendly", 0,
          "Golden Retriever", "Golden Paws");
      pets[1] = new Dog("Daisy", 4, "White", 'f', "Cute", 0, "Poodle",
          "Poodle Palace");
      pets[2] = new Dog("Max", 6, "Black", 'm', "Loyal", 0, "Labrador",
          "Labrador Love");
      pets[3] = new Dog("Lucy", 3, "Brown", 'f', "Playful", 0, "Beagle",
          "Beagle Buddies");
      pets[4] = new Dog("Charlie", 7, "Black", 'm', "Energetic", 15000,
          "German Shepherd", "German Guard");
      pets[5] = new Cat("Whiskers", 3, "White", 'f', "Playful", 0, "Siamese",
          "Siamese Cats");
      pets[6] = new Cat("Mittens", 2, "Black", 'f', "Cuddly", 0, "Persian",
          "Persian Paws");
      pets[7] = new Cat("Tiger", 4, "Orange", 'm', "Curious", 0, "Savannah",
          "Savannah Breeders");
      pets[8] = new Cat("Smokey", 5, "Grey", 'm', "Lazy", 0, "Chartreux",
          "Chartreux Cats");
      pets[9] = new Cat("Luna", 1, "White", 'f', "Independent", 17500,
          "Ragdoll", "Ragdoll Royalty");
      pets[10] = new Bird("Polly", 2, "Green", 'f', "Talkative", 0, "Seeds",
          "Budgerigar");
      pets[11] = new Bird("Apollo", 2, "Gray", 'm', "eloquent", 0, "pistachios",
          "Parrot");
      pets[12] = new Bird("Zeus", 1, "Yellow", 'm', "Loud", 1600,
          "Sunflower seeds", "Budgerigar");
      pets[13] = new Fish("Bubbles", 1, "Orange", 'm', "Shy", 80, "Freshwater",
          false, "Goldfish");
      pets[14] = new Fish("Nemo", 1, "Orange", 'm', "Adventurous", 160,
          "Saltwater", true, "Clownfish");
      pets[15] = new Fish("Dory", 1, "Blue", 'f', "Forgetful", 450, "Saltwater",
          true, "Regal Tang");
      pets[16] = new Rodent("Nibbles", 1, "Grey", 'm', "Curious", 200, false,
          "Hamster");
      pets[17] = new Rodent("Squeaky", 1, "White", 'f', "Loud", 180, true,
          "Rat");
      pets[18] = new Rodent("Fluffy", 1, "Brown", 'f', "Soft", 220, false,
          "Chinchilla");
      pets[19] = new Various("Maturin", 100, "Green", 'm', "Wise", 500,
          "Turtle");

      for (Pet pet : pets)
      {
        petList.add(pet);
      }

      out.writeObject(petList);
      System.out.println("Pets serialized successfully");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("bookings.bin")))
    {
      KennelList kennelList = new KennelList();
      Customer[] customers = new Customer[10];
      Pet[] pets = new Pet[20];

      customers[0] = new Customer("John", "Doe", "12345678",
          "john.doe@mail.com");
      customers[1] = new Customer("Jane", "Smith", "87654321",
          "jane.smith@mail.com");
      customers[2] = new Customer("James", "Brown", "23456789",
          "james.brown@mail.com");
      customers[3] = new Customer("Emily", "Johnson", "98765432",
          "emily.johnson@mail.com");
      customers[4] = new Customer("Michael", "Williams", "34567890",
          "michael.williams@mail.com");
      customers[5] = new Customer("Sarah", "Taylor", "45678901",
          "sarah.taylor@mail.com");
      customers[6] = new Customer("David", "Miller", "56789012",
          "david.miller@mail.com");
      customers[7] = new Customer("Emma", "Davis", "67890123",
          "emma.davis@mail.com");
      customers[8] = new Customer("Daniel", "Wilson", "78901234",
          "daniel.wilson@mail.com");
      customers[9] = new Customer("Olivia", "Martinez", "89012345",
          "olivia.martinez@mail.com");

      pets[0] = new Dog("Buddy", 5, "Brown", 'm', "Friendly", 0,
          "Golden Retriever", "Golden Paws");
      pets[1] = new Dog("Daisy", 4, "White", 'f', "Cute", 0, "Poodle",
          "Poodle Palace");

      Booking[] bookings = new Booking[10];
      bookings[0] = new Booking(customers[0], pets[0], new MyDate(1, 10, 2024),
          new MyDate(10, 10, 2024));
      bookings[1] = new Booking(customers[1], pets[1], new MyDate(2, 12, 2024),
          new MyDate(24, 12, 2024));
      bookings[2] = new Booking(customers[2], pets[2], new MyDate(3, 1, 2025),
          new MyDate(10, 1, 2025));
      bookings[3] = new Booking(customers[3], pets[3], new MyDate(4, 2, 2025),
          new MyDate(14, 2, 2025));
      bookings[4] = new Booking(customers[4], pets[5], new MyDate(21, 11, 2024),
          new MyDate(30, 11, 2024));
      bookings[5] = new Booking(customers[5], pets[6], new MyDate(5, 3, 2025),
          new MyDate(15, 3, 2025));
      bookings[6] = new Booking(customers[6], pets[7], new MyDate(12, 12, 2024),
          new MyDate(19, 12, 2024));
      bookings[7] = new Booking(customers[7], pets[8], new MyDate(6, 4, 2025),
          new MyDate(16, 4, 2025));
      bookings[8] = new Booking(customers[8], pets[10],
          new MyDate(31, 12, 2024), new MyDate(7, 1, 2025));
      bookings[9] = new Booking(customers[9], pets[11], new MyDate(7, 1, 2025),
          new MyDate(25, 1, 2025));

      for (Booking booking : bookings)
      {
        kennelList.add(booking);
      }

      out.writeObject(kennelList);
      System.out.println("Bookings serialized successfully");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("purchases.bin")))

    {
      PurchaseList purchaseList = new PurchaseList();
      Customer[] customers = new Customer[10];
      Pet[] pets = new Pet[20];

      // Reuse the existing customers and pets arrays from previous serialization
      customers[0] = new Customer("John", "Doe", "12345678",
          "john.doe@mail.com");
      customers[1] = new Customer("Jane", "Smith", "87654321",
          "jane.smith@mail.com");
      customers[2] = new Customer("James", "Brown", "23456789",
          "james.brown@mail.com");
      customers[3] = new Customer("Emily", "Johnson", "98765432",
          "emily.johnson@mail.com");
      customers[4] = new Customer("Michael", "Williams", "34567890",
          "michael.williams@mail.com");
      customers[5] = new Customer("Sarah", "Taylor", "45678901",
          "sarah.taylor@mail.com");
      customers[6] = new Customer("David", "Miller", "56789012",
          "david.miller@mail.com");
      customers[7] = new Customer("Emma", "Davis", "67890123",
          "emma.davis@mail.com");
      customers[8] = new Customer("Daniel", "Wilson", "78901234",
          "daniel.wilson@mail.com");
      customers[9] = new Customer("Olivia", "Martinez", "89012345",
          "olivia.martinez@mail.com");

      pets[0] = new Dog("Buddy", 5, "Brown", 'm', "Friendly", 0,
          "Golden Retriever", "Golden Paws");
      pets[1] = new Dog("Daisy", 4, "White", 'f', "Cute", 0, "Poodle",
          "Poodle Palace");
      pets[2] = new Dog("Max", 6, "Black", 'm', "Loyal", 0, "Labrador",
          "Labrador Love");
      pets[3] = new Dog("Lucy", 3, "Brown", 'f', "Playful", 0, "Beagle",
          "Beagle Buddies");
      pets[4] = new Dog("Charlie", 7, "Black", 'm', "Energetic", 15000,
          "German Shepherd", "German Guard");
      pets[5] = new Cat("Whiskers", 3, "White", 'f', "Playful", 0, "Siamese",
          "Siamese Cats");
      pets[6] = new Cat("Mittens", 2, "Black", 'f', "Cuddly", 0, "Persian",
          "Persian Paws");
      pets[7] = new Cat("Tiger", 4, "Orange", 'm', "Curious", 0, "Savannah",
          "Savannah Breeders");
      pets[8] = new Cat("Smokey", 5, "Grey", 'm', "Lazy", 0, "Chartreux",
          "Chartreux Cats");
      pets[9] = new Cat("Luna", 1, "White", 'f', "Independent", 17500,
          "Ragdoll", "Ragdoll Royalty");
      pets[10] = new Bird("Polly", 2, "Green", 'f', "Talkative", 0, "Seeds",
          "Budgerigar");
      pets[11] = new Bird("Apollo", 2, "Gray", 'm', "eloquent", 0, "pistachios",
          "Parrot");
      pets[12] = new Bird("Zeus", 1, "Yellow", 'm', "Loud", 1600,
          "Sunflower seeds", "Budgerigar");

      Purchase[] purchases = new Purchase[10];
      purchases[0] = new Purchase(customers[0], pets[0], 0);
      purchases[1] = new Purchase(customers[1], pets[1], 0);
      purchases[2] = new Purchase(customers[2], pets[2], 0);
      purchases[3] = new Purchase(customers[3], pets[3], 0);
      purchases[4] = new Purchase(customers[4], pets[5], 10);
      purchases[5] = new Purchase(customers[5], pets[6], 15);
      purchases[6] = new Purchase(customers[6], pets[7], 0);
      purchases[7] = new Purchase(customers[7], pets[8], 0);
      purchases[8] = new Purchase(customers[8], pets[10], 30);
      purchases[9] = new Purchase(customers[9], pets[11], 0);

      for (Purchase purchase : purchases)
      {
        purchaseList.add(purchase);
      }

      out.writeObject(purchaseList);
      System.out.println("Purchases serialized successfully");
    }
    catch (Exception e)

    {
      e.printStackTrace();
    }

    System.out.println("Done writing");
  }
}
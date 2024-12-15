package utils;

import model.*;
import model.Pets.*;

import java.io.*;

public class FileWriter
{
  public static void main(String[] args){
    Customer customer1 = new Customer("John", "Doe", "12345678", "john.doe@mail.com");
    Customer customer2 = new Customer("Jane", "Smith", "87654321", "jane.smith@mail.com");
    Customer customer3 = new Customer("James", "Brown", "23456789", "james.brown@mail.com");
    Customer customer4 = new Customer("Emily", "Johnson", "98765432", "emily.johnson@mail.com");
    Customer customer5 = new Customer("Michael", "Williams", "34567890", "michael.williams@mail.com");
    Customer customer6 = new Customer("Sarah", "Taylor", "45678901", "sarah.taylor@mail.com");
    Customer customer7 = new Customer("David", "Miller", "56789012", "david.miller@mail.com");
    Customer customer8 = new Customer("Emma", "Davis", "67890123", "emma.davis@mail.com");
    Customer customer9 = new Customer("Daniel", "Wilson", "78901234", "daniel.wilson@mail.com");
    Customer customer10 = new Customer("Olivia", "Martinez", "89012345", "olivia.martinez@mail.com");

    Pet pet1 = new Dog("Buddy", 5, "Brown", 'm', "Friendly", 0, "Golden Retriever", "Golden Paws");
    Pet pet2 = new Dog("Daisy", 4, "White", 'f', "Cute", 0, "Poodle", "Poodle Palace");
    Pet pet3 = new Dog("Max", 6, "Black", 'm', "Loyal", 0, "Labrador", "Labrador Love");
    Pet pet4 = new Dog("Lucy", 3, "Brown", 'f', "Playful", 0, "Beagle", "Beagle Buddies");
    Pet pet5 = new Dog("Charlie", 7, "Black", 'm', "Energetic", 15000, "German Shepherd", "German Guard");
    Pet pet6 = new Cat("Whiskers", 3, "White", 'f', "Playful", 0, "Siamese", "Siamese Cats");
    Pet pet7 = new Cat("Mittens", 2, "Black", 'f', "Cuddly", 0, "Persian", "Persian Paws");
    Pet pet8 = new Cat("Tiger", 4, "Orange", 'm', "Curious", 0, "Savannah", "Savannah Breeders");
    Pet pet9 = new Cat("Smokey", 5, "Grey", 'm', "Lazy", 0, "Chartreux", "Chartreux Cats");
    Pet pet10 = new Cat("Luna", 1, "White", 'f', "Independent", 17500, "Ragdoll", "Ragdoll Royalty");
    Pet pet11 = new Bird("Polly", 2, "Green", 'f', "Talkative", 0, "Seeds", "Budgerigar");
    Pet pet12 = new Bird("Apollo", 2, "Gray", 'm', "eloquent", 0, "pistachios", "Parrot");
    Pet pet13 = new Bird("Zeus", 1, "Yellow", 'm', "Loud", 1600, "Sunflower seeds", "Budgerigar");
    Pet pet14 = new Fish("Bubbles", 1, "Orange", 'm', "Shy", 80, "Freshwater", false, "Goldfish");
    Pet pet15 = new Fish("Nemo", 1, "Orange", 'm', "Adventurous", 160, "Saltwater", true, "Clownfish");
    Pet pet16 = new Fish("Dory", 1, "Blue", 'f', "Forgetful", 450, "Saltwater", true, "Regal Tang");
    Pet pet17 = new Rodent("Nibbles", 1, "Grey", 'm', "Curious", 200, false, "Hamster");
    Pet pet18 = new Rodent("Squeaky", 1, "White", 'f', "Loud", 180, true, "Rat");
    Pet pet19 = new Rodent("Fluffy", 1, "Brown", 'f', "Soft", 220, false, "Chinchilla");
    Pet pet20 = new Various("Maturin", 100, "Green", 'm', "Wise", 500, "Turtle");

    try{FileOutputStream fileOut = new FileOutputStream("pets.bin");
      ObjectOutputStream write = new ObjectOutputStream(fileOut);
      /*
      write.writeObject(new Booking(customer1, pet1, new MyDate(1, 10, 2024), new MyDate(10, 10, 2024)));
      write.writeObject(new Booking(customer2, pet2, new MyDate(2, 12, 2024), new MyDate(24, 12, 2024)));
      write.writeObject(new Booking(customer3, pet3, new MyDate(3, 1, 2025), new MyDate(10, 1, 2025)));
      write.writeObject(new Booking(customer4, pet4, new MyDate(4, 2, 2025), new MyDate(14, 2, 2025)));
      write.writeObject(new Booking(customer5, pet6, new MyDate(21, 11, 2024), new MyDate(30, 11, 2024)));
      write.writeObject(new Booking(customer6, pet7, new MyDate(5, 3, 2025), new MyDate(15, 3, 2025)));
      write.writeObject(new Booking(customer7, pet8, new MyDate(12, 12, 2024), new MyDate(19, 12, 2024)));
      write.writeObject(new Booking(customer8, pet9, new MyDate(6, 4, 2025), new MyDate(16, 4, 2025)));
      write.writeObject(new Booking(customer9, pet11, new MyDate(31, 12, 2024), new MyDate(7, 1, 2025)));
      write.writeObject(new Booking(customer10, pet12, new MyDate(7, 1, 2025), new MyDate(25, 1, 2025)));
      */
      /*
      write.writeObject(pet1);
      write.writeObject(pet2);
      write.writeObject(pet3);
      write.writeObject(pet4);
      write.writeObject(pet5);
      write.writeObject(pet6);
      write.writeObject(pet7);
      write.writeObject(pet8);
      write.writeObject(pet9);
      write.writeObject(pet10);
      write.writeObject(pet11);
      write.writeObject(pet12);
      write.writeObject(pet13);
      write.writeObject(pet14);
      write.writeObject(pet15);
      write.writeObject(pet16);
      write.writeObject(pet17);
      write.writeObject(pet18);
      write.writeObject(pet19);
      write.writeObject(pet20);
      */
      //write.writeObject(new Purchase(customer1, pet1)); //bad purchase constructor :c

      write.close();
    }
    catch(FileNotFoundException e){
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch(IOException e){
      System.out.println("IO Error writing to file");
      e.printStackTrace();
      System.exit(1);
    }
    System.out.println("Done writing");
  }
}



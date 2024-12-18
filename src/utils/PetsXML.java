package utils;

import model.Pets.*;

import java.io.*;
import java.util.ArrayList;

public class PetsXML {
  private PetList petList;
  private ArrayList<Pet> pets;

  public PetsXML() {
    pets = new ArrayList<>();
    readPets();
    writePets();
  }

  public void readPets() {
    try (FileInputStream fileIn = new FileInputStream("pets.bin");
        ObjectInputStream read = new ObjectInputStream(fileIn)) {

      petList = (PetList) read.readObject();

      // Populate the pets ArrayList from petList
      for (int i = 0; i < petList.size(); i++) {
        pets.add(petList.get(i));
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void writePets() {
    try (FileOutputStream fileOut = new FileOutputStream("pets.xml");
        PrintWriter write = new PrintWriter(fileOut)) {

      write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      write.println("<pets>");
      for (Pet pet : pets)
      {
        if (pet.getPrice() != 0)
        {
          // Determine pet type and write XML accordingly
          String petType = determinePetType(pet);
          write.println("<pet type=\"" + petType + "\">");

          // Write common pet attributes
          write.println("<name>" + pet.getName() + "</name>");
          write.println("<age>" + pet.getAge() + "</age>");
          write.println("<gender>" + pet.getGender() + "</gender>");
          write.println("<colour>" + pet.getColour() + "</colour>");
          write.println("<comment>" + pet.getComment() + "</comment>");
          write.println("<price>" + pet.getPrice() + "</price>");

          // Write specific pet type attributes
          writeSpecificPetAttributes(write, pet);

          write.println("</pet>");
        }
      }

      write.println("</pets>");
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      System.exit(1);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private String determinePetType(Pet pet) {
    if (pet instanceof Bird) return "bird";
    if (pet instanceof Cat) return "cat";
    if (pet instanceof Dog) return "dog";
    if (pet instanceof Fish) return "fish";
    if (pet instanceof Rodent) return "rodent";
    if (pet instanceof Various) return "various";
    return "unknown";
  }

  private void writeSpecificPetAttributes(PrintWriter write, Pet pet) {
    if (pet instanceof Bird bird) {
      write.println("<preferredFood>" + bird.getPreferredFood() + "</preferredFood>");
      write.println("<specie>" + bird.getSpecie() + "</specie>");
    } else if (pet instanceof Cat cat) {
      write.println("<breed>" + cat.getBreed() + "</breed>");
      write.println("<breederName>" + cat.getBreederName() + "</breederName>");
    } else if (pet instanceof Dog dog) {
      write.println("<breed>" + dog.getBreed() + "</breed>");
      write.println("<breederName>" + dog.getBreederName() + "</breederName>");
    } else if (pet instanceof Fish fish) {
      write.println("<waterType>" + fish.getWater() + "</waterType>");
      write.println("<predators>" + fish.getPredators() + "</predators>");
      write.println("<specie>" + fish.getSpecie() + "</specie>");
    } else if (pet instanceof Rodent rodent) {
      write.println("<doesItBite>" + rodent.getDoesItBite() + "</doesItBite>");
      write.println("<specie>" + rodent.getSpecie() + "</specie>");
    } else if (pet instanceof Various various) {
      write.println("<specie>" + various.getSpecie() + "</specie>");
    }
  }

  public static void main(String[] args)
  {
    new PetsXML();
  }
}
package model.Pets;

import java.io.Serializable;

/**
 * Represents a Dog pet in the pet shop system.
 * Extends the Pet class with dog-specific attributes like breed and breeder name.
 *
 * @author Yelyzaveta Tkachenko
 */
public class Dog extends Pet implements Serializable
{
  private String breed;
  private String breederName;

  /**
   * Creates a new Dog with all attributes including price.
   *
   * @param name        The dog's name
   * @param age         The dog's age
   * @param colour      The dog's colour
   * @param gender      The dog's gender ('M' or 'F')
   * @param comment     Additional comments about the dog
   * @param price       The dog's price
   * @param breed       The dog's breed
   * @param breederName Name of the dog's breeder
   */
  public Dog(String name, int age, String colour, char gender, String comment,
      int price, String breed, String breederName)
  {
    super(name, age, colour, gender, comment, price);
    this.breed = breed;
    this.breederName = breederName;
  }

  /**
   * Creates a new Dog without specifying a price.
   *
   * @param name        The dog's name
   * @param age         The dog's age
   * @param colour      The dog's colour
   * @param gender      The dog's gender ('M' or 'F')
   * @param comment     Additional comments about the dog
   * @param breed       The dog's breed
   * @param breederName Name of the dog's breeder
   */
  public Dog(String name, int age, String colour, char gender, String comment,
      String breed, String breederName)
  {
    super(name, age, colour, gender, comment);
    this.breed = breed;
    this.breederName = breederName;
  }

  /**
   * Gets the dog's breed.
   *
   * @return The breed as a String
   */
  public String getBreed()
  {
    return breed;
  }

  /**
   * Gets the name of the dog's breeder.
   *
   * @return The breeder's name as a String
   */
  public String getBreederName()
  {
    return breederName;
  }

  /**
   * Sets the dog's breed.
   *
   * @param breed The new breed to set
   */
  public void setBreed(String breed)
  {
    this.breed = breed;
  }

  /**
   * Sets the name of the dog's breeder.
   *
   * @param breederName The new breeder name to set
   */
  public void setBreederName(String breederName)
  {
    this.breederName = breederName;
  }

  /**
   * Creates a copy of this Dog object.
   *
   * @return A new Dog object with the same attributes as this one
   */
  public Pet copy()
  {
    return new Dog(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), breed, breederName);
  }

  /**
   * Returns a string representation of the Dog.
   * Includes all attributes from the parent Pet class plus dog-specific details.
   *
   * @return A formatted string containing all dog information
   */
  public String toString()
  {
    return "Dog: " + super.toString() + ", breed: " + breed + ", breeder name: "
        + breederName;
  }

  /**
   * Checks if this Dog is equal to another object.
   *
   * @param obj The object to compare with this Dog
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Dog other = (Dog) obj;
    return other.getName().equals(super.getName())
        && other.getAge() == super.getAge() && other.getColour()
        .equals(super.getColour()) && other.getGender() == super.getGender()
        && other.getComment().equals(super.getComment())
        && other.getPrice() == super.getPrice() && other.getBreed()
        .equals(breed) && other.getBreederName().equals(breederName);
  }

  /**
   * Gets the type of pet.
   *
   * @return The string "Dog" identifying this pet type
   */
  public String getType()
  {
    return "Dog";
  }
}

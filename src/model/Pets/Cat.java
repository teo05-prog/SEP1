package model.Pets;

import java.io.Serializable;

/**
 * Represents a Cat pet in the pet shop system.
 * Extends the Pet class with cat-specific attributes like breed and breeder name.
 *
 * @author Yelyzaveta Tkachenko
 */
public class Cat extends Pet implements Serializable
{
  private String breed;
  private String breederName;

  /**
   * Creates a new Cat with all attributes including price.
   *
   * @param name        The cat's name
   * @param age         The cat's age
   * @param colour      The cat's colour
   * @param gender      The cat's gender ('M' or 'F')
   * @param comment     Additional comments about the cat
   * @param price       The cat's price
   * @param breed       The cat's breed
   * @param breederName Name of the cat's breeder
   */
  public Cat(String name, int age, String colour, char gender, String comment,
      int price, String breed, String breederName)
  {
    super(name, age, colour, gender, comment, price);
    this.breed = breed;
    this.breederName = breederName;
  }

  /**
   * @param name        The cat's name
   * @param age         The cat's age
   * @param colour      The cat's colour
   * @param gender      The cat's gender ('M' or 'F')
   * @param comment     Additional comments about the cat
   * @param breed       The cat's breed
   * @param breederName Name of the cat's breeder
   */
  public Cat(String name, int age, String colour, char gender, String comment,
      String breed, String breederName)
  {
    super(name, age, colour, gender, comment);
    this.breed = breed;
    this.breederName = breederName;
  }

  /**
   * Gets the cat's breed.
   *
   * @return The breed as a String
   */
  public String getBreed()
  {
    return breed;
  }

  /**
   * Gets the name of the cat's breeder.
   *
   * @return The breeder's name as a String
   */
  public String getBreederName()
  {
    return breederName;
  }

  /**
   * Sets the cat's breed.
   *
   * @param breed The new breed
   */
  public void setBreed(String breed)
  {
    this.breed = breed;
  }

  /**
   * Sets the name of the cat's breeder.
   *
   * @param breederName The new breeder's name
   */
  public void setBreederName(String breederName)
  {
    this.breederName = breederName;
  }

  /**
   * Creates a copy of this Cat object.
   *
   * @return A new Cat object with the same attributes as this one
   */
  public Pet copy()
  {
    return new Cat(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), breed, breederName);
  }

  /**
   * Returns a String representation of the Cat.
   * Includes all attributes from the parent Pet class plus cat-specific details.
   *
   * @return
   */
  public String toString()
  {
    return "Cat: " + super.toString() + ", breed: " + breed + ", breeder name: "
        + breederName;
  }

  /**
   * Checks if this Cat is equal to another object.
   *
   * @param obj The object to compare with this Cat
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Cat other = (Cat) obj;
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
   * @return The String "Cat" identifying this pet type
   */
  public String getType()
  {
    return "Cat";
  }
}

package model.Pets;

import java.io.Serializable;

/**
 * Represents a Bird pet in the pet shop system.
 * Extends the Pet class with bird-specific attributes like preferred food and species.
 *
 * @author Yelyzaveta Tkachenko
 */
public class Bird extends Pet implements Serializable
{
  private String preferredFood;
  private String specie;

  /**
   * Creates a new Bird with all attributes including price.
   *
   * @param name          The bird's name
   * @param age           The bird's age
   * @param colour        The bird's colour
   * @param gender        The bird's gender ('M' or 'F')
   * @param comment       Additional comments about the bird
   * @param price         The bird's price
   * @param preferredFood The bird's preferred food type
   * @param specie        The bird's species
   */
  public Bird(String name, int age, String colour, char gender, String comment,
      int price, String preferredFood, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.preferredFood = preferredFood;
    this.specie = specie;
  }

  /**
   * Creates a new Bird without specifying a price.
   *
   * @param name          The bird's name
   * @param age           The bird's age
   * @param colour        The bird's colour
   * @param gender        The bird's gender ('M' or 'F')
   * @param comment       Additional comments about the bird
   * @param preferredFood The bird's preferred food type
   * @param specie        The bird's species
   */
  public Bird(String name, int age, String colour, char gender, String comment,
      String preferredFood, String specie)
  {
    super(name, age, colour, gender, comment);
    this.preferredFood = preferredFood;
    this.specie = specie;
  }

  /**
   * Gets the bird's preferred food type.
   *
   * @return The preferred food as a String
   */
  public String getPreferredFood()
  {
    return preferredFood;
  }

  /**
   * Sets the bird's preferred food type.
   *
   * @param preferredFood The new preferred food type
   */
  public void setPreferredFood(String preferredFood)
  {
    this.preferredFood = preferredFood;
  }

  /**
   * Gets the bird's species.
   *
   * @return The species as a String
   */
  public String getSpecie()
  {
    return specie;
  }

  /**
   * Sets the bird's species.
   *
   * @param specie The new species
   */
  public void setSpecie(String specie)
  {
    this.specie = specie;
  }

  /**
   * Creates a copy of this Bird object.
   *
   * @return A new Bird object with the same attributes as this one
   */
  public Pet copy()
  {
    return new Bird(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), preferredFood, specie);
  }

  /**
   * Returns a String representation of the Bird.
   * Includes all attributes from the parent Pet class plus bird-specific details.
   *
   * @return A formatted String containing all bird information
   */
  public String toString()
  {
    return "Bird: " + super.toString() + ", preferred food: " + preferredFood
        + ", specie: " + specie;
  }

  /**
   * Checks if this Bird is equal to another object.
   *
   * @param obj The object to compare with this Bird
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Bird other = (Bird) obj;
    return other.getName().equals(super.getName())
        && other.getAge() == super.getAge() && other.getColour()
        .equals(super.getColour()) && other.getGender() == super.getGender()
        && other.getComment().equals(super.getComment())
        && other.getPrice() == super.getPrice() && other.getPreferredFood()
        .equals(preferredFood) && other.getSpecie().equals(specie);
  }

  /**
   * Gets the type of pet.
   *
   * @return The String "Bird" identifying this pet type
   */
  public String getType()
  {
    return "Bird";
  }
}
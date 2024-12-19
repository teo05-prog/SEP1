package model.Pets;

import java.io.Serializable;

/**
 * Represents a Various pet in the pet shop system.
 * Extends the Pet class with species information for pets that don't fit other categories.
 *
 * @author Yelyzaveta Tkachenko
 */
public class Various extends Pet implements Serializable
{
  private String specie;

  /**
   * Creates a new Various pet with all attributes including price.
   *
   * @param name    The various pet's name
   * @param age     The various pet's age
   * @param colour  The various pet's colour
   * @param gender  The various pet's gender ('M' or 'F')
   * @param comment Additional comments about the various pet
   * @param price   The various pet's price
   * @param specie  The various pet's species
   */
  public Various(String name, int age, String colour, char gender,
      String comment, int price, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.specie = specie;
  }

  /**
   * Creates a new Various pet without specifying a price.
   *
   * @param name    The various pet's name
   * @param age     The various pet's age
   * @param colour  The various pet's colour
   * @param gender  The various pet's gender ('M' or 'F')
   * @param comment Additional comments about the various pet
   * @param specie  The various pet's species
   */
  public Various(String name, int age, String colour, char gender,
      String comment, String specie)
  {
    super(name, age, colour, gender, comment);
    this.specie = specie;
  }

  /**
   * Gets the various pet's species.
   *
   * @return The species as a String
   */
  public String getSpecie()
  {
    return specie;
  }

  /**
   * Sets the various pet's species.
   *
   * @param specie The new species to set
   */
  public void setSpecie(String specie)
  {
    this.specie = specie;
  }

  /**
   * Creates a copy of this Various pet object.
   *
   * @return A new Various object with identical attributes
   */
  public Pet copy()
  {
    return new Various(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), specie);
  }

  /**
   * Returns a string representation of the Various pet.
   * Includes all attributes from Pet class plus the species.
   *
   * @return A formatted string containing all various pet information
   */
  public String toString()
  {
    return "Various: " + super.toString() + ", specie: " + specie;
  }

  /**
   * Checks if this Various pet is equal to another object.
   *
   * @param obj The object to compare with this Various pet
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Various other = (Various) obj;
    return other.getName().equals(super.getName())
        && other.getAge() == super.getAge() && other.getColour()
        .equals(super.getColour()) && other.getGender() == super.getGender()
        && other.getComment().equals(super.getComment())
        && other.getPrice() == super.getPrice() && other.getSpecie()
        .equals(specie);
  }

  /**
   * Gets the type of pet.
   *
   * @return The string "Various" identifying this pet type
   */
  public String getType()
  {
    return "Various";
  }
}

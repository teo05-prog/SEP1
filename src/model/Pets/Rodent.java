package model.Pets;

import java.io.Serializable;

/**
 * Represents a Rodent pet in the pet shop system.
 * Extends the Pet class with rodent-specific attributes like biting behavior and species.
 *
 * @author Yelyzaveta Tkachenko
 */
public class Rodent extends Pet implements Serializable
{
  private boolean doesItBite;
  private String specie;

  /**
   * Creates a new Rodent with all attributes including price.
   *
   * @param name       The rodent's name
   * @param age        The rodent's age
   * @param colour     The rodent's colour
   * @param gender     The rodent's gender ('M' or 'F')
   * @param comment    Additional comments about the rodent
   * @param price      The rodent's price
   * @param doesItBite Whether the rodent tends to bite
   * @param specie     The rodent's species
   */
  public Rodent(String name, int age, String colour, char gender,
      String comment, int price, boolean doesItBite, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.doesItBite = doesItBite;
    this.specie = specie;
  }

  /**
   * Creates a new Rodent without specifying a price.
   *
   * @param name       The rodent's name
   * @param age        The rodent's age
   * @param colour     The rodent's colour
   * @param gender     The rodent's gender ('M' or 'F')
   * @param comment    Additional comments about the rodent
   * @param doesItBite Whether the rodent tends to bite
   * @param specie     The rodent's species
   */
  public Rodent(String name, int age, String colour, char gender,
      String comment, boolean doesItBite, String specie)
  {
    super(name, age, colour, gender, comment);
    this.doesItBite = doesItBite;
    this.specie = specie;
  }

  /**
   * Gets whether the rodent tends to bite.
   *
   * @return true if the rodent has biting tendencies, false otherwise
   */
  public boolean getDoesItBite()
  {
    return doesItBite;
  }

  /**
   * Sets whether the rodent tends to bite.
   *
   * @param doesItBite doesItBite true if the rodent bites, false otherwise
   */
  public void setDoesItBite(boolean doesItBite)
  {
    this.doesItBite = doesItBite;
  }

  /**
   * Gets the rodent's species.
   *
   * @return The species as a String
   */
  public String getSpecie()
  {
    return specie;
  }

  /**
   * Sets the rodent's species.
   *
   * @param specie The new species
   */
  public void setSpecie(String specie)
  {
    this.specie = specie;
  }

  /**
   * Creates a copy of this Rodent object.
   *
   * @return A new Rodent object with identical attributes
   */
  public Pet copy()
  {
    return new Rodent(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), doesItBite, specie);
  }

  /**
   * Returns a string representation of the Rodent.
   * Includes all attributes from Pet class plus rodent-specific details.
   *
   * @return A formatted string containing all rodent information
   */
  public String toString()
  {
    return "Rodent: " + super.toString() + ", does it bite?: " + doesItBite
        + ", specie: " + specie;
  }

  /**
   * Checks if this Rodent is equal to another object.
   *
   * @param obj The object to compare with this Rodent
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Rodent other = (Rodent) obj;
    return other.getName().equals(super.getName())
        && other.getAge() == super.getAge() && other.getColour()
        .equals(super.getColour()) && other.getGender() == super.getGender()
        && other.getComment().equals(super.getComment())
        && other.getPrice() == super.getPrice()
        && other.getDoesItBite() == doesItBite && other.getSpecie()
        .equals(specie);
  }

  /**
   * Gets the type of pet.
   *
   * @return The string "Rodent" identifying this pet type
   */
  public String getType()
  {
    return "Rodent";
  }
}

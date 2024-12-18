package model.Pets;

import java.io.Serializable;

/**
 * Abstract base class for all pets in the pet shop system.
 * Defines common attributes and behaviors shared by all pet types.
 *
 * @author Yelyzaveta Tkachenko
 */
public abstract class Pet implements Serializable
{
  private String name;
  private int age;
  private String colour;
  private char gender;
  private String comment;
  private int price;

  /**
   * Creates a new Pet with all attributes including price.
   *
   * @param name    The pet's name
   * @param age     The pet's age
   * @param colour  The pet's colour
   * @param gender  The pet's gender ('M' or 'F')
   * @param comment Additional comments about the pet
   * @param price   The pet's price
   */
  public Pet(String name, int age, String colour, char gender, String comment,
      int price)
  {
    this.name = name;
    this.age = age;
    this.colour = colour;
    this.gender = gender;
    this.comment = comment;
    this.price = price;
  }

  /**
   * Creates a new Pet without specifying a price.
   *
   * @param name    The pet's name
   * @param age     The pet's age
   * @param colour  The pet's colour
   * @param gender  The pet's gender ('M' or 'F')
   * @param comment Additional comments about the pet
   */
  public Pet(String name, int age, String colour, char gender, String comment)
  {
    this.name = name;
    this.age = age;
    this.colour = colour;
    this.gender = gender;
    this.comment = comment;
  }

  /**
   * Gets the pet's name.
   *
   * @return The name as a String
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the pet's age.
   *
   * @return The age as an integer
   */
  public int getAge()
  {
    return age;
  }

  /**
   * Gets the pet's colour.
   *
   * @return The colour as a String
   */
  public String getColour()
  {
    return colour;
  }

  /**
   * Gets the pet's gender.
   *
   * @return The gender as a char ('M' or 'F')
   */
  public char getGender()
  {
    return gender;
  }

  /**
   * Gets additional comments about the pet.
   *
   * @return The comments as a String
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Gets the pet's price.
   *
   * @return The price as an integer
   */
  public int getPrice()
  {
    return price;
  }

  /**
   * Sets the pet's name.
   *
   * @param name The new name to set
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Sets the pet's age.
   *
   * @param age The new age to set
   */
  public void setAge(int age)
  {
    this.age = age;
  }

  /**
   * Sets the pet's colour.
   *
   * @param colour The new colour to set
   */
  public void setColour(String colour)
  {
    this.colour = colour;
  }

  /**
   * Sets the pet's gender.
   *
   * @param gender The new gender to set ('M' or 'F')
   */
  public void setGender(char gender)
  {
    this.gender = gender;
  }

  /**
   * Sets additional comments about the pet.
   *
   * @param comment The new comments to set
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * Sets the pet's price.
   *
   * @param price The new price to set
   */
  public void setPrice(int price)
  {
    this.price = price;
  }

  /**
   * Creates a copy of this pet object.
   * Must be implemented by concrete subclasses.
   *
   * @return A new Pet object with identical attributes
   */
  public abstract Pet copy();

  /**
   * Gets the type of pet.
   * Must be implemented by concrete subclasses.
   *
   * @return A string identifying the specific type of pet
   */
  public abstract String getType();

  /**
   * Checks if this pet is equal to another object.
   * Must be implemented by concrete subclasses.
   *
   * @param obj The object to compare with this pet
   * @return true if the objects are equal, false otherwise
   */
  public abstract boolean equals(Object obj);

  /**
   * Returns a string representation of the pet.
   * Includes all base attributes and optionally includes price if set.
   *
   * @return A formatted string containing the pet's information
   */
  public String toString()
  {
    if (price != 0)
    {
      return "name: " + name + ", age: " + age + ", colour: " + colour
          + ", gender: " + gender + ", comment: " + comment + ", price: "
          + price;
    }
    return "name: " + name + ", age: " + age + ", colour: " + colour
        + ", gender: " + gender + ", comment: " + comment;
  }
}


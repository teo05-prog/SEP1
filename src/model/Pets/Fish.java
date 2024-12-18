package model.Pets;

import java.io.Serializable;

/**
 * Represents a Fish pet in the pet shop system.
 * Extends the Pet class with fish-specific attributes like water type, predator status, and species.
 *
 * @author Yelyzaveta Tkachenko
 */
public class Fish extends Pet implements Serializable
{
  private String water;
  private boolean predator;
  private String specie;

  /**
   * Creates a new Fish with all attributes including price.
   *
   * @param name     The fish's name
   * @param age      The fish's age
   * @param colour   The fish's colour
   * @param gender   The fish's gender ('M' or 'F')
   * @param comment  Additional comments about the fish
   * @param price    The fish's price
   * @param water    The type of water the fish requires
   * @param predator Whether the fish is a predator species
   * @param specie   The fish's species
   */
  public Fish(String name, int age, String colour, char gender, String comment,
      int price, String water, boolean predator, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.water = water;
    this.predator = predator;
    this.specie = specie;
  }

  /**
   * Creates a new Fish without specifying a price.
   *
   * @param name     The fish's name
   * @param age      The fish's age
   * @param colour   The fish's colour
   * @param gender   The fish's gender ('M' or 'F')
   * @param comment  Additional comments about the fish
   * @param water    The type of water the fish requires
   * @param predator Whether the fish is a predator species
   * @param specie   The fish's species
   */
  public Fish(String name, int age, String colour, char gender, String comment,
      String water, boolean predator, String specie)
  {
    super(name, age, colour, gender, comment);
    this.water = water;
    this.predator = predator;
    this.specie = specie;
  }

  /**
   * Gets the type of water required for this fish.
   *
   * @return The water type as a String
   */
  public String getWater()
  {
    return water;
  }

  /**
   * Gets whether this fish is a predator.
   *
   * @return true if the fish is a predator, false otherwise
   */
  public boolean getPredator()
  {
    return predator;
  }

  /**
   * Gets the fish's species.
   *
   * @return The species as a String
   */
  public String getSpecie()
  {
    return specie;
  }

  /**
   * Sets the type of water required for this fish.
   *
   * @param water The new water type
   */
  public void setWater(String water)
  {
    this.water = water;
  }

  /**
   * Sets whether this fish is a predator.
   *
   * @param predator true if the fish is a predator, false otherwise
   */
  public void setPredators(boolean predator)
  {
    this.predator = predator;
  }

  /**
   * Sets the fish's species.
   *
   * @param specie The new species
   */
  public void setSpecie(String specie)
  {
    this.specie = specie;
  }

  /**
   * Creates a copy of this Fish object.
   *
   * @return A new Fish object with identical attributes
   */
  public Pet copy()
  {
    return new Fish(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), water, predator, specie);
  }

  /**
   * Returns a string representation of the Fish.
   * Includes all attributes from the parent Pet class plus fish-specific details.
   *
   * @return A formatted string containing all fish information
   */
  public String toString()
  {
    return "Fish: " + super.toString() + ", type of water: " + water
        + ", predator: " + predator + ", specie: " + specie;
  }

  /**
   * Checks if this Fish is equal to another object.
   *
   * @param obj The object to compare with this Fish
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Fish other = (Fish) obj;
    return other.getName().equals(super.getName())
        && other.getAge() == super.getAge() && other.getColour()
        .equals(super.getColour()) && other.getGender() == super.getGender()
        && other.getComment().equals(super.getComment())
        && other.getPrice() == super.getPrice() && other.getWater()
        .equals(water) && other.getPredator() == predator && other.getSpecie()
        .equals(specie);
  }

  /**
   * Gets the type of pet.
   *
   * @return The string "Fish" identifying this pet type
   */
  public String getType()
  {
    return "Fish";
  }
}
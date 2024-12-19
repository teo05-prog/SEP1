package model.Pets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class containing a list of Pets objects.
 *
 * @author Yelyzaveta Tkachenko
 */
public class PetList implements Serializable
{
  private static final long serialVersionUID = -1818279810449535543L;
  private ArrayList<Pet> pets;

  /**
   * No-argument constructor initializing the PetList.
   */
  public PetList()
  {
    pets = new ArrayList<>();
  }

  /**
   * Removes a Pet from the list.
   *
   * @param pet the Pet that will be removed from the list
   */
  public void remove(Pet pet)
  {
    pets.remove(pet);
  }

  /**
   * Gets how many Pet objects are in the list.
   *
   * @return the number of Pet objects in the list
   */
  public int size()
  {
    return pets.size();
  }

  /**
   * Adds a Pet to the list.
   *
   * @param pet the Pet to add to the list
   */
  public void add(Pet pet)
  {
    pets.add(pet);
  }

  /**
   * Gets a Pet object from position index from the list.
   *
   * @param index the position in the list of the Pet object
   * @return the Pet at index if one exists, else null
   */
  public Pet get(int index)
  {
    return pets.get(index);
  }

  /**
   * Gets a String representation of the PetList.
   *
   * @return a String containing information about all Pet objects in the list
   */
  public String toString()
  {
    String str = "";
    for (Pet pet : pets)
    {
      if (pet != null)
      {
        str += pet.toString();
      }
    }
    return str;
  }

  /**
   * Compares this PetList with another object for equality.
   *
   * @param obj The object to compare with this PetList
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    PetList other = (PetList) obj;
    return Objects.equals(pets, other.pets);
  }
}

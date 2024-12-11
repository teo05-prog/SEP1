package model.Pets;

import java.util.ArrayList;
import java.util.Objects;

public class PetList
{
  private ArrayList<Pet> pets;
  private int maxNumberOfPets;

  public PetList(int maxNumberOfPets)
  {
    this.maxNumberOfPets = maxNumberOfPets;
    pets = new ArrayList<>();
  }

  public void addPet(Pet pet)
  {
    if (pets.size() < maxNumberOfPets)
    {
      pets.add(pet);
    }
    else
    {
      System.out.println("Cannot add more pets.");
    }
  }

  public void removePet(Pet pet)
  {
    if (pets.contains(pet))
    {
      pets.remove(pet);
    }
    else
    {
      System.out.println("model.Pets.model.Pets.Pet not found in the list.");
    }
  }

  public Pet getPets(int index) throws Exception
  {
    if (index >= 0 && index < pets.size())
    {
      return pets.get(index);
    }
    else
    {
      throw new IndexOutOfBoundsException("Invalid index");
    }
  }

  public int getPetsCount()
  {
    return pets.size();
  }

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

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    PetList other = (PetList) obj;
    return maxNumberOfPets == other.maxNumberOfPets && Objects.equals(pets,
        other.pets);
  }
}

package model.Pets;

import java.io.Serializable;

public class Rodent extends Pet implements Serializable
{
  private boolean doesItBite;
  private String specie;

  public Rodent(String name, int age, String colour, char gender,
      String comment, int price, boolean doesItBite, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.doesItBite = doesItBite;
    this.specie = specie;
  }

  public boolean getDoesItBite()
  {
    return doesItBite;
  }

  public void setDoesItBite(boolean doesItBite)
  {
    this.doesItBite = doesItBite;
  }

  public String getSpecie()
  {
    return specie;
  }

  public void setSpecie(String specie)
  {
    this.specie = specie;
  }

  public Pet copy()
  {
    return new Rodent(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), doesItBite, specie);
  }

  public String toString()
  {
    return "Rodent: " + super.toString() + ", does it bite?: " + doesItBite
        + ", specie: " + specie;
  }

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
}

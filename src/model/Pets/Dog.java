package model.Pets;

import java.io.Serializable;

public class Dog extends Pet implements Serializable
{
  private String breed;
  private String breederName;

  public Dog(String name, int age, String colour, char gender, String comment,
      int price, String breed, String breederName)
  {
    super(name, age, colour, gender, comment, price);
    this.breed = breed;
    this.breederName = breederName;
  }

  public Dog(String name, int age, String colour, char gender, String comment,
      String breed, String breederName)
  {
    super(name, age, colour, gender, comment);
    this.breed = breed;
    this.breederName = breederName;
  }

  public String getBreed()
  {
    return breed;
  }

  public String getBreederName()
  {
    return breederName;
  }

  public void setBreed(String breed)
  {
    this.breed = breed;
  }

  public void setBreederName(String breederName)
  {
    this.breederName = breederName;
  }

  public Pet copy()
  {
    return new Dog(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), breed, breederName);
  }

  public String toString()
  {
    return "Dog: " + super.toString() + ", breed: " + breed + ", breeder name: "
        + breederName;
  }

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

  public String getType()
  {
    return "Dog";
  }
}

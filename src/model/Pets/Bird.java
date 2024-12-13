package model.Pets;

public class Bird extends Pet
{
  private String preferredFood;
  private String specie;

  public Bird(String name, int age, String colour, char gender, String comment,
      int price, String preferredFood, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.preferredFood = preferredFood;
    this.specie = specie;
  }

  public String getPreferredFood()
  {
    return preferredFood;
  }

  public void setPreferredFood(String preferredFood)
  {
    this.preferredFood = preferredFood;
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
    return new Bird(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), preferredFood, specie);
  }

  public String toString()
  {
    return "model.Pets.model.Pets.Bird: " + super.toString()
        + ", preferred food: " + preferredFood + specie + "/n";
  }

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
}
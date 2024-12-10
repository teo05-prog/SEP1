package model.Pets;

public class Fish extends Pet
{
  private String water;
  private boolean predators;
  private String specie;

  public Fish(String name, int age, String colour, char gender, String comment,
      int price, String water, boolean predators, String specie)
  {
    super(name, age, colour, gender, comment);
    this.water = water;
    this.predators = predators;
    this.specie = specie;
  }

  public String getWater()
  {
    return water;
  }

  public boolean getPredators()
  {
    return predators;
  }

  public String getSpecie()
  {
    return specie;
  }

  public void setWater(String water)
  {
    this.water = water;
  }

  public void setPredators(boolean predators)
  {
    this.predators = predators;
  }

  public void setSpecie(String specie)
  {
    this.specie = specie;
  }

  public Pet copy()
  {
    return new Fish(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), water, predators, specie);
  }

  public String toString()
  {
    return "model.Pets.model.Pets.Fish: " + super.toString() + ", preferred type of water: " + water
        + ", predator: " + predators + ", specie: " + specie + "/n";
  }

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
        .equals(water) && other.getPredators() == predators && other.getSpecie()
        .equals(specie);
  }
}
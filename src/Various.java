public class Various extends Pet
{
  private String specie;

  public Various(String name, int age, String colour, char gender,
      String comment, int price, String specie)
  {
    super(name, age, colour, gender, comment, price);
    this.specie = specie;
  }

  public String getSpecie()
  {
    return specie;
  }

  public void setSpecie()
  {
    this.specie = specie;
  }

  public Pet copy()
  {
    return new Various(super.getName(), getAge(), getColour(), getGender(),
        getComment(), getPrice(), specie);
  }

  public String toString()
  {
    return "Pets.Various: " + super.toString() + ", specie: " + specie + "/n";
  }

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
}

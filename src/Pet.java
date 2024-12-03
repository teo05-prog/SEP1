public abstract class Pet
{
  private String name;
  private int age;
  private String colour;
  private char gender;
  private String comment;

  public Pet(String name,int age,String colour,char gender, String comment){
    this.name=name;
    this.age=age;
    this.colour=colour;
    this.gender=gender;
    this.comment=comment;
  }

  public String getName(){
    return name;
  }
  public int getAge(){
    return age;
  }
  public String getColour(){
    return colour;
  }
  public char getGender(){
    return gender;
  }
  public String getComment(){
    return comment;
  }

  public void setName(String name){
    this.name=name;
  }
  public void setAge(int age){
    this.age=age;
  }
  public void setColour(String colour){
    this.colour=colour;
  }
  public void setGender(char gender){
    this.gender=gender;
  }
  public void setComment(String comment){
    this.comment=comment;
  }

  public abstract Pet copy();

  public abstract boolean equals(Object obj);

  public String toString(){
    return "Name: "+name+", age: "+age+", colour: "+colour+" gender: "+gender+" comment: "+comment;
  }
}


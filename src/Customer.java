public class Customer
{
  private String name;
  private String phone;
  private String email;

  public Customer(String name, String phone, String email)
  {
    setName(name);
    setPhone(phone);
    setEmail(email);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    if(name == null)
    {
      throw new IllegalArgumentException("Name must not be null");
    }
    String[] words = name.split("\\s+");
    String temp = "";
    for (String word : words) {
      if(words.length != 2) {
        throw new IllegalArgumentException("Name must be a string of 2 words");
      }
      if (word.length() < 2) {
        throw new IllegalArgumentException("First and last name be strings of at least 2 characters");
      }
      temp += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
    }
    this.name = temp.trim();
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    if(phone == null)
    {
      throw new IllegalArgumentException("Phone must not be null");
    }
    if(phone.length() != 8){
      throw new IllegalArgumentException("Phone number must be 8 digits long");
    }
    for (int i = 0; i < 8; i++)
    {
      if(phone.charAt(i) != '0' && phone.charAt(i) != '1'
          && phone.charAt(i) != '2' && phone.charAt(i) != '3'
          && phone.charAt(i) != '4' && phone.charAt(i) != '5'
          && phone.charAt(i) != '6' && phone.charAt(i) != '7'
          && phone.charAt(i) != '8' && phone.charAt(i) != '9'){
        throw new IllegalArgumentException("Phone number must contain only digits");
      }
    }
    this.phone = phone;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    if(email == null)
    {
      throw new IllegalArgumentException("Email must not be null");
    }
    if(!email.contains("@") || !email.contains(".")){
      throw new IllegalArgumentException("Email must contain '@' and '.'");
    }
    this.email = email;
  }

  public String toString()
  {
    return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
  }

  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Customer other = (Customer)obj;
    return name.equals(other.getName()) && phone.equals(other.getPhone()) && email.equals(other.getEmail());
  }
}
import java.text.DecimalFormat;

public class MyDate
{
  private int day;
  private int month;
  private int year;

  public MyDate(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public int getDay()
  {
    return day;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public int getMonth()
  {
    return month;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getYear()
  {
    return year;
  }

  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }

  public boolean isBefore(MyDate date)
  {
    if (this.year < date.year)
    {
      if (this.month < date.month)
      {
        if (this.day < date.day)
          return true;
      }
    }
    return false;
  }

  public boolean isAfter(MyDate date)
  {
    if (this.year > date.year)
    {
      if (this.month > date.month)
      {
        if (this.day > date.day)
          return true;
      }
    }
    return false;
  }

  public String toString()
  {
    DecimalFormat formatter = new DecimalFormat("00");
    return formatter.format(this.day) + "/" + formatter.format(this.month) + "/"
        + this.year;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    MyDate other = (MyDate) obj;
    return day == other.day && month == other.month && year == other.year;
  }
}

public class Date
{
  private int day;
  private int month;
  private int year;

  public Date(int day, int month, int year)
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

  public Date copy()
  {
    return new Date(day, month, year);
  }

  public boolean isBefore(Date date1)
  {
    if (this.year < date1.year)
    {
      if (this.month < date1.month)
      {
        if (this.day < date1.day)
          return true;
      }
    }
    return false;
  }

  public String toString()
  {
    return day + "/" + month + "/" + year;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    Date other = (Date) obj;
    return day == other.day && month == other.month && year == other.year;
  }
}

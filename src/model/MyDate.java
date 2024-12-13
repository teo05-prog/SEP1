package model;

import java.text.DecimalFormat;
import java.time.LocalDate;

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

  public static MyDate today()
  {
    LocalDate today = LocalDate.now();
    return new MyDate(today.getDayOfMonth(), today.getMonthValue(),
        today.getYear());
  }

  public int compareTo(MyDate other)
  {
    if (this.year != other.year)
    {
      return this.year - other.year;
    }
    if (this.month != other.month)
    {
      return this.month - other.month;
    }
    return this.day - other.day;
  }

  public boolean isBefore(MyDate date)
  {
    return this.compareTo(date) < 0;
  }

  public boolean isAfter(MyDate date)
  {
    return this.compareTo(date) > 0;
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

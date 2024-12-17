package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * A class that manages dates
 *
 * @author Teodora Stoicescu
 */
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  /**
   * A constructor that makes a new date
   *
   * @param day   The date's day
   * @param month The date's month
   * @param year  The date's year
   */
  public MyDate(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Sets the date's day
   *
   * @param day the date's day to set
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Returns the date's day
   *
   * @return the date's day
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Sets the date's month
   *
   * @param month the date's month to set
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Returns the date's month
   *
   * @return the date's month
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Sets the date's year
   *
   * @param year the date's year to set
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * Returns the date's year
   *
   * @return the date's year
   */
  public int getYear()
  {
    return year;
  }

  /**
   * A method that creates a copy of the date
   *
   * @return a copy of the date
   */
  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }

  /**
   * A method that takes the current date
   *
   * @return today's date
   */
  public static MyDate today()
  {
    LocalDate today = LocalDate.now();
    return new MyDate(today.getDayOfMonth(), today.getMonthValue(),
        today.getYear());
  }

  /**
   * A method that compares the current date with the one in the parameter
   *
   * @param other the date it is being compared with
   * @return an integer value that indicates the comparison result:
   * - A negative value if the current date is earlier than the other date.
   * - Zero if the current date is equal to the other date.
   * - A positive value if the current date is later than the other date.
   */
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

  /**
   * Method that tells you if the date is before the one in the parameter
   *
   * @param date the date it is being compared with
   * @return true if the current date is before the date in the parameter, false otherwise
   */
  public boolean isBefore(MyDate date)
  {
    return this.compareTo(date) < 0;
  }

  /**
   * Method that tells you if the date is after the one in the parameter
   *
   * @param date the date it is being compared with
   * @return true if the current date is after the date in the parameter, false otherwise
   */
  public boolean isAfter(MyDate date)
  {
    return this.compareTo(date) > 0;
  }

  /**
   * Provides a String representation of the date
   *
   * @return a formatted String with the date in the format dd/mm/yyyy
   */
  public String toString()
  {
    DecimalFormat formatter = new DecimalFormat("00");
    return formatter.format(this.day) + "/" + formatter.format(this.month) + "/"
        + this.year;
  }

  /**
   * Compares this date with another object for equality
   *
   * @param obj the object to compare with this date
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    MyDate other = (MyDate) obj;
    return day == other.day && month == other.month && year == other.year;
  }
}

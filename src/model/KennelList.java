package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Booking objects.
 *
 * @author Teodora Stoicescu
 */
public class KennelList implements Serializable
{
  private ArrayList<Booking> bookings;

  /**
   * No-argument constructor initializing the KennelList.
   */
  public KennelList()
  {
    bookings = new ArrayList<>(10);
  }

  /**
   * Adds a Booking to the list.
   *
   * @param booking the Booking to add to the list
   */
  public void add(Booking booking)
  {
    bookings.add(booking);
  }

  /**
   * Removes a Booking from the list.
   *
   * @param booking the Booking that will be removed from the list
   */
  public void remove(Booking booking)
  {
    bookings.remove(booking);
  }

  /**
   * Gets how many Booking objects are in the list.
   *
   * @return the number of Booking objects in the list
   */
  public int size()
  {
    return bookings.size();
  }

  /**
   * Gets a Booking object from position index from the list.
   *
   * @param index the position in the list of the Customer object
   * @return the Booking at index if one exists, else null
   */
  public Booking get(int index)
  {
    return bookings.get(index);
  }

  /**
   * Gets a String representation of the KennelList.
   *
   * @return a String containing information about all Booking objects in the list
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < bookings.size(); i++)
    {
      if (bookings.get(i) != null)
        temp += bookings.get(i);
    }
    return temp;
  }

  /**
   * Compares this KennelList with another object for equality.
   *
   * @param obj The object to compare with this KennelList
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;

    KennelList other = (KennelList) obj;
    return bookings.equals(other.bookings);
  }
}

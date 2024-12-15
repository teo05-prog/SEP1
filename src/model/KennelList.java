package model;

import java.util.ArrayList;

public class KennelList
{
  private ArrayList<Booking> bookings;

  public KennelList()
  {
    bookings = new ArrayList<>(10);
  }

  public void add(Booking booking)
  {
    bookings.add(booking);
  }

  public void remove(Booking booking)
  {
    bookings.remove(booking);
  }

  public Booking getBooking(int index)
  {
    if(index >= 0 && index < bookings.size())
    {
      return bookings.get(index);
    }
    return null;
  }

  public String toString()
  {
    String temp = "";
    for(int i = 0; i < bookings.size(); i++)
    {
      if(bookings.get(i) != null)
        temp += bookings.get(i);
    }
    return temp;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;

    KennelList other = (KennelList) obj;
    return bookings.equals(other.bookings);
  }

  public int size()
  {
    return bookings.size();
  }

  public Booking get(int i)
  {
    return bookings.get(i);
  }

}

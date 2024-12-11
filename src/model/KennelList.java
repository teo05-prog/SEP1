package model;

import java.util.ArrayList;

public class KennelList
{
  private ArrayList<Booking> bookings;

  public KennelList()
  {
    bookings = new ArrayList<>(20);
  }

  public void addBooking(Booking booking)
  {
    bookings.add(booking);
  }

  public void removeBooking(Booking booking)
  {
    bookings.remove(booking);
  }

  public Booking getBooking(int index)
  {
    for (int i = 0; i < bookings.size(); i++)
    {
      if(i == index)
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

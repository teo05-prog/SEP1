package model;

import java.util.ArrayList;

public class Room
{
  private ArrayList<Booking> bookings;

  public Room()
  {
    bookings = new ArrayList<>();
  }

  public boolean isAvailableDuring(MyDate startDate, MyDate endDate)
  {
    for (Booking booking : bookings)
    {
      if (startDate.isBefore(booking.getEndDate()) && endDate.isAfter(
          booking.getStartDate()))
      {
        return false;
      }
    }
    return true;
  }
}

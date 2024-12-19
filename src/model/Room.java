package model;

import java.util.ArrayList;

/**
 * Represents a room in the kennel that can hold bookings.
 * Manages availability and scheduling for a single room.
 */
public class Room
{
  private ArrayList<Booking> bookings;

  /**
   * Creates a new empty room with no bookings.
   */
  public Room()
  {
    bookings = new ArrayList<>();
  }

  /**
   * Checks if the room is available for a given date range.
   * A room is considered available if there are no overlapping bookings during the specified period.
   *
   * @param startDate The start date of the period to check
   * @param endDate The end date of the period to check
   * @return true if the room is available during the entire period, false if there are any overlapping bookings
   */
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

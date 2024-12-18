package utils;

import model.Booking;
import model.KennelList;
import model.MyDate;

import java.io.*;
import java.util.ArrayList;

public class KennelXML {
  private KennelList kennelList;
  private ArrayList<Booking> currentBookings;

  public KennelXML()
  {
    currentBookings = new ArrayList<Booking>();
    readCurrentBookings();
    writeKennel();
  }

  public void readCurrentBookings() {
    try (FileInputStream fileIn = new FileInputStream("bookings.bin");
        ObjectInputStream read = new ObjectInputStream(fileIn)) {

      kennelList = (KennelList) read.readObject();

      // Populate the pets ArrayList from petList
      for (int i = 0; i < kennelList.size(); i++) {
        if ((kennelList.get(i).getStartDate().isBefore(MyDate.today()) ||
            kennelList.get(i).getStartDate().equals(MyDate.today())) &&
            (kennelList.get(i).getEndDate().isAfter(MyDate.today()) ||
                kennelList.get(i).getEndDate().equals(MyDate.today())))
        {
          currentBookings.add(kennelList.get(i));
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void writeKennel() {
    try (FileOutputStream fileOut = new FileOutputStream("kennel.xml");
        PrintWriter write = new PrintWriter(fileOut)) {

      write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      write.println("<kennel>");
      write.println("<occupancy>" + currentBookings.size() + "</occupancy>");
      write.println("</kennel>");
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      System.exit(1);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}
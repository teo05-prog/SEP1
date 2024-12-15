package utils;

import model.*;
import model.Pets.*;

import java.io.*;

public class FileReader
{
  public static void main(String[] args){
    try{FileInputStream fileIn = new FileInputStream("bookings.bin");
      ObjectInputStream read = new ObjectInputStream(fileIn);
      while(true){
        try {
          Booking something = (Booking) read.readObject();
        System.out.println(something);
        }
        catch(EOFException eof) {
          System.out.println("End of file");
          break;
        }
      }
      read.close();
    }
    catch(FileNotFoundException e){
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch(IOException e) {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    }
    catch(ClassNotFoundException e){
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }
}


package utils;

import model.*;

import java.io.*;

public class FileReader
{
  public static void main(String[] args)
  {
    try
    {
      FileInputStream fileIn = new FileInputStream("customers.bin");
      ObjectInputStream read = new ObjectInputStream(fileIn);
      while (true)
      {
        try
        {
          Customer customer = (Customer) read.readObject();
          System.out.println(customer);
        }
        catch (EOFException eof)
        {
          System.out.println("End of file");
          break;
        }
      }
      read.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }
}


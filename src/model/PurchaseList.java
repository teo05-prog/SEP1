package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Purchase objects.
 *
 * @author Bianca Buzdugan
 */
public class PurchaseList implements Serializable
{
  private ArrayList<Purchase> purchases;

  /**
   * No-argument constructor initializing the PurchaseList.
   */
  public PurchaseList()
  {
    purchases = new ArrayList<>();
  }

  /**
   * Gets how many Purchase objects are in the list.
   *
   * @return the number of Purchase objects in the list
   */
  public int size()
  {
    return purchases != null ? purchases.size() : 0;
  }

  /**
   * Gets a Purchase object from position index from the list.
   *
   * @param index the position in the list of the Purchase object
   * @return the Purchase at index if one exists, else null
   */
  public Purchase get(int index)
  {
    return purchases != null ? purchases.get(index) : null;
  }

  /**
   * Adds a Purchase to the list.
   *
   * @param purchase the Purchase to add to the list
   */
  public void add(Purchase purchase)
  {
    purchases.add(purchase);
  }

  /**
   * Removes a Purchase from the list.
   *
   * @param purchase the Purchase that will be removed from the list
   */
  public void remove(Purchase purchase)
  {
    purchases.remove(purchase);
  }

  /**
   * Gets a String representation of the PurchaseList.
   *
   * @return a String containing information about all Purchase objects in the list
   */
  public String toString()
  {
    String str = "";
    for (Purchase purchase : purchases)
    {
      if (purchase != null)
      {
        str += purchase.toString();
      }
    }
    return str;
  }

  /**
   * Compares this PurchaseList with another object for equality.
   *
   * @param obj The object to compare with this PurchaseList
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    PurchaseList other = (PurchaseList) obj;
    return purchases.equals(other.purchases);
  }
}

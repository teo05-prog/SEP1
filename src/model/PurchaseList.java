package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchaseList implements Serializable
{
  private ArrayList<Purchase> purchases;
  private Purchase purchase;

  public PurchaseList()
  {
    purchases = new ArrayList<>();
  }

  public void addPurchase(Purchase purchase)
  {
    purchases.add(purchase);
  }

  public void removePurchase(Purchase purchase)
  {
    purchases.remove(purchase);
  }

  public Purchase getPurchase(int index) throws Exception
  {
    if (index >= 0 && index < purchases.size())
    {
      return purchases.get(index);
    }
    else
    {
      throw new IndexOutOfBoundsException("Invalid index");
    }
  }

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

  public boolean equals(Object obj)
  {
    if (obj == null & getClass() != obj.getClass())
    {
      return false;
    }
    PurchaseList other = (PurchaseList) obj;
    return purchases.equals(other.purchases);
  }

  public int size()
  {
    return purchases != null ? purchases.size() : 0;
  }

  public Purchase get(int index)
  {
    return purchases != null ? purchases.get(index) : null;
  }

  public void add(Purchase purchase)
  {
    purchases.add(purchase);
  }
}

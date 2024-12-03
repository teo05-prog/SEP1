public class Booking
{
  private Pet petInfo;
  private Customer customer;
  private Date startDate;
  private Date endDate;
  private int price;

  public Booking(Customer customer, Pet petInfo, Date startDate, Date endDate, int price)
  {
    this.customer = customer;
    this.petInfo = petInfo;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }


}

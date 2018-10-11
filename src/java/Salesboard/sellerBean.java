/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salesboard;

/**
 *
 * @author kris
 */
public class sellerBean {
    
  private String seller;
  private String buyer;
  private int total_spent;
    
  public sellerBean()  {   }
  public sellerBean(String seller, String buyer, int total_spent) {
      this.seller = seller;
      this.buyer = buyer;
      this.total_spent = total_spent;
  }
  public String getSeller()
  {
    return seller;
  }
  
  public void setSeller(String seller)
  {
    this.seller = seller;
  }
   
  public String getBuyer()
  {
    return buyer;
  }
  
  public void setBuyer(String address)
  {
    this.buyer = buyer;
  }
   public int getTotal_spent()
  {
    return total_spent;
  }
  
  public void setTotal_spent(int total_spent)
  {
    this.total_spent = total_spent;
  }
}



    


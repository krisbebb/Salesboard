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
public class itemBean {
  private int id;
  private String seller;
  private String item;
  private String description;
  private int quantity;
  private  int price;
  
    
  public itemBean()  {   }
  public itemBean(int id, String seller, String item, String description, int quantity, int price) {
        this.id = id;
        this.seller = seller;
        this.item = item;
        this.description = description;
        this.quantity= quantity;
        this.price = price;
       }
  public int getId()
  {
    return id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  public String getSeller()
  {
    return seller;
  }
  
  public void setSeller(String item)
  {
    this.seller = seller;
  }
  
  public String getItem()
  {
    return item;
  }
  
  public void setItem(String item)
  {
    this.item = item;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public void setDescription(String item)
  {
    this.description = description;
  }
  
  public int getQuantity()
  {
    return quantity;
  }
  
  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }
  
   public int getPrice()
  {
    return price;
  }
  
  public void setPrice(int price)
  {
    this.price = price;
  }
}



    


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
public class userBean {
    
  private String username;
  private int age;
  private String address;
    
  public userBean()  {   }
  public userBean(String username, int age, String address) {
      this.username = username;
      this.age = age;
      this.address = address;
  }
  public String getUsername()
  {
    return username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  public int getAge()
  {
    return age;
  }
  
  public void setAge(int age)
  {
    this.age = age;
  }
  
  public String getAddress()
  {
    return address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
}



    


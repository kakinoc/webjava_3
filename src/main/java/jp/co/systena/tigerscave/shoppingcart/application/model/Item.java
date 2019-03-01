package jp.co.systena.tigerscave.shoppingcart.application.model;

public class Item {

  private String name;
  private int price;

  public Item() {}


  public String getName() {
    return name;
  }

  public void setItemName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


}

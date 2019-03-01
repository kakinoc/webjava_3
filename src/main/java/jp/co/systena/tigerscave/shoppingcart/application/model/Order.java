package jp.co.systena.tigerscave.shoppingcart.application.model;

public class Order {

  private Item item;
  private int num;

  public Order() {}

  public Item getItem() {
    return item;
  }
  public void setItem(Item item) {
    this.item = item;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public int calOrderPrice(){
    int orderPrice = this.num * this.item.getPrice();

    return orderPrice;
  }
}

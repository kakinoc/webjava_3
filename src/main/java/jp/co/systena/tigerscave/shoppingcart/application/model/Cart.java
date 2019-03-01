package jp.co.systena.tigerscave.shoppingcart.application.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private List<Order> orderlist = new ArrayList<Order>();

  public List<Order> getOrderList() {
    return orderlist;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderlist = orderList;
  }

  public void addOrderlist(Order order) {
    this.orderlist.add(order);
  }

  public int calOrderTotalPrice() {
    int totalPrice = 0;

    for(Order o : this.orderlist) {
      totalPrice += o.calOrderPrice();
    }

    return totalPrice;
  }
}

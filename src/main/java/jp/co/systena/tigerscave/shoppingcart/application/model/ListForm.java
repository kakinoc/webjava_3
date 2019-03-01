package jp.co.systena.tigerscave.shoppingcart.application.model;

import javax.validation.constraints.NotNull;

public class ListForm {

  @NotNull
  private Item item;

  @NotNull
  private int num;

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
}
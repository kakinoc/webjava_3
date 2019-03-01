package jp.co.systena.tigerscave.shoppingcart.application.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcart.application.model.Cart;
import jp.co.systena.tigerscave.shoppingcart.application.model.Item;
import jp.co.systena.tigerscave.shoppingcart.application.model.ListForm;
import jp.co.systena.tigerscave.shoppingcart.application.model.Order;

@Controller
public class ListController {

  @Autowired
  HttpSession session;

  @Autowired
  JdbcTemplate jdbcTemplate;

  //SQLを定義、商品一覧取得
  String SELECTITEMLISTSQL = "SELECT * FROM Items ORDER BY item_id";
  private List<Item> selectItemList() {

    List<Item> list =
        jdbcTemplate.query(SELECTITEMLISTSQL, new BeanPropertyRowMapper<Item>(Item.class));

    return list;
  }

  @ModelAttribute
  ListForm setUpForm() {
    return new ListForm();
  }

  Order order = new Order();
  Cart cart = new Cart();

  @RequestMapping(value = "/productList", method = RequestMethod.GET)
  public ModelAndView showTop(ModelAndView mav) {

    List<Item> itemlist = this.selectItemList();

    mav.addObject("itemlist", itemlist);

    mav.setViewName("listview1");
    return mav;
  }

  @RequestMapping(value = {"/productList"}, method = {RequestMethod.POST})
  public ModelAndView showProductList(ModelAndView mav, @Valid ListForm listForm, BindingResult bindingResult, HttpServletRequest request) {

    HttpSession session = request.getSession();

    if (bindingResult.getAllErrors().size() > 0) {

      System.out.println(bindingResult.getAllErrors().toString());

      mav.addObject("listForm", new ListForm());
      return new ModelAndView("redirect:/productList");
    }

    Order order = new Order();

    order.setItem(listForm.getItem());
    order.setNum(listForm.getNum());
    cart.addOrderlist(order);

    mav.addObject("totalPrice",cart.calOrderTotalPrice());
    session.setAttribute("message", listForm.getItem().getName() + "を" + listForm.getNum() + "個カートに入れました。"
        + "　合計、" + listForm.getItem().getPrice() * listForm.getItem().getPrice());

    return new ModelAndView("redirect:/productList");
  }

  @RequestMapping(value = {"/comfirm"}, method = {RequestMethod.POST})
  public ModelAndView showComfirm(@ModelAttribute ListForm listForm) {

    ModelAndView mav = new ModelAndView();

    mav.addObject("totalPrice",cart.calOrderTotalPrice());
    mav.setViewName("listview2");
    return mav;
  }
}
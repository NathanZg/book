package com.bookstore.test;

import com.bookstore.pojo.Cart;
import com.bookstore.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * ClassName: CartTest
 * Description:
 * date: 2022/2/2 17:50
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.clear();
        System.out.println(cart);
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.updateCount(1, 10);
        System.out.println(cart);
    }
}
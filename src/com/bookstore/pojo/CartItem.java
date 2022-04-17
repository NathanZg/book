package com.bookstore.pojo;

import java.math.BigDecimal;

/**
 * ClassName: CartItem
 * Description:
 * date: 2022/2/2 16:59
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class CartItem {

    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totoalPrice;

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totoalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totoalPrice = totoalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotoalPrice() {
        return totoalPrice;
    }

    public void setTotoalPrice(BigDecimal totoalPrice) {
        this.totoalPrice = totoalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totoalPrice=" + totoalPrice +
                '}';
    }
}

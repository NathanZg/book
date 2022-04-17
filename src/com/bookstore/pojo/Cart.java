package com.bookstore.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: Cart
 * Description:
 * date: 2022/2/2 17:02
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class Cart {

    private Map<Integer,CartItem> items = new LinkedHashMap<Integer, CartItem>();

    public Cart() {
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount +=entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotoalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public void addItem(CartItem certItem){
        //先查看购物车中是否已经添加
        CartItem item = items.get(certItem.getId());
        if(item==null){
            //没添加过
            items.put(certItem.getId(), certItem);
        }else {
            //添加过
            item.setCount(item.getCount()+1);
            item.setTotoalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id,Integer count){
        //查看是否有添加过
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotoalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

}

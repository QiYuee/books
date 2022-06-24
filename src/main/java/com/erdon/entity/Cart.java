package com.erdon.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer,CartItems> items = new HashMap<>();

    public Map<Integer, CartItems> getItems() {
        return items;
    }

    public void addItem(CartItems cartItems){
        CartItems item = items.get(cartItems.getgId());
        if (item==null){
            items.put(cartItems.getgId(),cartItems);
        }else {
            item.setgNumber(item.getgNumber()+1);
            item.setgTotalPrice(item.getgPrice().multiply(new BigDecimal(item.getgNumber())));
        }
    }

    public void deletItem(Integer gId){
        items.remove(gId);
    }

    public void delectAllItem(){
        items.clear();
    }
    public void updateItemNumber(Integer gId,Integer gNumber){
        CartItems item = this.items.get(gId);
        if (item!=null) {
            item.setgNumber(gNumber);
            item.setgTotalPrice(item.getgPrice().multiply(new BigDecimal(item.getgNumber())));
        }
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItems value : items.values()) {
            totalPrice = totalPrice.add(value.getgTotalPrice());
        }
        return totalPrice;
    }
    public Integer getCount(){
         Integer totalCount = 0;
        for (CartItems value : items.values()) {
            totalCount += value.getgNumber();
        }
        return totalCount;
    }
    @Override
    public String toString() {
        return "totalCount="+getCount()+"\n"+"totalPrice="+getTotalPrice()+"\n"+ "Cart{" +
                "items=" + items +
                '}';
    }

}

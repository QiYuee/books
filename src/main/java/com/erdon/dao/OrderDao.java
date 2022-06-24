package com.erdon.dao;

import com.erdon.entity.Cart;
import com.erdon.entity.Order;

public interface OrderDao{
    public int saveOrder(Order order);
}

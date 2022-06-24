package com.erdon.service.impl;

import com.erdon.dao.OrderDao;
import com.erdon.dao.OrderItemDao;
import com.erdon.entity.*;
import com.erdon.service.BookService;
import com.erdon.service.OrderService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao ;
    OrderItemDao itemDao;
    BookService bookService;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setItemDao(OrderItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
//
//    @Transactional(
//            propagation = Propagation.REQUIRED,
//            isolation = Isolation.DEFAULT,
//            readOnly = false,
//            rollbackFor = {
//
//            }
//    )
    @Override
    public String saveOrder(Cart cart, int userId) {
      //   orderDao = MbUtils.getSqlSession().getMapper(OrderDao.class);
      //   itemDao = MbUtils.getSqlSession().getMapper(OrderItemDao.class);
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        for (CartItems value : cart.getItems().values()) {
            OrderItem item = new OrderItem(null,value.getgName(),value.getgNumber(),value.getgPrice(),value.getgTotalPrice(),orderId);
            itemDao.saveOrderItem(item);
            Book book = bookService.queryBook(value.getgId());
            book.setSales(book.getSales()+value.getgNumber());
            book.setStock(book.getStock()-value.getgNumber());
            bookService.modifyBook(value.getgId(),book);
        }
        cart.delectAllItem();
        return orderId;
    }
}

package com.rcoem.devops.repository;

import com.rcoem.devops.dto.OrderInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OrderInfoRepository {
    Map<String, OrderInfo> orderInfoTable;

    @PostConstruct
    public void init() {
        orderInfoTable = new HashMap<>();
    }
    public List<OrderInfo> getAllOrders(){
      return orderInfoTable.values()
              .stream().collect(Collectors.toList());
    }
    public OrderInfo createOrder(OrderInfo orderInfo){
        String userId=UUID.randomUUID().toString();
       this.orderInfoTable.put(userId, orderInfo);
       return orderInfo.toBuilder().id(userId).build();
    }
    public OrderInfo getOrderById(String id){
        return this.orderInfoTable.getOrDefault(id,null);
    }
}

package com.rcoem.devops.services;

import com.rcoem.devops.dto.OrderInfo;
import com.rcoem.devops.dto.UserInfo;
import com.rcoem.devops.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class OrderService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    RestTemplate restTemplate;
    public List<OrderInfo> getAllOrders(){
        return orderInfoRepository.getAllOrders();
    }
    public String createOrder(OrderInfo orderInfo){
        OrderInfo createdOrderInfo= orderInfoRepository.createOrder(orderInfo);
        UserInfo userInfo = restTemplate.getForObject("http://localhost:9056/user-mgmt/users/"+orderInfo.getUserId(), UserInfo.class);
        return userInfo.getName();
    }
    public OrderInfo getOrder(String id){
        OrderInfo orderInfo = orderInfoRepository.getOrderById(id);
       if (nonNull(orderInfo)){
           return orderInfo;
       }
       throw new RuntimeException("Order not found");
    }
}

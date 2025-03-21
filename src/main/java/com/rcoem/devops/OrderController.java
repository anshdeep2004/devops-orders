package com.rcoem.devops;

import com.rcoem.devops.dto.OrderInfo;
import com.rcoem.devops.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping()
    List<OrderInfo> getAllUsers(){
        return orderService.getAllOrders();
    }
    @PostMapping()
    String  createUser(@RequestBody OrderInfo orderInfo){
        String username= orderService.createOrder(orderInfo);
        return "Thanks for order "+username;
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderInfo> getUserById(@PathVariable String id){
        try{
         return ResponseEntity.ok().body(orderService.getOrder(id));
        }catch (Exception e){
         return   ResponseEntity.notFound().build();
        }
    }
}

package local.jlwilliams.orders.controllers;

import local.jlwilliams.orders.models.Order;
import local.jlwilliams.orders.services.AgentServices;
import local.jlwilliams.orders.services.OrderServices;
import local.jlwilliams.orders.views.AdvanceOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController
{
    @Autowired
    private OrderServices orderService;

    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> findOrders(){
        List<Order> rtn = orderService.listOrders();
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id){
        Order rtn = orderService.findOrderById(id);
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> listOrdersByAdvanceamount(){
        List<AdvanceOrders> rtn = orderService.listAdvanceOrders();
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }
//    POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> postOrder(@Valid @RequestBody Order newOrder){
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);
        HttpHeaders responseHeaders = new HttpHeaders();

        URI orderURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{ordernum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();

        responseHeaders.setLocation(orderURI);
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.OK);
    }

//    PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{id}", consumes = {"application/json"})
    public ResponseEntity<?> putOrder(@Valid @RequestBody Order updateOrder, @PathVariable long id){
        updateOrder.setOrdnum(id);
        orderService.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    DELETE http://localhost:2019/orders/order/58

    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

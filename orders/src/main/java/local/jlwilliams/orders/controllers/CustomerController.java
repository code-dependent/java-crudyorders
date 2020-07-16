package local.jlwilliams.orders.controllers;

import local.jlwilliams.orders.models.Customer;
import local.jlwilliams.orders.services.CustomerServices;
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
@RequestMapping(value = "/customers")
public class CustomerController
{
    @Autowired
    private CustomerServices customerService;

//    http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listCustomerOrders(){
        List<Customer> rtn = customerService.listAllCustomersOrders();
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }
//    http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{id}",produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long id){
        Customer rtn = customerService.findById(id);
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }
//    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{subname}", produces = {"application/json"})
    public ResponseEntity<?> listCustomersBySubname(@PathVariable String subname){
        List<Customer> rtn = customerService.findBySubname(subname);
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

//    POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = {"application/json"} )
    public ResponseEntity<?> postCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI customerURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(customerURI);
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }

//    PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{id}", consumes = {"application/json"})
    public ResponseEntity<?> putCustomer(@Valid @RequestBody Customer customerUpdate, @PathVariable long id){
        customerUpdate.setCustcode(id);
        customerService.save(customerUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{id}", consumes = {"application/json"})
    public ResponseEntity<?> patchCustomer(@RequestBody Customer updatedCustomer, @PathVariable long id){
        customerService.update(updatedCustomer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    DELETE http://localhost:2019/customers/customer/5
    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

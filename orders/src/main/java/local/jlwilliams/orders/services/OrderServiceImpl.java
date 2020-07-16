package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Customer;
import local.jlwilliams.orders.models.Order;
import local.jlwilliams.orders.models.Payment;
import local.jlwilliams.orders.repositories.CustomerRepository;
import local.jlwilliams.orders.repositories.OrderRepository;
import local.jlwilliams.orders.repositories.PaymentRepository;
import local.jlwilliams.orders.views.AdvanceOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl
        implements OrderServices
{
    @Autowired
    private OrderRepository orderrepos;

    @Autowired
    private PaymentRepository paymentrepos;

    @Override
    public List<Order> listOrders()
    {
        List<Order> rtn = new ArrayList<>();
        orderrepos.findAll()
                .iterator()
                .forEachRemaining(rtn::add);
        return rtn;
    }

    @Override
    public Order save(Order order)
    {
        Order rtn = new Order();
        if(order.getOrdnum() != 0){
            orderrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order ID "+ order.getOrdnum() + " Not Found..."));

            rtn.setOrdnum(order.getOrdnum());
        }
        rtn.setCustomer(order.getCustomer());
        rtn.setOrderdescription(order.getOrderdescription());

        rtn.setOrdamount(order.getOrdamount());
        rtn.setAdvanceamount(order.getAdvanceamount());


        rtn.getPayments().clear();
        for(Payment p: order.getPayments()){
            Payment newPay = paymentrepos.findById(p.getPaymentid())
                    .orElseThrow(()-> new EntityNotFoundException("Payment ID " + p.getPaymentid() + " Not Found..."));
            rtn.getPayments().add(newPay);
        }

        return orderrepos.save(rtn);
    }

    @Override
    public Order findOrderById(long id)
    {
        return orderrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Order ID "+ id +" Not Found..."));

    }

    @Override
    public List<AdvanceOrders> listAdvanceOrders()
    {
        return orderrepos.getAdvanceOrders();
    }

    @Override
    public void delete(long id)
    {
        if(!orderrepos.findById(id).isPresent()){
            throw new EntityNotFoundException("Order ID " + id + " Not Found...");
        }
        orderrepos.deleteById(id);

    }
}

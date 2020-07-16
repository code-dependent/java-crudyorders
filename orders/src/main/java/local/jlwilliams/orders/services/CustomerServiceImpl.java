package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Customer;
import local.jlwilliams.orders.models.Order;
import local.jlwilliams.orders.repositories.AgentRepository;
import local.jlwilliams.orders.repositories.CustomerRepository;
import local.jlwilliams.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl
        implements CustomerServices
{
    @Autowired
    private CustomerRepository customerrepos;

    @Autowired
    private OrderRepository orderrepos;

    @Override
    public Customer save(Customer customer)
    {
        Customer rtn = new Customer();
        if(customer.getCustcode() != 0){
            customerrepos.findById(customer.getCustcode())
                    .orElseThrow(()-> new EntityNotFoundException("Customer ID "+ customer.getCustcode()+" Not Found"));

            rtn.setCustcode(customer.getCustcode());
        }
        rtn.setCustname(customer.getCustname());
        rtn.setAgent(customer.getAgent());
        rtn.setCustcity(customer.getCustcity());
        rtn.setCustcountry(customer.getCustcountry());
        rtn.setWorkingarea(customer.getWorkingarea());
        rtn.setPhone(customer.getPhone());
        rtn.setGrade(customer.getGrade());
        rtn.setOpeningamt(customer.getOpeningamt());
        rtn.setOutstandingamt(customer.getOutstandingamt());
        rtn.setPaymentamt(customer.getPaymentamt());
        rtn.setReceiveamt(customer.getReceiveamt());

        rtn.getOrders().clear();
        for(Order o : customer.getOrders()){
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(),rtn, o.getOrderdescription());

            rtn.getOrders().add(newOrder);
        }

        return customerrepos.save(rtn);
    }

    @Override
    public List<Customer> listAllCustomersOrders()
    {
        List<Customer> rtn = new ArrayList<>();
        customerrepos.findAll()
            .iterator()
            .forEachRemaining(rtn::add);
        return rtn;
    }

    @Override
    public Customer findById(long id)
    {
        return customerrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Customer ID "+ id +" Not Found..."));
    }

    @Override
    public List<Customer> findBySubname(String subname)
    {
        List<Customer> rtn = new ArrayList<>();
        rtn = customerrepos.findByCustnameContainingIgnoringCase(subname);
        if(rtn == null){
            throw new EntityNotFoundException("Customers With Subname " + subname + " Not Found");
        }
        return rtn;
    }

    @Override
    public void delete(long id)
    {
        if(!customerrepos.findById(id).isPresent())
        {
            throw new EntityNotFoundException("Customer ID Not Found... ("+ id +")");
        }
        customerrepos.deleteById(id);
    }

    @Override
    public Customer update(Customer customer,
                           long id)
    {
        Customer rtn =
            customerrepos.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("Customer ID "+ customer.getCustcode()+" Not Found"));

        if(customer.getCustname() != null)
        {
            rtn.setCustname(customer.getCustname());
        }
        if(customer.getAgent() != null)
        {
            rtn.setAgent(customer.getAgent());
        }
        if(customer.getCustcity() != null)
        {
            rtn.setCustcity(customer.getCustcity());
        }
        if(customer.getCustcountry() != null)
        {
            rtn.setCustcountry(customer.getCustcountry());
        }
        if(customer.getWorkingarea() != null)
        {
            rtn.setWorkingarea(customer.getWorkingarea());
        }
        if(customer.getPhone() != null)
        {
            rtn.setPhone(customer.getPhone());
        }
        if(customer.getGrade() != null)
        {
            rtn.setGrade(customer.getGrade());
        }
        if(customer.hasvalueforopeningamt)
        {
            rtn.setOpeningamt(customer.getOpeningamt());
        }
        if(customer.hasvalueforoutstandingamt)
        {
            rtn.setOutstandingamt(customer.getOutstandingamt());
        }
        if(customer.hasvalueforpaymentamt)
        {
            rtn.setPaymentamt(customer.getPaymentamt());
        }
        if(customer.hasvalueforreceiveamt)
        {
            rtn.setReceiveamt(customer.getReceiveamt());
        }


        if(customer.getOrders().size() > 0)
        {
            rtn.getOrders()
                    .clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order(o.getOrdamount(),
                        o.getAdvanceamount(),
                        rtn,
                        o.getOrderdescription());

                rtn.getOrders()
                        .add(o);
            }
        }

        return rtn;
    }
}

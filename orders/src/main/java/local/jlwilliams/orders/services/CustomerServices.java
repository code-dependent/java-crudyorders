package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer save(Customer customer);

    List<Customer> listAllCustomersOrders();

    Customer findById(long id);

    List<Customer> findBySubname(String subname);

    void delete(long id);

    Customer update(Customer customer, long id);
}

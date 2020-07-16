package local.jlwilliams.orders.repositories;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository
        extends CrudRepository<Customer, Long>
{
    List<Customer> findByCustnameContainingIgnoringCase(String subname);
}

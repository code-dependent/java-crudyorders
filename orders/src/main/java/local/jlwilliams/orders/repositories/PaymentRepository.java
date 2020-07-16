package local.jlwilliams.orders.repositories;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository
        extends CrudRepository<Payment, Long>
{
}

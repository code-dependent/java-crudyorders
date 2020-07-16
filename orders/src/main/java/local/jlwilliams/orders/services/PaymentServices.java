package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Payment;

public interface PaymentServices
{
    Payment save(Payment payment);
}

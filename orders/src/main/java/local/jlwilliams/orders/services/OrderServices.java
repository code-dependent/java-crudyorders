package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Order;
import local.jlwilliams.orders.views.AdvanceOrders;

import java.util.List;

public interface OrderServices
{
    Order save(Order order);
    Order findOrderById(long id);

    List<AdvanceOrders> listAdvanceOrders();

    void delete(long id);

    List<Order> listOrders();
}

package local.jlwilliams.orders.repositories;

import local.jlwilliams.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long>
{
}

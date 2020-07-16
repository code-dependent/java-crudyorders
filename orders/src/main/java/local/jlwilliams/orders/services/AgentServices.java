package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Agent;

public interface AgentServices
{
    Agent save(Agent agent);

    Agent getAgentById(long id);
}

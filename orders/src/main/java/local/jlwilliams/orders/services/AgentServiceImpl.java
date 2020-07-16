package local.jlwilliams.orders.services;

import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.repositories.AgentRepository;
import local.jlwilliams.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentServices
{
    @Autowired
    private AgentRepository agentrepos;

    @Override
    public Agent save(Agent agent)
    {
        return agentrepos.save(agent);
    }

    @Override
    public Agent getAgentById(long id)
    {
        return agentrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Agent ID "+ id +" Not Found..."));
    }
}

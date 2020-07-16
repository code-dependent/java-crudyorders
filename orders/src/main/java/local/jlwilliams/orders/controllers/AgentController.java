package local.jlwilliams.orders.controllers;


import local.jlwilliams.orders.models.Agent;
import local.jlwilliams.orders.models.Customer;
import local.jlwilliams.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;

@RestController
@RequestMapping(value = "/agents")
public class AgentController
{
    @Autowired
    private AgentServices agentService;

    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> getAgentByid(@PathVariable long id){
        Agent rtn = agentService.getAgentById(id);
        return new ResponseEntity<>(rtn,
                HttpStatus.OK);
    }

    //    Stretch Goal Testing
//    DELETE http://localhost:2019/agents/unassigned/8

//    @DeleteMapping(value = "/unassigned/{id}")
//    public ResponseEntity<?> deleteAgent()

//    DELETE http://localhost:2019/agents/unassigned/16

//    HTML, CSS, Javascript, github, terminal, react.js, LESS, "SASS", Vercel, vscode, Java, Spring Boot, H2DB, SQL, postgresSQL, Bootstrap, Reactstrap, Redux,
}

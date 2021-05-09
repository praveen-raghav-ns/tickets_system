package com.support.tickets.controller;

import com.support.tickets.models.ticket.Ticket;
import com.support.tickets.models.ticket.TicketService;
import com.support.tickets.models.ticket_response.TicketResponse;
import com.support.tickets.models.ticket_response.TicketResponseService;
import com.support.tickets.models.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
public class TicketsController {

    public static final String OPEN_STATUS = "open";

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketResponseService ticketResponseService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/tickets", method = RequestMethod.GET)
    public @ResponseBody Iterable<Ticket> ticketsList() {
        return ticketService.listAllTickets();
    }

    @RequestMapping(path = "tickets", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Ticket createTicket(@RequestBody Map<String, Object> payload){
        Ticket newTicket = new Ticket((String)payload.get("subject"),
                (String)payload.get("description"), OPEN_STATUS, (String)payload.get("priority"), (String)payload.get("type"));
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("UTC"));
        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
        newTicket.setCreatedAt(timestamp);
        newTicket.setUpdatedAt(timestamp);
        newTicket.setAgent(ticketService.fetchAvailableAgent());
        newTicket.setRequester(userService.fetchUserById((Integer) payload.get("requester_id")));

        return ticketService.addTicket(newTicket);
    }

    @RequestMapping(path = "tickets/{id}", method = RequestMethod.GET)
    public @ResponseBody Ticket showTicket(@PathVariable("id") Integer ticketId){
        return ticketService.fetchTicketById(ticketId);
    }

    @RequestMapping(path = "respond_to_ticket/{id}", method = RequestMethod.POST)
    public @ResponseBody TicketResponse addResponse(@PathVariable("id") Integer ticketId, @RequestBody Map<String, Object> payload){
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setRespondedBy(userService.fetchUserById((Integer) payload.get("responded_by_id")));
        ticketResponse.setResponseText((String) payload.get("response_text"));
        ticketResponse.setTicket(ticketService.fetchTicketById(ticketId));

        ticketResponseService.addTicketResponse(ticketResponse);
        return ticketResponse;
    }

    @RequestMapping(path = "/testing", method = RequestMethod.GET)
    public @ResponseBody String testing(){
        ticketService.fetchAvailableAgent();
        return "test";
    }
}

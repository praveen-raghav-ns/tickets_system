package com.support.tickets.models.ticket_response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketResponseService {

    @Autowired
    private TicketResponseRepository ticketResponseRepository;

    public TicketResponse addTicketResponse(TicketResponse newTicketResponse){
        ticketResponseRepository.save(newTicketResponse);
        return newTicketResponse;
    }
}

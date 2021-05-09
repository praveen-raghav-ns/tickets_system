package com.support.tickets.models.ticket;

import com.support.tickets.models.user.User;
import com.support.tickets.models.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;

    public List<Ticket> listAllTickets(){
        // todo: pagination
        List<Ticket> ticketList = new ArrayList<>();
        ticketRepository.getAllByAgentIsNull().forEach(ticketList::add);
        return ticketList;
    }

    public Ticket addTicket(Ticket newTicket){
        ticketRepository.save(newTicket);
        return newTicket;
    }

    public Ticket fetchTicketById(Integer id){
        return ticketRepository.getTicketsById(id);
    }

    public List<Ticket> fetchTicketsUpdatedBefore(Date date, String status){
        return ticketRepository.findAllByUpdatedAtBeforeAndStatus(date, status);
    }

    public List<Ticket> updateMultipleTickets(List<Ticket> tickets){
        ticketRepository.saveAll(tickets);
        return tickets;
    }

    public User fetchAvailableAgent(){
        return userService.fetchUserById(ticketRepository.getAvailableAgentToday());
    }
}

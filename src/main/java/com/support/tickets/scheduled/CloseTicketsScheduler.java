package com.support.tickets.scheduled;

import com.support.tickets.models.ticket.Ticket;
import com.support.tickets.models.ticket.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class CloseTicketsScheduler {
    private static final Logger log = LoggerFactory.getLogger(CloseTicketsScheduler.class);
    @Autowired
    private TicketService ticketService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //scheduled every 8 hours once
    @Scheduled(fixedRate = 8*60*60*1000)
    public void closeResolvedTickets() {
        log.info("CloseTicketsScheduler starts {}", dateFormat.format(new Date()));
        ZoneId defaultZoneId = ZoneId.of("UTC");
        List<Ticket> queriedTickets = ticketService.fetchTicketsUpdatedBefore(Date.from(LocalDate.now().minusDays(30).atStartOfDay(defaultZoneId).toInstant()), "resolved");
        for(Ticket ticket : queriedTickets){
            ticket.setStatus("closed");
        }
        ticketService.updateMultipleTickets(queriedTickets);
        log.info("CloseTicketsScheduler ends {}", dateFormat.format(new Date()));
    }

}

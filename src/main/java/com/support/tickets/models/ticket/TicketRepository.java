package com.support.tickets.models.ticket;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    Iterable<Ticket> getAllByAgentIsNull();
    Ticket getTicketsById(Integer id);

    List<Ticket> findAllByUpdatedAtBeforeAndStatus(Date updatedAt, String status);
    List<Ticket> findAllByUpdatedAtBefore(Date updatedAt);

    @Query(value = "select agent_id from ticket where created_at > CURDATE() group by agent_id order by count(agent_id) asc limit 1;", nativeQuery = true)
    Integer getAvailableAgentToday();
}

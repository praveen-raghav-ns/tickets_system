package com.support.tickets.models.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.support.tickets.models.ticket_response.TicketResponse;
import com.support.tickets.models.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String subject;
    private String description;
    @OneToOne
    private User requester;
    @OneToOne
    private User agent;

    private String status;
    private String priority;
    private String type;

    @OneToMany
    @JoinColumn(name = "ticket_id")
    private Set<TicketResponse> ticketResponses;

    public Integer getId() {
        return id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date updatedAt;

    public Ticket(){}
    public Ticket(String subject, String description, String status, String priority, String type){
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.status = status;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<TicketResponse> getTicketResponses() {
        return ticketResponses;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

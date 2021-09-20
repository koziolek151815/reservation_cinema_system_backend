package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ticket_types")
public class TicketTypeEntity {
    @Id
    @GeneratedValue
    private Long ticketTypeId;
    private Double price;
    private String name;
}

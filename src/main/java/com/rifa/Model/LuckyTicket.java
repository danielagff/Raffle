package com.rifa.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Tb_LuckyTicket")
@Getter @Setter @NoArgsConstructor
public class LuckyTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Lucky_Ticket_Id")
    private Long luckyTicketId;

    @Column(name = "Ticket_Owner")
    private String ticketOwner;

    @Column(name = "QuantityBought")
    private Integer quantityBought;

    @Column(name = "Lucky_Number")
    private String luckyNumber;

    @Column(name = "Ticket_Price")
    private BigDecimal ticketPrice;


}

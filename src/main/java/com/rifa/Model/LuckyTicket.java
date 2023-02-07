package com.rifa.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Tb_lucky_ticket_0263")
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

    @Column(name = "Payment_Status")
    private String PaymentStatus;

    @ManyToOne
    @JoinColumn(name = "Id_Raffle", nullable = false)
    @JsonIgnoreProperties("luckyTicketList")
    private Raffle raffle;

    //I need reference this class with Raffle class, each luckyTicket need to have one Raffle


}

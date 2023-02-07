package com.rifa.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rifa.Model.DTO.LuckyTicketDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Tb_Raffle_143")
@Getter @Setter @NoArgsConstructor
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Raffle")
    private Long idRaffle;

    @Column(name = "Raffle_Name", nullable = false)
    private String raffleName;

    @Column(name = "Award", nullable = false)
    private BigDecimal award;

    @Column(name = "Number_Price", nullable = false)
    private BigDecimal numberPrice;

    @Column(name = "Raffle_Description")
    private String raffleDescription;

    @Column(name = "Raffle_Status")
    private String raffleStatus;

    @Column(name = "Number_Ticket_Quantity")
    private Integer numberTicketsQuantity;

    @Column(name = "Winner_name")
    private String winnerName;

    @Column(name = "Winner_Ticket")
    private String winnerTicket;

    @OneToMany(mappedBy = "raffle")
    @JsonIgnoreProperties("raffle")
    private List<LuckyTicket> luckyTicketList;

}

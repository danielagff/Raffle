package com.rifa.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name = "Winner_Name")
    private String winnerName;

}

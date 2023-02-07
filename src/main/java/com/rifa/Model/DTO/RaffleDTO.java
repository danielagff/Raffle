package com.rifa.Model.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class RaffleDTO {

    private String raffleName;

    private BigDecimal award;

    private BigDecimal numberPrice;

    private Integer numberTicketsQuantity;

    private String raffleDescription;

    private List<LuckyTicketDTO> luckyTicketDTOS;
}

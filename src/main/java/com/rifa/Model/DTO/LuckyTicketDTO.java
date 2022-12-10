package com.rifa.Model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
public class LuckyTicketDTO {

    private String ticketOwner;

    private Integer quantityBought;

    private String luckyNumber;

    private BigDecimal ticketPrice;

}

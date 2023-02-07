package com.rifa.Model.DTO;

import com.rifa.Model.Raffle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class LuckyTicketDTO {

    private String ticketOwner;

    private String luckyNumber;

    private BigDecimal ticketPrice;

    private Long idRaffle;

}

package com.rifa.Service.ServicesInterfaces;

import com.rifa.Model.DTO.RaffleDTO;
import com.rifa.Model.Raffle;

import java.math.BigDecimal;
import java.util.List;

public interface IRaffleService {

    List<RaffleDTO> findAllRaffles() throws Exception;

    String createRaffles(RaffleDTO raffleDTO);

}

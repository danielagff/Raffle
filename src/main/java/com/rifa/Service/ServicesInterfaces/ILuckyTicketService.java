package com.rifa.Service.ServicesInterfaces;

import com.rifa.Generics.BuyTicket;
import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Model.LuckyTicket;

import java.math.BigDecimal;
import java.util.List;

public interface ILuckyTicketService {

    String createLuckyTicket(Long id);

    List<LuckyTicketDTO> getAllLuckyTickets() throws Exception;

    String generateAleatoryNumber();

    String getWinnerOfRaffle(Long id);

    LuckyTicketDTO getWinnerByLuckyNumber(Long idRaffle) throws Exception;

    List<LuckyTicketDTO> getAllParticipantsByRaffleId(Long id);

    BigDecimal getTotalPriceOfTicketsBought(BuyTicket buy, Long id) throws Exception;





    




}

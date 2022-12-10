package com.rifa.Service.ServicesInterfaces;

import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Model.LuckyTicket;

import java.util.List;

public interface ILuckyTicketService {

    String createLuckyTicket(LuckyTicketDTO luckyTicketDTO);

    List<LuckyTicketDTO> getAllLuckyTickets() throws Exception;

    String generateAleatoryNumber();
}

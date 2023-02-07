package com.rifa.Controller;

import com.rifa.Generics.BuyTicket;
import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Service.ServicesInterfaces.ILuckyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/tickets")
public class TicketsController {
    @Autowired
    private ILuckyTicketService iLuckyTicketService;


    @GetMapping("/allTickets")
    public ResponseEntity<List<LuckyTicketDTO>> getAllTickets() throws Exception
    {
        return ResponseEntity.ok(iLuckyTicketService.getAllLuckyTickets());
    }

    @GetMapping("/winner/{id}")
    public LuckyTicketDTO getWinnerByRaffle(@PathVariable Long id) throws Exception
    {
        return iLuckyTicketService.getWinnerByLuckyNumber(id);
    }
    @GetMapping("/allTickets/{id}")
    public ResponseEntity<List<LuckyTicketDTO>> getAllTickets(@PathVariable Long id) throws Exception
    {
        return ResponseEntity.ok(iLuckyTicketService.getAllParticipantsByRaffleId(id));
    }

    @GetMapping("buy/{id}")
    public ResponseEntity<BigDecimal> buyTickets(@RequestBody BuyTicket buy, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(iLuckyTicketService.getTotalPriceOfTicketsBought(buy, id));
    }

}

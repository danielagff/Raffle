package com.rifa.Controller;

import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Service.ServicesInterfaces.ILuckyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tickets")
public class TicketsController {
    @Autowired
    private ILuckyTicketService iLuckyTicketService;

    @PostMapping("/create")
    public String createTicket(@RequestBody LuckyTicketDTO luckyTicketDTO)
    {
        return iLuckyTicketService.createLuckyTicket(luckyTicketDTO);
    }

    @GetMapping("/allTickets")
    public ResponseEntity<List<LuckyTicketDTO>> getAllTickets() throws Exception
    {
        return ResponseEntity.ok(iLuckyTicketService.getAllLuckyTickets());
    }
}

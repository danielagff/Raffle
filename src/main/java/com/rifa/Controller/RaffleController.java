package com.rifa.Controller;

import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Model.DTO.RaffleDTO;
import com.rifa.Model.Raffle;
import com.rifa.Service.ServicesInterfaces.ILuckyTicketService;
import com.rifa.Service.ServicesInterfaces.IRaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruffles")
public class RaffleController {

    @Autowired
    private IRaffleService iRaffleService;

    @PostMapping("/create")
    public String createRuffle(@RequestBody RaffleDTO raffleDTO)
    {
        return iRaffleService.createRaffles(raffleDTO);
    }

    @GetMapping
    public ResponseEntity<List<RaffleDTO>> getAllRaffles() throws Exception {
        return ResponseEntity.ok(iRaffleService.findAllRaffles());
    }


}

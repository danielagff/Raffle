package com.rifa.Repository;

import com.rifa.Model.LuckyTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILuckyTicketRepository extends JpaRepository<LuckyTicket, Long> {
    @Query(value = "select count(1) from Tb_lucky_ticket_0263 where Lucky_Number = :luckyNumber and id_raffle = :id", nativeQuery = true)
    Long availableLuckyNumber(String luckyNumber, Long id);

    @Query(value = "select Lucky_Number from Tb_lucky_ticket_0263 where id_raffle = :id",  nativeQuery = true )
    List<String> getAllLuckyTicketsByRaffle(Long id);

    @Query(value = "select * from Tb_lucky_ticket_0263  where Lucky_Number = :lucky and Id_Raffle = :idRaffle", nativeQuery = true)
    LuckyTicket getTicketInformationByLuckyNumber(String lucky, Long idRaffle);

    @Query(value = "select * from Tb_lucky_ticket_0263  where Lucky_Number = :lucky and Id_Raffle = :idRaffle", nativeQuery = true)
    LuckyTicket getWinnerInformationByLuckyNumber(String lucky, Long idRaffle);

    @Query(value = "select * from Tb_lucky_ticket_0263 where id_raffle = :id",  nativeQuery = true )
    List<LuckyTicket> getAllLuckyTicketByRaffleId(Long id);

}

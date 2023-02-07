package com.rifa.Repository;

import com.rifa.Model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IRaffleRepository extends JpaRepository<Raffle, Long> {

    @Query(value = "select count(1) from Tb_Raffle_143 where Raffle_Name = :raffleName", nativeQuery = true)
    Long isAvailableRaffleName(String raffleName);

    @Query(value = "select Number_Price from Tb_Raffle_143 where Id_Raffle = :id", nativeQuery = true)
    BigDecimal getPriceByIdRaffle(Long id);

    @Query(value = "select Number_Ticket_Quantity from Tb_Raffle_143 where Id_Raffle = :id", nativeQuery = true)
    Integer getNumberTicketQuantityByIdRaffle(Long id);

    @Query(value = "select Id_Raffle from Tb_Raffle_143 where Raffle_Name = :raffleName", nativeQuery = true)
    Long getIdByRaffleName(String raffleName);

    @Query(value = "select * from Tb_Raffle_143 where Id_Raffle = :id", nativeQuery = true)
    Raffle getByIdRuffle(Long id);




}

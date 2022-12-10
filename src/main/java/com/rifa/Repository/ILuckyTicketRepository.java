package com.rifa.Repository;


import com.rifa.Model.LuckyTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILuckyTicketRepository extends JpaRepository<LuckyTicket, Long> {

}

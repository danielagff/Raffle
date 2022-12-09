package com.rifa.Repository;

import com.rifa.Model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRaffleRepository extends JpaRepository<Raffle, Long> {

    @Query(value = "select count(1) from Tb_Raffle_143 where Raffle_Name = :raffleName", nativeQuery = true)
    Long isAvailableRaffleName(String raffleName);
}

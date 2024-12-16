package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s JOIN FETCH s.seller WHERE s.date BETWEEN :localDate AND :now")
    List<SaleMinDTO> findAllByDateBetween(@Param("localDate") LocalDate localDate, @Param("now") LocalDate now);
}

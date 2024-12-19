package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SellerSaleSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@EnableJpaRepositories
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s JOIN FETCH s.seller WHERE s.date BETWEEN :localDate AND :now")
    List<SaleMinDTO> findAllByDateBetween(@Param("localDate") LocalDate localDate, @Param("now") LocalDate now);

    @Query("SELECT s FROM Sale s JOIN s.seller se WHERE s.date BETWEEN :minDate AND :maxDate AND se.name LIKE :name")
    List<SaleMinDTO> findAllByDateBetweenWithName(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, @Param("name") String name);


    @Query("""
                 SELECT new com.devsuperior.dsmeta.projections.SellerSaleSummaryProjection(
                     se.name AS sellerName,
                     SUM(s.amount) AS totalAmount)
                 FROM Sale s
                 JOIN s.seller se
                 WHERE s.date BETWEEN :minDate AND :maxDate
                 GROUP BY se.name
            \s""")
    List<SellerSaleSummaryProjection> getSummaryForSallerRepository(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);

    @Query("""
               SELECT new com.devsuperior.dsmeta.projections.SellerSaleSummaryProjection(
                    se.name AS sellerName,
                    SUM(s.amount) AS totalAmount)
               FROM Sale s
               JOIN s.seller se
               WHERE(:minDate IS NULL OR s.date >= :minDate)
               AND (:maxDate IS NULL OR s.date <= :maxDate)
               GROUP BY se.name
            """)
    List<SellerSaleSummaryProjection> getSummaryForSaller(LocalDate minDate, LocalDate maxDate);

}

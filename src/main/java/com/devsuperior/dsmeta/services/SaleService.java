package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.projections.SellerSaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public List<SaleMinDTO> getReport(LocalDate minDate, LocalDate maxDate, String name) {
        if (minDate == null) {
            minDate = LocalDate.now().minusYears(1);
        }
        if (maxDate == null) {
            maxDate = LocalDate.now();
        }
        if (name == null || name.trim().isEmpty()) {
            return repository.findAllByDateBetween(LocalDate.now().minusYears(1), LocalDate.now());
        } else {
            return repository.findAllByDateBetweenWithName(minDate, maxDate, "%" + name + "%");
        }
    }

    public List<SellerSaleSummaryProjection> getSummaryForSaller(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null && maxDate == null) {
            return repository.getSummaryForSaller(LocalDate.now().minusYears(1), LocalDate.now());
        } else {
            return repository.getSummaryForSallerRepository(minDate, maxDate);
        }
    }

}

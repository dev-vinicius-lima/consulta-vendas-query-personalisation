package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SellerSaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<List<SaleMinDTO>> getReport(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxDate, @RequestParam(required = false) String name) {
        List<SaleMinDTO> dto = service.getReport(minDate, maxDate, name);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping(value = "/summary")
    public ResponseEntity<List<SellerSaleSummaryProjection>> getSummary(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxDate) {
        List<SellerSaleSummaryProjection> summaries = service.getSummaryForSaller(minDate, maxDate);
        return ResponseEntity.ok(summaries);

    }
}

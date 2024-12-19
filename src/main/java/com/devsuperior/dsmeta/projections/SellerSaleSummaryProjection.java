package com.devsuperior.dsmeta.projections;

public class SellerSaleSummaryProjection {

    private String sellerName;
    private Double totalAmount;

    // Constructor matching the expected signature
    public SellerSaleSummaryProjection(String sellerName, Double totalAmount) {
        this.sellerName = sellerName;
        this.totalAmount = totalAmount;
    }

    public SellerSaleSummaryProjection() {
    }

    // Getters
    public String getSellerName() {
        return sellerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}

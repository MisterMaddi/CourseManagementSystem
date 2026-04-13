package com.coursemanagement.interfaces;

/**
 * Interface for purchasable items
 * Demonstrates: Interface with multiple methods
 */
public interface Purchasable {
    double getPurchasePrice();
    boolean canPurchase(double userBalance);
}

package com.coursemanagement.interfaces;

/**
 * Interface for searchable entities
 */
public interface Searchable {
    boolean matchesQuery(String query);
}

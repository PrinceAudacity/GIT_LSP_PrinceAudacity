package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.List;

public class IntegerSet {
    // This list will hold your set elements.
    private List<Integer> set;

    public IntegerSet() {
        // Initialize the list in the constructor.
        this.set = new ArrayList<>();
    }

    // Clear all elements from the set.
    public void clear() {
        set.clear();
    }

    // Return the length of the set.
    public int length() {
        return set.size();
    }

    /*
     * Compare two sets for equality.
     * They are equal if they contain all the same elements.
     */
    public boolean equals(IntegerSet other) {
        // If sizes differ, they can't be equal.
        if (set.size() != other.length()) {
            return false;
        }

        // Check if all elements are the same.
        return set.containsAll(other.set);
    }

    // Check if the set contains a value.
    public boolean contains(int value) {
        return set.contains(value);
    }

    // Add a value to the set if it's not already present.
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    // Remove a value from the set if present.
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    // Return the largest item in the set; throw an exception if the set is empty.
    public int largest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("The set is empty");
        }
        return set.stream().max(Integer::compare).get();
    }

    // Return the smallest item in the set; throw an exception if the set is empty.
    public int smallest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("The set is empty");
        }
        return set.stream().min(Integer::compare).get();
    }

    // Union this set with another set.
    public void union(IntegerSet intSetb) {
        for (int i : intSetb.set) {
            this.add(i);
        }
    }

    // Intersect this set with another set.
    public void intersect(IntegerSet intSetb) {
        set.retainAll(intSetb.set);
    }

    // Set the set to the difference of itself and another set.
    public void diff(IntegerSet intSetb) {
        set.removeAll(intSetb.set);
    }

    // Return true if the set is empty, false otherwise.
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // This method is for the complement of this set relative to another set.
    public void complement(IntegerSet intSetb) {
        // Assuming complement is similar to `diff` in this context
        IntegerSet tempSet = new IntegerSet();
        for (int elem : intSetb.set) {
            if (!this.contains(elem)) {
                tempSet.add(elem);
            }
        }
        this.set = tempSet.set;
    }

    // This toString method will return a string representation of the set.
    @Override
    public String toString() {
        return set.toString();
    }
}

// You also need to define the IntegerSetException class:
class IntegerSetException extends Exception {
    private static final long serialVersionUID = 1L;

	public IntegerSetException(String message) {
        super(message);
    }
}

package org.howard.edu.lsp.assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    // Default Constructor
    public IntegerSet() {
    }

    // Constructor if you want to pass in an already initialized set
    public IntegerSet(ArrayList<Integer> set) {
        this.set = set;
    }

    // Clears the internal representation of the set
    public void clear() {
        set.clear();
    }

    // Returns the length of the set
    public int length() {
        return set.size();
    }

    /*
     * Returns true if the two sets are equal, false otherwise;
     * Two sets are equal if they contain all of the same values in ANY order.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;
        IntegerSet otherSet = (IntegerSet) o;
        return this.set.containsAll(otherSet.set) && otherSet.set.containsAll(this.set);
    }

    // Returns true if the set contains the value, otherwise false
    public boolean contains(int value) {
        return set.contains(value);
    }

    // Returns the largest item in the set; Throws an IntegerSetException if the set is empty
    public int largest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("Set is empty, cannot find the largest item.");
        }
        return Collections.max(set);
    }

    // Returns the smallest item in the set; Throws an IntegerSetException if the set is empty
    public int smallest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("Set is empty, cannot find the smallest item.");
        }
        return Collections.min(set);
    }

    // Adds an item to the set or does nothing if it's already there
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    // Removes an item from the set or does nothing if it's not there
    public void remove(int item) {
        set.remove(Integer.valueOf(item)); // Remove the first occurrence of the item
    }

    // Set union
    public void union(IntegerSet intSetb) {
        for (int item : intSetb.set) {
            if (!set.contains(item)) {
                set.add(item);
            }
        }
    }

    // Set intersection, all elements in s1 and s2
    public void intersect(IntegerSet intSetb) {
        List<Integer> result = new ArrayList<>();
        for (int item : set) {
            if (intSetb.contains(item)) {
                result.add(item);
            }
        }
        set = result;
    }

    // Set difference, i.e., s1 - s2
    public void diff(IntegerSet intSetb) {
        set.removeAll(intSetb.set);
    }

    // Set complement, all elements not in s1
    public void complement(IntegerSet intSetb) {
        List<Integer> result = new ArrayList<>();
        for (int item : intSetb.set) {
            if (!set.contains(item)) {
                result.add(item);
            }
        }
        set = result;
    }

    // Returns true if the set is empty, false otherwise
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // Return String representation of your set
    @Override
    public String toString() {
        return set.toString();
    }
}

package org.howard.edu.lsp.assignment5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegerSetTest {

	// A test case for the 'clear' method
    // It checks if the set is empty after calling the clear method
    @Test
    @DisplayName("Test case for clear")
    public void testClear() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.clear();
        assertTrue(set.isEmpty(), "Set should be empty after clear is called.");
    }

    // A test case for the 'length' method
    // It verifies if the length of the set is as expected after adding items
    @Test
    @DisplayName("Test case for length")
    public void testLength() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        assertEquals(2, set.length(), "Length should be 2 after adding two items.");
    }

    // A test case for the 'equals' method
    // It checks if two sets with the same elements are considered equal
    @Test
    @DisplayName("Test case for equals")
    public void testEquals() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(1);

        assertTrue(set1.equals(set2), "Set1 should be equal to Set2 as they have the same elements.");
    }

    // A test case for the 'contains' method
    // It verifies if the set correctly identifies the presence of an element
    @Test
    @DisplayName("Test case for contains")
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        assertTrue(set.contains(1), "Set should contain the number 1.");
    }

    // A test case for the 'largest' method
    // It checks if the largest element is identified correctly
    @Test
    @DisplayName("Test case for largest")
    public void testLargest() throws IntegerSetException {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(3);
        set.add(2);
        assertEquals(3, set.largest(), "Largest should be 3.");
    }

    // A test case for the 'smallest' method
    // It checks if the smallest element is identified correctly
    @Test
    @DisplayName("Test case for smallest")
    public void testSmallest() throws IntegerSetException {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(3);
        set.add(2);
        assertEquals(1, set.smallest(), "Smallest should be 1.");
    }

    // A test case for the 'add' method that checks adding a new item
    @Test
    @DisplayName("Test case for add")
    public void testAdd() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        assertTrue(set.contains(1), "Set should contain the number 1 after it is added.");
    }

    // A test case for the 'remove' method that checks removing an existing item
    @Test
    @DisplayName("Test case for remove")
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.remove(1);
        assertFalse(set.contains(1), "Set should not contain the number 1 after it is removed.");
    }

    // Test case for union with two non-empty sets
    @Test
    @DisplayName("Test case for union")
    public void testUnion() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(4);

        set1.union(set2);
        assertTrue(set1.contains(1) && set1.contains(2) && set1.contains(3) && set1.contains(4), "Set1 should contain all elements from both sets after union.");
    }

    // Test case for union with an empty set
    @Test
    @DisplayName("Test case for union with empty set")
    public void testUnionWithEmptySet() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();

        set1.union(set2);
        assertEquals(2, set1.length(), "Set1 should remain unchanged after union with an empty set.");
    }

    // Test case for intersection with two sets with common elements
    @Test
    @DisplayName("Test case for intersection")
    public void testIntersect() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);

        set1.intersect(set2);
        assertTrue(set1.contains(2) && set1.length() == 1, "Set1 should only contain the number 2 after intersection with Set2.");
    }

    // Test case for intersection with two sets with no common elements
    @Test
    @DisplayName("Test case for intersection with disjoint sets")
    public void testIntersectDisjoint() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);

        set1.intersect(set2);
        assertTrue(set1.isEmpty(), "Set1 should be empty after intersection with a set that has no common elements.");
    }

    // Test case for difference between two sets
    @Test
    @DisplayName("Test case for difference")
    public void testDiff() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);

        set1.diff(set2);
        assertFalse(set1.contains(2), "Set1 should not contain the number 2 after difference with Set2.");
        assertTrue(set1.contains(1) && set1.contains(3), "Set1 should contain numbers 1 and 3 after difference with Set2.");
    }

    // Test case for complement between two sets
    @Test
    @DisplayName("Test case for complement")
    public void testComplement() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(1);
        set2.add(2);
        set2.add(3);

        set1.complement(set2);
        assertFalse(set1.contains(1) || set1.contains(2), "Set1 should not contain numbers 1 or 2 after complement with Set2.");
        assertTrue(set1.contains(3), "Set1 should contain number 3 after complement with Set2.");
    }

    // Test case for complement with an empty set
    @Test
    @DisplayName("Test case for complement with empty set")
    public void testComplementWithEmptySet() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();

        set1.complement(set2);
        assertEquals(2, set1.length(), "Set1 should remain unchanged after complement with an empty set.");
    }

    // Test case for adding duplicate number to a set
    @Test
    @DisplayName("Test case for adding duplicate number")
    public void testAddDuplicate() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1); // Try to add duplicate
        assertEquals(1, set.length(), "Adding a duplicate should not change the set size.");
    }

    // Test case for removing a number that doesn't exist in the set
    @Test
    @DisplayName("Test case for removing non-existing number")
    public void testRemoveNonExisting() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.remove(2); // Try to remove a non-existing number
        assertTrue(set.contains(1) && set.length() == 1, "Removing a non-existing number should not affect the set.");
    }
}

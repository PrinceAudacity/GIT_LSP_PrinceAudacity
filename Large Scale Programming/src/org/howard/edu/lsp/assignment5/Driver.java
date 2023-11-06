package org.howard.edu.lsp.assignment5;

public class Driver {
    public static void main(String[] args) {
        // Test IntegerSet operations here and print results
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        System.out.println("Value of Set1 is: " + set1.toString());
        try {
            System.out.println("Smallest value in Set1 is: " + set1.smallest());
            System.out.println("Largest value in Set1 is: " + set1.largest());
        } catch (IntegerSetException e) {
            System.out.println("Error: " + e.getMessage());
        }

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);
        set2.add(4);

        System.out.println("Value of Set2 is: " + set2.toString());

        // Test union, intersection, difference, and complement operations
        set1.union(set2);
        System.out.println("Union of Set1 and Set2: " + set1.toString());

        IntegerSet set3 = new IntegerSet();
        set3.add(2);
        set3.add(3);

        set1.intersect(set3);
        System.out.println("Intersection of Set1 and Set3: " + set1.toString());

        set1.diff(set2);
        System.out.println("Difference of Set1 and Set2: " + set1.toString());

        set1.complement(set3);
        System.out.println("Complement of Set1 with respect to Set3: " + set1.toString());
    }
}

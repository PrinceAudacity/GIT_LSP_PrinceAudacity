package org.howard.edu.lsp.midterm.problem2;

public class Driver {
    public static void main(String[] args) {
        Range range1 = new Range(5, 10);
        Range range2 = new Range(8, 15);
        int testValue = 7;
        
        System.out.println("Is " + testValue + " in range? " + range1.isValueInRange(testValue));
        System.out.println("Do range1 and range2 overlap? " + range1.doRangesOverlap(range2));
        System.out.println("Size of range1: " + range1.size());
    }
}

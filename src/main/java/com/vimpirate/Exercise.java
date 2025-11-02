/**
 * (Eliminate duplicates)
 * <p>
 * Write a method that removes the duplicate elements from an array list of integers using the following header:
 * <p>
 * `public static void removeDuplicate(ArrayList<Integer> list)`
 * <p>
 * Write a test program that prompts the user to enter 10 integers to a list and
 * displays the distinct integers in their input order and separated by exactly one space.
 * Sample Run
 * <p>
 * Enter ten integers: 35 5 3 5 64 33 2 2 4 35
 * The distinct integers are 35 5 3 64 33 2 4
 */
package com.vimpirate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Exercise {

    /**
     * Removes duplicate integers from the list.
     * <p>
     * Modifies the input list in place so only unique integers remain.
     *
     * @param list the list of integers
     */
    public static void removeDuplicate(ArrayList<Integer> list) {
        ArrayList<Integer> uniqueList = new ArrayList<>();
        // Using a HashMap to track seen integers,
        // should result in better performance since lookups are O(1)
        // instead of looping through the uniqueList each time.
        HashMap<Integer, Boolean> seenNumbers = new HashMap<>();
        for (Integer number : list) {
            if (!seenNumbers.containsKey(number)) {
                seenNumbers.put(number, true);
                uniqueList.add(number);
            }
        }
        list.clear();
        list.addAll(uniqueList);
    }

    /**
     * Main method to test the removeDuplicate function.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        System.out.print("Enter ten integers: ");
        for (int i = 0; i < 10; i++) {
            list.add(input.nextInt());
        }

        removeDuplicate(list);

        System.out.print("The distinct integers are ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(" ");
        }

        System.out.println();
        input.close();
    }
}

package com.vimpirate;

import java.math.BigInteger;
import java.util.Scanner;

public class Exercise {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer of any size: ");
        int n = input.nextInt();

        BigInteger result = factorial(BigInteger.valueOf(n));
        System.out.println("Factorial of " + n + " is " + result);
    }

    // Recursive method to calculate factorial using BigInteger
    public static BigInteger factorial(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) { // base case
            return BigInteger.ONE;
        } else {
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }
}

package com.vimpirate;

import java.util.Scanner;

/**
 * Chapter 13, Exercise 4.
 * CMSC 215.
 * @author David Graham
 */
public class Exercise {

    /**
     * The main entrypoint for calculating h and k of a parabola.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a,b,c: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            return;
        }
        int a = scanner.nextInt();
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            return;
        }
        int b = scanner.nextInt();
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            return;
        }
        int c = scanner.nextInt();

        if (a == 0) {
            System.out.println("a must not be 0 for a parabola");
            return;
        }

        // h = -b / (2a)
        Rational h = new Rational(-b, 2L * a);

        // k = (4ac - b^2) / (4a)
        long kNum = 4L * a * c - (long) b * b;
        long kDen = 4L * a;
        Rational k = new Rational(kNum, kDen);

        System.out.println("h is " + h + " k is " + k);
    }
}
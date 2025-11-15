package com.vimpirate;

import java.util.Scanner;

public class Exercise {
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
        long hNum = - (long)b;
        long hDen = 2L * a;
        String hStr = toReducedRational(hNum, hDen);

        // k = (4ac - b^2) / (4a)
        long kNum = 4L * a * c - (long)b * b;
        long kDen = 4L * a;
        String kStr = toReducedRational(kNum, kDen);

        System.out.println("h is " + hStr + " k is " + kStr);
    }

    private static String toReducedRational(long num, long den) {
        if (den == 0) return "undefined";
        // normalize sign to denominator positive
        if (den < 0) {
            den = -den;
            num = -num;
        }
        long g = gcd(Math.abs(num), Math.abs(den));
        num /= g;
        den /= g;
        if (den == 1) return Long.toString(num);
        return num + "/" + den;
    }

    private static long gcd(long x, long y) {
        while (y != 0) {
            long t = x % y;
            x = y;
            y = t;
        }
        return x == 0 ? 1 : x;
    }
}
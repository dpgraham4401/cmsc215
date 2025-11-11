/**
 * CMSC 215 - Project 2
 *
 * @author David Graham
 * <p>
 * The second programming project involves writing a program that produces a list of students who
 * are eligible for membership in an honor society. This program consists of four classes.
 */
package com.vimpirate;

import com.vimpirate.students.Graduate;
import com.vimpirate.students.Student;
import com.vimpirate.students.Undergraduate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


/**
 * The main class that reads student data from a file, calculates GPA thresholds,
 * and prints eligible students for the honor society.
 */
public class Project2 {

    private static final double MAX_GPA = 4.0;

    private static Student createUndergraduate(String name, int hours, int points, String studentLevel) {
        Undergraduate.Year year = Undergraduate.Year.valueOf(studentLevel.toUpperCase(Locale.ROOT));
        return new Undergraduate(name, hours, points, year);
    }

    private static Student createGraduate(String name, int hours, int points, String studentLevel) {
        Graduate.Degree degree = Graduate.Degree.valueOf(studentLevel.toUpperCase(Locale.ROOT));
        return new Graduate(name, hours, points, degree);
    }


    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<>();
        double totalGpa = 0.0;

        File file = new File("students.txt");
        if (!file.exists()) {
            System.out.println("File Not Found");
            throw new FileNotFoundException("students.txt (no such file or directory)");
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String name = scanner.next();
                int hours = scanner.nextInt();
                int points = scanner.nextInt();
                String studentLevel = scanner.next().toUpperCase(Locale.ROOT);

                Student student;
                if (studentLevel.equalsIgnoreCase("Freshman") ||
                        studentLevel.equalsIgnoreCase("Sophomore") ||
                        studentLevel.equalsIgnoreCase("Junior") ||
                        studentLevel.equalsIgnoreCase("Senior")) {

                    student = createUndergraduate(name, hours, points, studentLevel);

                } else if (studentLevel.equalsIgnoreCase("Masters") ||
                        studentLevel.equalsIgnoreCase("Doctorate")) {
                    student = createGraduate(name, hours, points, studentLevel);
                } else {
                    System.out.printf("Error: Unknown student level '%s' for student '%s'. Exiting.\n", studentLevel, name);
                    return;
                }

                students.add(student);
                totalGpa += student.gpa();
            }

            if (students.isEmpty()) {
                System.out.println("No student data found.");
                return;
            }

            double averageGpa = totalGpa / students.size();
            // the threshold is halfway between the average GPA and the maximum GPA
            double threshold = (averageGpa + MAX_GPA) / 2.0;
            Student.setGpaThreshold(threshold);

            System.out.printf("GPA Threshold for membership if %.2f\n", threshold);

            for (Student s : students) {
                if (s.eligibleForHonorSociety()) {
                    System.out.println(s);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt not found.");
        }
    }
}

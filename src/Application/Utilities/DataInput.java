/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author KhoaDang
 */
public class DataInput {
    private final Scanner sc;

    public DataInput() {
        sc = new Scanner(System.in);
    }

    public int inputInteger(String msg, int x, int y) {
        System.out.println(msg);
        boolean check = true;
        int input;
        while (check) {
            try {
                input = Integer.parseInt(sc.nextLine());
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + " to " + y);
                    check = true;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.err.println("This must be number");
                check = true;
            }
        }
        return 0;
    }

    public double inputDouble(String msg, int x, int y) {
        System.out.println(msg);
        boolean check = true;
        double input;
        while (check) {
            try {
                input = Double.parseDouble(sc.nextLine());
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.err.println("This must be number");
                check = true;
            }
        }
        return 0;
    }

    public String inputStringCanBlank(String msg) {
        String input = "";
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty());
        return input;
    }

    public String inputStringNotEmpty(String msg) {
        String input = "";
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty());
        return input;
    }

    public String inputStringPattern(String msg, String pt) {
        String input = "";
        Pattern pattern = Pattern.compile(pt);
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty() || !pattern.matcher(input).matches());
        return input;
    }

    public boolean inputYN(String msg) {
        String choice;
        while (true) {
            System.out.println(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must be Y or N");
                continue;
            }
        }
    }

    public LocalDateTime inputDate(String msg, String pattern) {
        System.out.println(msg);
        boolean check = true;
        LocalDateTime date = null;
        while (check) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                date = LocalDateTime.parse(sc.nextLine(), dtf);
                if (date.isBefore(LocalDateTime.now())) {
                    System.out.println("you need to create a tour in the future");
                    check = true;
                } else {
                    check = false;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format date must be " + pattern);
                check = true;
            }
        }
        return date;
    }

}

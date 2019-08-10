package com.javadub1.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        Scanner scanner = new Scanner(System.in);

        boolean isWorking = true;

        do {
            String komenda = scanner.nextLine();

            if (komenda.equalsIgnoreCase("dodaj")) {
                Student nowyStudent = new Student();

                System.out.println("Podaj imie:");
                nowyStudent.setName(scanner.nextLine());

                System.out.println("Podaj indeks:");
                nowyStudent.setIndeks(scanner.nextLine());

                System.out.println("Podaj wiek:");
                nowyStudent.setAge(Integer.parseInt(scanner.nextLine()));

                dao.insertStudent(nowyStudent);
            } else if (komenda.equalsIgnoreCase("usun")) {
                System.out.println("Podaj id do usunięcia:");

                dao.deleteStudent(Long.parseLong(scanner.nextLine()));
//                1. stworzyć komendę sql która usuwa rekrod po indeksie (id) - w miejscu id wstawiamy znak zapytania.
//                2. metoda w dao
//                3. w metodzie prepared statement stworzony z zapytania (stałej finalnej stworzonej w punkcie 1)
//                4. ustawienie wartości id (wewnątrz try)
//                5. wykonanie
//                Podpowiedź wzoruj się na przykładzie INSERT.

            } else if (komenda.equalsIgnoreCase("listuj")) {
                // listowanie
                dao.getAllStudents().forEach(System.out::println);

            } else if (komenda.equalsIgnoreCase("quit")) {
                isWorking = false;
            }
        } while (isWorking);
    }
}









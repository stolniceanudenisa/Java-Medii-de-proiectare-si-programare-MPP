package org.example;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/40766/IdeaProjects/teme-lab-stolniceanudenisa/cars.db");

        System.out.println("Connected to SQLite");
    }

}
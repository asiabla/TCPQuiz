/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.*;

/**
 *
 * @author Joanna
 */

public class Server {

   
     /**
     * Funkcja umozliwiajaca połączenie z bazą danych
     * @param polaczenieURL String
     * @return Connection
     */
    static Connection connectDB(String polaczenieURL)
    {
       
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(polaczenieURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return conn;
    }
    
    /**
     * Funkcja służąca do odczytu od klienta a następnie przesłanie do niego stringa
     * @param string String
     * @param out PrintWriter
     * @param in BufferedReader
     * @return String
     */
    
    //klient wysyła odpowiedz
    static String writeRead(String string, PrintWriter out, BufferedReader in) {
        out.println(string);
        out.flush();
        String str = "";
        try {
            str = in.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }
        return str;
    }

     /**
     * Funkcja obliczająca ID uzytkownika który ma zostać dodany na podstawie max. ID
     * @param stmt Statement
     * @return int x, zmienna będąca ID dla nowego uzytkownika
     */
    static int countIDCustomer(Statement stmt){
        String query = "SELECT MAX(IDCustomer) FROM customer";
        int x=0;
        try{
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            x = rs.getInt(1);
            x+=1;
        } catch (SQLException wyjatek) {
            System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }
        return x;
    }
    
    /**
     * Funkcja obliczająca maxymalny wynik klientow
     * @param stmt Statement
     * @return int x, maxsymalny wynik
     */
    static int maxScore(Statement stmt){
        String query = "SELECT MAX(Score) FROM customer";
        int x=0;
        try{
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            x = rs.getInt(1);
           
        } catch (SQLException wyjatek) {
            System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }
        return x;
    }
    /**
     * Funkcja obliczająca minimalny wynik klientow
     * @param stmt Statement
     * @return int x, minimalny wynik
     */
    static int minScore(Statement stmt){
        String query = "SELECT MIN(Score) FROM customer";
        int x=0;
        try{
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            x = rs.getInt(1);
            
        } catch (SQLException wyjatek) {
            System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }
        return x;
    }
    
     /**
     * Funkcja obliczająca średni wynik klientow
     * @param stmt Statement
     * @return int x, średni wynik
     */
     static double avgScore(Statement stmt){
        String query = "SELECT Score FROM customer";
        double x = 0;
        try{
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            x = rs.getDouble(1);
           
        } catch (SQLException wyjatek) {
            System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }
        return x;
    }
    
 
    
    
}

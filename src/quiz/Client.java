/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Joanna
 */
public class Client 
{
    /**
     * Funkcja służąca do odebrania z serewera i wyświetlenia pierwszych informacji,
     * odebranie pierwszego Stringa od klienta i wysłanie go na serwer
     * @param in BufferedReader
     * @param out PrinterWriter
     * @param str String do odczytu z serwera
     * @param sc Scanner
     * @return odczyt od klienta
     */
    static String selectWhatToDo(BufferedReader in, PrintWriter out, String str, Scanner sc){
        try{
            str = in.readLine();
            System.out.println(str);
            
        } catch (Exception e) {
            System.err.println(e);
        }
        String odczyt;
        odczyt = sc.nextLine();
        out.println(odczyt);
        out.flush();
        return odczyt;
    }
    
    /**
     * Funkcja służąca do odczytu z servera, a następnie pobrania odpowiedzi i przesłania do serwera
     * @param str String
     * @param in BufferedReader
     * @param out PrintWriter
     * @param sc Scanner
     */
    static void readWriteAnswer (String str, BufferedReader in, PrintWriter out, Scanner sc){
        try {
            str = in.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(str);
        
        str = sc.nextLine();
        while(!str.equals("a") &&!str.equals("b") &&!str.equals("c") &&!str.equals("d") &&!str.equals("y"))
        {
            System.out.println ("Incorrect answer. Choose a, b, c or d. Make sure you not use upper-case letter");
            str = sc.nextLine();
        }
        out.println(str);
        out.flush();
    }
    /**
     * Funkcja służąca do odczytu z servera a następnie pobranie i przesłanie do niego stringa
     * @param str String
     * @param in BufferedReader
     * @param out PrintWriter
     * @param sc Scanner
     */
    static void readWrite (String str, BufferedReader in, PrintWriter out, Scanner sc){
        try {
            str = in.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(str);
        str = sc.nextLine();
        out.println(str);
        out.flush();
    }
    
   
     /**
     * Funkcja wychodząca z aplikacji
     * @param sc Scanner
     * @param socket Socket
     * @param str String
     */
    static void exit(Scanner sc, Socket socket, String str){
        System.out.println(str);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        try {
            socket.close();
        } catch (Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Joanna
 */
public class ClientMain {
     public static void main(String args[]) throws IOException{

        //ustawienie portu do komunikacji
        int port = 8000;
        String str = null;
            

                //ustawienie socketa do komunikacji i streamów
                Socket socket = new Socket("localhost", port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                //wprowadzanie informacji przez klienta
                Scanner sc = new Scanner(System.in);

                
                //y jesli bierzemy udzial
                String odczyt = Client.selectWhatToDo(in, out, str, sc);
                
                
                if (odczyt.equals("y"))
                {   
                    // odczytanie i zapisanie imienia uzytkownika 
                    Client.readWrite(str, in, out, sc);
                    odczyt = in.readLine();
                    System.out.println("Hey "+ odczyt+ ", let's get started!!  ");
                    
                    // pięć pętli dla pięciu pytań 
                    for(int i = 1; i<6; i++)
                    {
                    //Istrukcja udzielania odpowiedzi
                    odczyt = in.readLine();
                    System.out.println(odczyt);
                    
                    // pytanie
                    odczyt = in.readLine();
                    System.out.println("Question:  " +odczyt);
                    //odpowiedz1
                    odczyt = in.readLine();
                    System.out.println("a) " + odczyt);
                    //odpowiedz2
                    odczyt = in.readLine();
                    System.out.println("b) " + odczyt);
                    //odpowiedz3
                    odczyt = in.readLine();
                    System.out.println("c) " + odczyt);
                    //odpowiedz4
                    odczyt = in.readLine();
                    System.out.println("d) " + odczyt);
                    
                    //udzielenie odpowiedzi
                      Client.readWriteAnswer(str, in, out, sc);
                    } 
                    
                    //punkty
                    odczyt = in.readLine();
                    System.out.println(odczyt);
                    
                    //statystyki
                    odczyt = in.readLine();
                    System.out.println(odczyt);
                    
                    
                }
                
                else
                {
                    str = "PROGRAM COMPLETED (UNCORRECT FUNCTION) - PRESS ENTER TO EXIT";
                    Client.exit(sc, socket, str);
                    return;
                }
                
               
                
     }

    
}

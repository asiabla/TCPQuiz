/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.*;





/**
 *
 * @author Joanna
 */


public class ServerThread extends Thread {
    Socket mySocket;

    public ServerThread(Socket socket) //konstruktor klasy ServerThread
    {
        super();
        mySocket = socket;
        
    }

    public void run() // program wątku
    {
        while(true)
        {
            try {
                String query;
                int score = 0;
                //polaczenie z baza
                String polaczenieURL = "jdbc:mysql://localhost/questionnaire?user=root&password=";
                Connection conn = Server.connectDB(polaczenieURL);
               //zmienne dla komunikacji tcp
                Statement stmt = conn.createStatement();
                ResultSet rs = null;
                PreparedStatement preparedStmt;
                PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                
                
                String pytanie = "Hello, Do you want to take part in our survey. Enter 'y'";
                String odp = Server.writeRead(pytanie, out, in);
                
                if (odp.equals("y"))
                {   
                    String name;
                    pytanie = "What is your name?";
                    name = Server.writeRead(pytanie, out, in);
                    out.println(name);
                    out.flush();
                    
                    
                    int answerno = 1;
                    
                    for(int id = 1; id <6; id++)
                    {
                        //wyswietlanie pytan
                      pytanie = "Choose the answer by clicking a, b, c or d";
                      out.println(pytanie);
                      out.flush();
                      
                     query = "SELECT question FROM question WHERE IDQuestion ="+id;
                     rs = stmt.executeQuery(query);
                     rs.next();
                     String question = rs.getString("question");
                     out.println(question);
                     out.flush();
                     
                     query = "SELECT Answer FROM answer WHERE IDAnswer ="+answerno;
                     rs = stmt.executeQuery(query);
                     rs.next();
                     String odpowiedz1 = rs.getString("Answer");
                     out.println(odpowiedz1);
                     out.flush();
                     
                     answerno ++;
                     query = "SELECT Answer FROM answer WHERE IDAnswer ="+answerno;
                     rs = stmt.executeQuery(query);
                     rs.next();
                     String odpowiedz2 = rs.getString("Answer");
                     out.println(odpowiedz2);
                     out.flush();
                     
                     answerno ++;
                      query = "SELECT Answer FROM answer WHERE IDAnswer ="+answerno;
                     rs = stmt.executeQuery(query);
                     rs.next();
                     String odpowiedz3 = rs.getString("Answer");
                     out.println(odpowiedz3);
                     out.flush();
                     
                     answerno ++;
                     query = "SELECT Answer FROM answer WHERE IDAnswer ="+answerno;
                     rs = stmt.executeQuery(query);
                     rs.next();
                     String odpowiedz4 = rs.getString("Answer");
                     out.println(odpowiedz4);
                     out.flush();
                     answerno ++;
                     
                     pytanie = "";
                     odp = Server.writeRead(pytanie, out, in);
                     
                      //sprawdzanie odpowiedzi
                      if (odp.equals("c") || odp.equals("d") || odp.equals("a") || odp.equals("b"))
                              {
                                 if(odp.equals("a"))
                                 {
                                        query = "SELECT correctAnswer FROM question WHERE IDQuestion ="+id;
                                        rs = stmt.executeQuery(query);
                                        rs.next();
                                        String correctAnswer = rs.getString("correctAnswer"); 
                                        
                                        if(correctAnswer.equals("a"))
                                        {
                                            score++;
                                        }
                                 }
                                 
                                 else if (odp.equals("b"))
                                 {
                                     query = "SELECT correctAnswer FROM question WHERE IDQuestion ="+id;
                                        rs = stmt.executeQuery(query);
                                        rs.next();
                                        String correctAnswer = rs.getString("correctAnswer"); 
                                        
                                        if(correctAnswer.equals("b"))
                                        {
                                            score++;
                                        }
                                 }
                                  else if (odp.equals("c"))
                                 {
                                     query = "SELECT correctAnswer FROM question WHERE IDQuestion ="+id;
                                        rs = stmt.executeQuery(query);
                                        rs.next();
                                        String correctAnswer = rs.getString("correctAnswer"); 
                                        
                                        if(correctAnswer.equals("c"))
                                        {
                                            score++;
                                        } 
                                 }
                                 
                                 else
                                  {
                                     query = "SELECT correctAnswer FROM question WHERE IDQuestion ="+id;
                                        rs = stmt.executeQuery(query);
                                        rs.next();
                                        String correctAnswer = rs.getString("correctAnswer"); 
                                        
                                        if(correctAnswer.equals("d"))
                                        {
                                            score++;
                                        } 
                                  }
                                 
                                  
                              } 
                    
                    }
                  
                    //dodanie nowego klienta do bazy
                  int x = Server.countIDCustomer(stmt);
                  query = "INSERT INTO `customer` (`IDCustomer`, `Score`, `Name`) VALUES ('"+x+"', '"+score+"', '"+name+"')";
                  preparedStmt = conn.prepareStatement(query);
                  preparedStmt.execute();
                  out.println("Thanks for taking part in our quiz. Your score: " + score +"/5");
                  out.flush(); 
                  
                  //statystyki
                  int maxScore = Server.maxScore(stmt);
                  int minScore = Server.minScore(stmt);
                  double avgScore = Server.avgScore(stmt);
                  
                  out.println("Statistics: MAXIUMUM RESULTS OF USERS: "+ maxScore + ", MINIMUM RESULT OF USERS: " + minScore+ ",  AVERAGE RESULT OF USERS: 3.25 ");
                  out.flush();
                  
                }
                
                conn.close();
                mySocket.close();
            
            } 
            
            catch (Exception e) {
            System.err.println(e);
        }
                
        }
    }
    
 }

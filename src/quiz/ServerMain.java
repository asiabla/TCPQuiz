/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Joanna
 */
public class ServerMain {
    public static void main(String args[]) {
        ServerSocket serverSocket = null;

        //ustawienie portu do komunikaji
        int port = 8000;
        try {
            int i = 1;
            // tworzymy socket
            serverSocket = new ServerSocket(port);
            while (true) {
                
                Socket socket = serverSocket.accept();
                // tworzymy wątek dla danego połączenia i uruchamiamy go
                System.out.println("Spawning "+ i);
                        i++;
                (new ServerThread(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

/**
 *
 * @author Joanna
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;


/**
 *
 * @author Joanna
 */
public class InetAddressTest {
    
     public static void main (String [] args) throws IOException
     {
         if(args.length>0)
         {
             String host = args [0];
             InetAddress [] addresses = InetAddress.getAllByName(host);
             
             for(InetAddress a : addresses)
             {
                 System.out.println (a);
             }
             
         }
         
         else 
         {
             InetAddress localHostAddress = InetAddress.getLocalHost();
             System.out.println (localHostAddress);
         }
     }
    
}

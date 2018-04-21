package com.mano.server;
import java.net.*;    
import java.util.HashSet;
import java.util.Scanner;
import java.io.*;

import com.mano.handler.ClientHandler;
public class FtServer   
{
	
  public static void main(String args[]) throws Exception
 { 
	  System.err.println("Enter the server Path");
	  Scanner sc = new Scanner(System.in);
	  String filePath = sc.nextLine()+"/";
	  
	  
	  System.err.println("Enter the Server Listner Port");
	  sc = new Scanner(System.in);
	  Integer port = sc.nextInt();
	  FtServer server = new FtServer();
	  server.serverConnection(filePath,port,server);

	}
/**
 * 
 * @param filePath
 * @param port
 * @param server
 */
private void serverConnection(String filePath, Integer port, FtServer server) {
	// TODO Auto-generated method stub
	Socket sock = null;
	ServerSocket sersock = null;
	try {
		sersock = new ServerSocket(port);
		
		while(true) { // to accept multiple connection 
		sock = server.createServerSocket(sersock);
		System.out.println("Connection is successful and wating for client");	
		try {
			new ClientHandler(sock,filePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sock.close();
		}
		System.err.println("Testing");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
		try {
			sersock.close();
			sersock = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



private  Socket createServerSocket(ServerSocket sersock) {
	// TODO Auto-generated method stub
	Socket sock = null;
	try {
		// establishing the connection with the server
		
		System.out.println("Server ready for connection");
		 sock = sersock.accept(); // binding with port
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return sock;
}
}
package com.mano.client;
import java.io.File;
import java.util.Scanner;

import com.mano.handler.ServerHandler;
import com.mano.util.SocketUtil;
public class FtClient  {
	SocketUtil util = new SocketUtil();
  public static void main( String args[ ] ) throws Exception
 {
	  
	  System.err.println("Enter the client Path");
	  Scanner sc = new Scanner(System.in);
	  String filePath = sc.nextLine()+"/";
	  
	  System.err.println("Enter the Host");
	  sc = new Scanner(System.in);
	  String address = sc.nextLine();
	  
	  System.err.println("Enter the Port");
	  sc = new Scanner(System.in);
	  Integer port = sc.nextInt();
	  FtClient client = new FtClient();
	  client.clientConnection(filePath,address,port,client);
	}

  /**
   * create socket connection
   * @param filePath
   * @param address
   * @param port
   * @param client
   */
private  void clientConnection(String filePath, String address,Integer port, FtClient client) {
	try {
		 
		  File file = new File(filePath);
			 for (File listFile : file.listFiles()) {
				 new ServerHandler(listFile,address,port);
			 }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
}

}
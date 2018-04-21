package com.mano.handler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.mano.util.SocketUtil;

/**
 * 
 * @author Manohar
 *
 */
public class ServerHandler extends Thread{
	File file = null;
	String address = null;
	Integer port = 0;
	SocketUtil util = new SocketUtil();
	/**
	 * Sending the data to Server
	 * @param file
	 * @param address
	 * @param port
	 */
	public ServerHandler(File file, String address, Integer port) {
		// TODO Auto-generated constructor stub
		this.file = file;
		this.address = address;
		this.port = port;
		
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader br = null;;
		Socket sock = null;
		try {
			sock = createClientSocket(address,port);
			br = util.getBufferReader(file);
			 sendDataToServer(br,sock,file);
			 System.err.println("Client Data has been sent sucessfully");
			 waitingForServerResponse(file,sock);
			 System.err.println(file + " Completed");
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			 try {
				br.close();
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Waiting for server data
	 * @param listFile
	 * @param sock
	 */
	private  void waitingForServerResponse(File listFile, Socket sock) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		BufferedReader socketRead = null;
		InputStream istream = null;
		String str = null;
		try {
			 System.err.println("Waiting for Server To Response");
			 istream = sock.getInputStream();
			 System.err.println("After Server Response");
			 socketRead = util.getBufferReaderInputStream(istream);
			str = socketRead.readLine();
			if (str.equals("DeleteServerFile")) {
				System.err.println("Delete the file");
				listFile.delete();
				System.err.println("Create new file");
				bw = util.getBufferWriter(new File(listFile.getAbsolutePath()));
			}
		     while((str = socketRead.readLine())  !=  null) 
		     { 
		    	 if (str.equals("EOF")) {
		    		 break;
		    	 }
				bw.write(str);
				bw.newLine();
				bw.flush();
		     }
		     
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			    socketRead.close();
			    bw = null;
			    socketRead = null;
			    istream = null;
				str = null;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * Sending the Data to the server
	 * @param br
	 * @param sock
	 * @param file
	 */
private  void sendDataToServer(BufferedReader br,Socket sock,File file ) {
	// TODO Auto-generated method stub
	String line = null;
	OutputStream ostream = null;
	try {
		ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);
		pwrite.println(file.getName());
		while ((line = br.readLine()) != null) {
			pwrite.println(line);
			pwrite.flush();
		}
		pwrite.println("EOF");
		pwrite.flush();
		br.close();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
		line = null;
		ostream = null;
	}
}

	
	
	/**
	 * create client socket connection
	 * @param address
	 * @param port
	 * @return sock
	 */
	private  Socket createClientSocket( String address,
			Integer port) {
		// TODO Auto-generated method stub
		Socket sock = null;
		try {
			 sock = new Socket(address, port);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sock;
	}
	
	
	
}

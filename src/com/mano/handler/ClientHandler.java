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


public class ClientHandler extends Thread{
	private Socket socket;
	private String filePath;
	  SocketUtil util = new SocketUtil();
	public ClientHandler(Socket sock, String filePath) {
		// TODO Auto-generated constructor stub
		this.socket =sock;
		this.filePath = filePath;
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
				InputStream istream = socket.getInputStream();
				OutputStream ostream = socket.getOutputStream();
				writeDataToServer(istream,filePath,ostream);
				System.err.println("Server send the data sucessfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Write the input stream into the file 
	 * and parallel fwd thw data to client.
	 * @param istream
	 * @param filePath
	 * @param ostream
	 * @return
	 */
	private  String writeDataToServer(InputStream istream, String filePath, OutputStream ostream) {
		// TODO Auto-generated method stub
		String fname = null;
		BufferedWriter bw = null;
		try {
			PrintWriter pwrite = new PrintWriter(ostream, true);
			pwrite.println("DeleteServerFile"); // command to delete the client file
			pwrite.flush();
			BufferedReader fileRead = util.getBufferReaderInputStream(istream);
			fname = fileRead.readLine();
			bw = util.getBufferWriter(new File(filePath + fname));
			// reading file contents
			String str = null;
			while ((str = fileRead.readLine()) != null) {
				if (str.equals("EOF")) {
					pwrite.println("EOF"); // command to indicate EOF for client 
					pwrite.flush();
					break;
				}
				System.err.println(str + "-VERIFIED");
				bw.write(str + "-VERIFIED");
				bw.newLine();
				bw.flush();
				pwrite.println(str + "-VERIFIED"); // sending each line to client
				pwrite.flush();
			}
			System.out.println(fname +"File has been sucessfully downloaded");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bw = null;
		}
		return fname;
	}
	
}

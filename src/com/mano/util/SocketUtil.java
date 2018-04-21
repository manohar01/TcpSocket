package com.mano.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SocketUtil {

	/**
	 * Read the file convert it into Buffered Reader
	 * 
	 * @param file
	 * @return br
	 */
	public  BufferedReader getBufferReader(File file) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return br;
	}
	
	/**
	 * Create a new Buffer writer
	 * @param file
	 * @return bw
	 */
	
	public  BufferedWriter getBufferWriter(File file) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		try {
			
			bw = new BufferedWriter(new FileWriter(new File(file.getAbsolutePath())));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bw;
	}

	/**
	 * 
	 * @param istream
	 * @return
	 */
	public  BufferedReader getBufferReaderInputStream(InputStream istream) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(istream));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return br;
	}
	
}

package com.bit2016.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.6";
	private static final int SERVER_PORT = 5000;
	
	public static void main(String[] args) {
		Socket socket =null;
		Scanner sc = null;
		try {
			 sc = new Scanner(System.in);
			//1
			socket = new Socket();
			//2
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			//3

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
			//4
						
			String data = sc.nextLine();
			System.out.println(">>" + sc.nextLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null){
				sc.close();
			}
			if( socket != null && socket.isClosed() == false)	{
				try{
					socket.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}

}

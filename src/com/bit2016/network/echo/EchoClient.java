package com.bit2016.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.6";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		Socket socket = null;
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			// 1
			socket = new Socket();
			// 2
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			// 3
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			// 4
			while (true) {
				System.out.print(">>");
				String data = sc.nextLine();

				if (data.equals("quit")) {
					break;
				}
				pw.println(data);
				String data1 = br.readLine();
				System.out.println("<<" + data1);
			}
		} catch (UnknownHostException ue) {
			System.out.println("안대!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	private static void log( String message ) { 
		System.out.println( "[Echo Client]" + message ); 
	} 


}

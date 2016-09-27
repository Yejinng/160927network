package com.bit2016.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private static final int PORT = 5000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. binding(소켓에 소켓주소(IP +port)을 바인딩한다
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[서버] 연결 기다림");

			// 3. accept(클라이언트로부터 연결요청을 기다린다.)
			Socket socket = serverSocket.accept(); // block
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetSocketAddress.getPort();
			System.out.println("[서버] 연결됨 From " + remoteHostAddress + ":" + remoteHostPort);

			try{	
			// 4. IOStream 받아오기
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
				while( true )	{
				// 5.데이터읽기
					String data = br.readLine();
					if(data == null){
						System.out.println("[서버] 클라이언트로부터 연결 끊김");
						break;
					}
					
					System.out.println("[서버] 데이터 수신:" + data);
				// 6. 쓰기
					pw.println(data);
					//pw.print(data + "\n");
		
				}
			}catch (SocketException e){
						System.out.println("[서버] 클라이언트로부터 연결 끊김");
			} catch(IOException e){
				e.printStackTrace();
			} finally{
				try{
			// 7. 자원정리 (소켓닫기)
			socket.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
			
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}

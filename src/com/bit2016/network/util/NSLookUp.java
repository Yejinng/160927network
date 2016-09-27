package com.bit2016.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookUp {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			String host = sc.nextLine();
			InetAddress[] inetAddresses = InetAddress.getAllByName(host);
			
			for(InetAddress inetAddress : inetAddresses){
				System.out.println(inetAddress.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}

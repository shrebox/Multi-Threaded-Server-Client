package cn_as2;
import java.util.*;
import java.io.*;
import java.net.*;

public class client_2015097 {
	
	public static void main(String[] args)
	{
		
		Socket s = null;
		DataInputStream ips=null;
		DataOutputStream ops=null;
		try {
			s = new Socket("127.0.0.1",1234);
			ips = new DataInputStream(s.getInputStream());
			ops = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scn = new Scanner(System.in);
		
		while(true)
		{
			String bhj = scn.nextLine();
			try {
				ops.writeUTF(bhj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(bhj.equals("exit"))
			{
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Client Disconnected");
				break;
			}
			
			try {
				System.out.println("Recieved from Server: " + ips.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			ips.close();
			ops.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

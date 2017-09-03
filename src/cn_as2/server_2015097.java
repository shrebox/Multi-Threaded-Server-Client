package cn_as2;
import java.util.*;
import java.io.*;
import java.net.*;

public class server_2015097 {
	
	public static void main(String args[]) throws IOException
	{
		
			ServerSocket ss=null;
			try {
				ss = new ServerSocket(1234);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int client_count = 0;
			while(true)
			{
				client_count++;
				final int cc = client_count;
				Socket s=ss.accept();
				
				System.out.println("Client "+ client_count+ " connected!");
				
				DataInputStream ips=new DataInputStream(s.getInputStream());;
				DataOutputStream ops=new DataOutputStream(s.getOutputStream());;
			
				Thread msg = new Thread(new Runnable(){
					
					public void run() {
						// TODO Auto-generated method stub
						
						while(true)
						{
							try {
								String val = ips.readUTF();
								
								System.out.println("Recieved from Client " + cc + " :" + val);
								
								if(val.equals("exit"))
								{
									s.close();
									System.out.println("Client " + cc + " disconnected!");
									break;
								}
								
								// reversing the string
								
								String final_reversed = "";
								String reversed = "";
								String tobereversed = val;
								
								for(int i=tobereversed.length()-1;i>=0;i--)
								{
									reversed += tobereversed.charAt(i);
								}
								
								//System.out.println(reversed);
								
								int lspace = -1;
								
								for(int i =0;i<tobereversed.length();i++)
								{
									if(reversed.charAt(i)==' ')
									{
										String temp = "";
										String temp2 = reversed.substring(lspace+1,i);
										for(int j=temp2.length()-1;j>=0;j--)
										{
											temp += temp2.charAt(j);
										}
										
										final_reversed+=temp + " ";
										//System.out.println(final_reversed);
										lspace = i;
									}
								}
								
								String temp = "";
								String temp2 = reversed.substring(lspace+1,tobereversed.length());
								for(int j=temp2.length()-1;j>=0;j--)
								{
									temp += temp2.charAt(j);
								}
								
								final_reversed+=temp;
								//System.out.println(final_reversed);
								//System.out.println("done");
								// sending the reversed string
								
								ops.writeUTF(final_reversed);
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								//System.out.println("1111111111111111111111111");
								e.printStackTrace();
							}
							
						}
						
						try {
							ips.close();
							ops.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//System.out.println("22222222222222222222222");
						}		
						
					}
					
				});
				
				msg.start();
				
			}		
		
	}
	
}



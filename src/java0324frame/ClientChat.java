package java0324frame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private Scanner input = new Scanner(System.in);
	FrameEx1 mgid=null;
	FrameEx2 myG=null;
	Mian myM=null;

	ClientChat(Socket c) {
		this.withServer = c;
		//streamSet();
		startID();
		//receive();
	//	send();
	}
	private void startID() {
		mgid = new FrameEx1(this);
	}
	private void receive() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("receive start~~");
					while(true) {
						reMsg = withServer.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
					//	System.out.println(msg);
						myG.setMsg(msg);
					}
				} catch (Exception e) {
					System.out.println("client receive end !!!");
					return;
				}
			}
		}).start();
		
	}


	public void send(String reMsg) {
		// TODO Auto-generated method stub
	//	new Thread(new Runnable() {
			
	//		@Override
		//	public void run() {
				// TODO Auto-generated method stub
				try {
					//System.out.println("send start~~");
					//Scanner in = new Scanner(System.in);
					//while(true) {
					//	String reMsg=in.nextLine();
						sendMsg = withServer.getOutputStream();
						sendMsg.write(reMsg.getBytes());
						
					//}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("client send end !!!");
					return;
				}
		//	}
		//}).start();
	}
	public void streamSet(String id) { 
		try {
			sendMsg = withServer.getOutputStream();
			sendMsg.write(id.getBytes());
			
			
			reMsg = withServer.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			String msg = new String(reBuffer);
			msg = msg.trim();
			System.out.println(msg);
			
			myM = new Mian();
		//	myG.setMe(id);
		
			
			receive();
			if(myM != null) {
				mgid.setClose();
			}
			
		} catch (Exception e) {
		
		}
	}
}
package java0324frame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChat extends Thread {

	private Socket withClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private ServerCenter sc = null;
	
	public String getMyId() {
		return id;
		
	}

	private ArrayList<Thread>tList= new ArrayList<>();
	// �� ���� �𸣰�����, �����Բ��� ����(�ʿ���µ� �ѵ� �����Բ��� �־ ������)
	
	ServerChat(Socket c, ServerCenter s) {
		this.withClient = c;
		this.sc = s;
		// streamSet();
	}

	@Override
	public void run() {
		// ServerChat(Socket c){ �� �ִ� streamSet();�� ��Ƽ������ ������� override ���� ������
		streamSet();
		receive();
		// send();

	}

	private void receive() {
		// recevie() �� ������ ������ ó��
		new Thread(new Runnable() {

			@Override
			public void run() { // �������� ������ �޴±�ɸ�
				// TODO Auto-generated method stub
				try {
					System.out.println("receive start!");
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						sc.reMsg(msg, id);
						//System.out.println("[" + id + "]" + msg);
					}
				} catch (Exception e) {
					System.out.println("receive End");
					return;
				}

			}
		}).start();

	}

	public void send(String reMsg) {
		// serverCenter�� reMsg�� ������ �ϱ� ������ public�� reMsg �Ű� ����
		// �ܵ����� thread �� �ʿ䎛� ������

		try {

			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());
		

		} catch (Exception e) {
			System.out.println("send End");
			return;
		}
	}

	private void streamSet() {
		try {
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim(); // Ʈ�� ���� ����

			InetAddress c_ip = withClient.getInetAddress();
			// withClient ��� ��Ĺ���� getInetAddress �� �޾Ƽ� c_ip�� ����
			String ip = c_ip.getHostAddress();
			// c_ip�� string���� ����
			System.out.println(id + "�� �α��� �ϼ̽��ϴ�");

			String reMsg = "�������� �Ǿ����ϴ�";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (IOException e) {
			
		}
	}
}

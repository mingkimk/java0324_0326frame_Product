package java0324frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameEx2 extends JFrame implements ActionListener {

	JLabel titleLb, listLb, infoLb;
	JPanel wp, cp, sp;
	JButton sendBtn, clearBtn;
	
	JList userList;
	JTextArea userInfo;
	JTextField msgbar;
	DefaultListModel<String> lm = new DefaultListModel<String>();
	
	ClientChat mychat=null;

	FrameEx2(ClientChat c) {
		this.mychat=c;
		//frame ������
		this.setBounds(150, 200, 300, 400);
		createN();
		createW();
		createC();
		createS();

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	private void createS() {
		// ��ư
		sp = new JPanel();
		sp.setBackground(new Color(103,153,255));
		msgbar = new JTextField(10);
		sendBtn = new JButton("Send");
		clearBtn =new JButton("Clear");
		sp.add(msgbar);
		sp.add(sendBtn);
		sp.add(clearBtn);
		this.add(sp, "South");

		// �޼��� ������
		msgbar.addActionListener(this);
		sendBtn.addActionListener(this);
		clearBtn.addActionListener(this);

	}

	private void createC() {
		cp = new JPanel();
		cp.setLayout(new BorderLayout());
		infoLb = new JLabel("     ��ȭ ����");
		userInfo = new JTextArea(10, 4);
		userInfo.setBackground(new Color(255,224,140));
		
	
		cp.add(infoLb, "North");
		cp.add(userInfo, "Center");
		this.add(cp, "Center");
	}

	private void createW() {
		wp = new JPanel();
		wp.setLayout(new BorderLayout(40,0));
		listLb = new JLabel("ģ�����");
		wp.add(listLb, "North");

		userList = new JList(lm);
		userList.setBackground(new Color(255,167,97));
		wp.add(userList, "Center");
		this.add(wp, "West");

	}

	private void createN() {
		titleLb = new JLabel(" �ʿͳ� ver 1.0");
		this.add(titleLb, "North");
	}
	
	
	public void setMe(String id) {
		titleLb.setText("���� �����: "+id);
		lm.addElement(id);
		setMsg(id+" �α��� �մϴ�");
	}
	
	public void setMsg(String msg) {
		userInfo.append(msg+"\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object nowCom = e.getSource();
		if (nowCom.equals(sendBtn)|| nowCom.equals(msgbar)) {

			String msg = msgbar.getText();
			// titleLb.setText(msg);
			//System.out.println(msg);
			msgbar.setText("");
			setMsg(msg);
			userInfo.append(msg + "\n");
			mychat.send(msg);
		} else if (nowCom.equals(clearBtn)) {
			msgbar.setText("");
			
		}

	}
	
	

	




}

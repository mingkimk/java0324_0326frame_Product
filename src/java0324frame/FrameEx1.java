package java0324frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameEx1 extends JFrame implements ActionListener {
	JPanel nP, cP, sP;
	JLabel idLabel, pwLabel;
	JTextField idField, pwdField;
	JButton loginBtn;
	
	ClientChat myChat=null;

//JPanel ��Ḧ �������ִ� �׸� /JLabel �ؽ�Ʈ(����)/JTextField ������ ���ڿ��� �Է� �޴� â /JButton ��ư ����
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// ���� �ڽ� ��ü �����
//		new FrameEx1();
//	}

	FrameEx1(ClientChat c) {//
		// ����Ŭ���� ������ �θ�
		super("");

		this.myChat=c;
		// ������ �� �⺻�� BorderLayout
		this.setLayout(new BorderLayout());

		// �뽺 �г� �����
		// �г��� �⺻ ���̾ƿ��� flowlayout.
		nP = new JPanel();
		idLabel = new JLabel("ID");
		nP.add(idLabel);

		// �ؽ�Ʈ �ʵ� �����
		idField = new JTextField(15);
		nP.add(idField);

		// center �г� �����
		cP = new JPanel();
		pwLabel = new JLabel("�� ȣ");
		pwdField = new JTextField(15);
		cP.add(pwLabel);
		cP.add(pwdField);
		// south �г� �����
				sP = new JPanel();
				loginBtn = new JButton("Login");
				sP.add(loginBtn);
				
				
				loginBtn.addActionListener(this);

				// �г� �����ӿ� �ֱ� ��ġ�� ����"".
				this.add(nP, "North");
				this.add(cP, "Center");
				this.add(sP, "South");
				
				// �� ����
				// this.setBackground(Color,blue);

				// ������ �����ֱ�
				this.setBounds(200, 100, 300, 200);

				// �ڵ����� �����Ը���
				this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
				// true�� ȭ�鿡�� ��Ÿ���� false�� ȭ�鿡�� �������
				this.setVisible(true);

	
				
	}
	public void setClose() {
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object cObj=e.getSource();
		if(cObj.equals(loginBtn));
		myChat.streamSet(idField.getText());
		//this.setVisible(false);
		
		
	}
}

	
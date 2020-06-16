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

//JPanel 재료를 담을수있는 그릇 /JLabel 텍스트(문자)/JTextField 한줄의 문자열을 입력 받는 창 /JButton 버튼 형식
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// 가기 자신 객체 만들기
//		new FrameEx1();
//	}

	FrameEx1(ClientChat c) {//
		// 슈퍼클래스 생성자 부름
		super("");

		this.myChat=c;
		// 프레임 의 기본은 BorderLayout
		this.setLayout(new BorderLayout());

		// 노스 패널 만들기
		// 패널의 기본 레이아웃은 flowlayout.
		nP = new JPanel();
		idLabel = new JLabel("ID");
		nP.add(idLabel);

		// 텍스트 필드 만들기
		idField = new JTextField(15);
		nP.add(idField);

		// center 패널 만들기
		cP = new JPanel();
		pwLabel = new JLabel("암 호");
		pwdField = new JTextField(15);
		cP.add(pwLabel);
		cP.add(pwdField);
		// south 패널 만들기
				sP = new JPanel();
				loginBtn = new JButton("Login");
				sP.add(loginBtn);
				
				
				loginBtn.addActionListener(this);

				// 패널 프레임에 넣기 위치도 지정"".
				this.add(nP, "North");
				this.add(cP, "Center");
				this.add(sP, "South");
				
				// 색 지정
				// this.setBackground(Color,blue);

				// 사이즈 정해주기
				this.setBounds(200, 100, 300, 200);

				// 자동으로 꺼지게만듬
				this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
				// true면 화면에서 나타나고 false면 화면에서 사라져라
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

	
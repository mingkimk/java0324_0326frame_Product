package JTableEX2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




	public class WindowonWindow extends JFrame {
		// ��ư�� �� ���� Ŭ����
		    public WindowonWindow() {
		        
		        setTitle("��ư���� ���� ���ο� â");
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		        JPanel MainContainer = new JPanel();
		        setContentPane(MainContainer);
		        
		        

//				JPanel cP = new JPanel();
//				JLabel chLabel = new JLabel("�ֹ� �Ͻðڽ��ϱ�?");
//				cP.add(chLabel);
//				this.add(cP, "Center");
		        
		        JPanel sP=new JPanel();
		        JLabel chLabel = new JLabel("�ֹ� �Ͻðڽ��ϱ�?");
				sP.add(chLabel);
				this.add(sP, "South");
		        JButton OpenWindow = new JButton("�� â ����");
		       this.add(sP,"South");
		        
		        OpenWindow.addActionListener(new ActionListener() {
		            // ������� ��ư "�� â ����"�� ��ư�� �������� �߻��ϴ� �ൿ�� ����
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // TODO Auto-generated method stub
		                new newWindow(); // Ŭ���� newWindow�� ���� ������
		            }
		            
		        });
		        
		        MainContainer.add(OpenWindow);
		        
		        setSize(300,100);
		        setResizable(false);
		        setVisible(true);
		    }
		    
		    public static void main(String[] args) {
		        // TODO Auto-generated method stub
		        new WindowonWindow();
		    }
		 
		}
		 
		class newWindow extends JFrame {
		    // ��ư�� �������� ��������� �� â�� ������ Ŭ����
		    newWindow() {
		        setTitle("���� ��� â");
		        // ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
		        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
		        
		        JPanel NewWindowContainer = new JPanel();
		        setContentPane(NewWindowContainer);
		        
		        JLabel NewLabel = new JLabel("�� â�� ���µ� ����!");
		        
		        NewWindowContainer.add(NewLabel);
		        
		        setSize(300,100);
		        setResizable(false);
		        setVisible(true);
		    }
		}
		 


	
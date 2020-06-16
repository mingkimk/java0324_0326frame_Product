package java0324frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



public class Mian extends JFrame implements ActionListener {
	String header[] = { "�̸�", "����", "����", "�������","1" };
	String contents[][] = { { "�����", "10", "800", "2020.01.23","2" }, { "Ȩ����", "9", "900", "2020.03.23","3" }, { "��īĨ", "12", "1000", "2020.02.23","4" }, };

	JTabbedPane tabPane = new JTabbedPane();

	DefaultTableModel tableModel = new DefaultTableModel(contents, header);

	JTable table = new JTable(tableModel);
	JScrollPane tableScroll = new JScrollPane(table);

	JPanel tab_1 = new JPanel();
	JPanel tab_1_inputP = new JPanel();
	JPanel tab_2 = new JPanel();

	JTextField[] indata = new JTextField[5];

	JPopupMenu popup;
	JMenuItem m_del, m_mod,m_Order;

	int modIntRow = -1;
	int OrderIntRow=-1;

	String fileName = "data.txt";
	Scanner in = new Scanner(System.in);
	
	DAO sqlDAO=DAO.getInstance();
	ArrayList<String[]> initList=new ArrayList<>();

	Mian() {
		super("��");
		Dimension size = new Dimension(600, 400);
		menuLayout();// �˾��޴�
		tableSetting();
		createInputP();
		createTabbedP();

		init(); // frame�� ����ǰ� ��� ������Ʈ�� �����Ǹ� �ʱ� ������ ���� �������� �޼���

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabPane);
		//this.setPreferredSize(size);

		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void init() {
		initList=sqlDAO.getList();
		for(int i=0 ; i < initList.size(); i++) {
			tableModel.addRow(initList.get(i));
		}
	}

	public void menuLayout() {
		popup = new JPopupMenu();
		m_mod = new JMenuItem("����");
		m_mod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// �����ϰ� ���� row�� ��ȯ�Ͽ��� �ش�Ǵ� ������ textfield�� �������� �ϰڽ��ϴ�.
				modIntRow = table.getSelectedRow();
				for (int i = 0; i < indata.length; i++) {
					indata[i].setText((String) table.getValueAt(modIntRow, i));
				}
			}
		});
		m_del = new JMenuItem("����");
		m_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {
					return;
				} else {
					delTableRow(table.getSelectedRow());
				}
			}
		});
		m_Order=new JMenuItem("�ֹ�");
		m_Order.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderIntRow=table.getSelectedRow();
				for(int i=0;i<indata.length;i++) {
					indata[i].setText((String) table.getValueAt(OrderIntRow, i));
				}
			}
		});
		popup.add(m_mod);
		popup.add(m_del);
		popup.add(m_Order);
	}

	public void delTableRow(int row) {
		tableModel.removeRow(row);
	}

	public void tableSetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.add(popup);
		table.addMouseListener(new MouseAdapter() { // ���콺 ���� ����  (Ŭ��, �巡��)
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {// ���� Ŭ��
				}
				if (e.getClickCount() == 2) { // ���� Ŭ��
				}
				if (e.getButton() == 3) {// ������ Ŭ��
					int column = table.columnAtPoint(e.getPoint());
					int row = table.rowAtPoint(e.getPoint());
					table.changeSelection(row, column, false, false);
					popup.show(table, e.getX(), e.getY());
				}
			}
		});

		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}

	}

	public void createInputP() {
		tab_1_inputP.setLayout(new BoxLayout(tab_1_inputP, BoxLayout.X_AXIS));
		for (int i = 0; i < indata.length; i++) {
			tab_1_inputP.add(indata[i] = new JTextField());
		}
		JButton addB = new JButton("Add");
		tab_1_inputP.add(addB);
		addB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				for (int i = 0; i < indata.length; i++) {
					in[i] = indata[i].getText();
					indata[i].setText("");
				}
				tableModel.addRow(in);
				saveToDB(in);
			}
		});

		JButton modB = new JButton("Mod");
		tab_1_inputP.add(modB);
		modB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (modIntRow != -1) {
					String in[] = new String[5];
					for (int i = 0; i < indata.length; i++) {
						in[i] = indata[i].getText();
						delTableRow(modIntRow);
						tableModel.insertRow(modIntRow, in);
					}
					modIntRow = -1;
				}
			}
		});
		JButton OrderB = new JButton("Order");
		tab_1_inputP.add(OrderB);
		OrderB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (OrderIntRow != -1) {
					String in[] = new String[5];
					for (int i = 0; i < indata.length; i++) {
						in[i] = indata[i].getText();
						delTableRow(OrderIntRow);
						tableModel.insertRow(OrderIntRow, in);
					}
					OrderIntRow = -1;
				}
			}
		});

		JButton delB = new JButton("Del");
		tab_1_inputP.add(delB);
		delB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() == -1) {
					return;
				} else {
					delTableRow(table.getSelectedRow());
				}
			}
		});

		JButton saveB = new JButton("Save");
		tab_1_inputP.add(saveB);
		saveB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//saveToDB();
			}
		});
	}

	public void saveToDB(String[] in) {
		DTOProduct newDTO = new DTOProduct();
		
		newDTO.setName(in[0]);
		newDTO.setJ1(in[1]);
		newDTO.setJ2(in[2]);
		newDTO.setJ3(in[3]);
		newDTO.setJ4(in[4]);
		sqlDAO.InsertProduct(newDTO);
	}

	public void createTabbedP() {
		tab_1.setLayout(new BorderLayout());
		tab_1.add(tableScroll, "Center");
		tab_1.add(tab_1_inputP, "South");
		tabPane.add("���ڷ�", tab_1);

		tab_2.setLayout(new BorderLayout());
		tab_2.add(new Tab_2_Canvas(), "Center");
		tabPane.add("Image", tab_2);
	}
	

		// ��ư�� �� ���� Ŭ����

		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Mian();
		DAO DAOobj = new DAO();
		DTOProduct a = new DTOProduct();
		
		while(true) {
			
			
		}
		
	}	
	
	
	
	
	
	//	int r= DAOobj.InsertProduct(a);
		// 2. ������ ����
//		a = new DTOProduct();
//		a.setName("����Ĩ");
//		a.setJ1("12");
//		a.setJ2("3000");
//		a.setJ3("0901");
//		a.setJ4("1");
//		DAOobj.InsertProduct(a);
		
		
		//DAOobj.delOne(a);
	
	

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
	
			
			
		}


	
		
	

}

package java0324frame;
import java.util.ArrayList;
public class ServerCenter {
	private ArrayList<ServerChat> sList = new ArrayList<>();
	public void addServerChat(ServerChat s) {
		this.sList.add(s);

	}

	public void reMsg(String msg, String id) {
		// mes type
		// /to kkk hi~ good!
		// /to+id+�޼��� ������ �Է� �ϸ�  �ش� id ���׸� �޼����� ���� �ȴ�.
		if (msg.indexOf("/to") == 0) {
			// /to �� �Է� �ϸ� ������ recive end ��� �߸鼭  �ش� client ����
			int firstInt = msg.indexOf(" ") + 1; 
			//indexOf�� ���ڿ� �ε��� ��ȯ �ϴ°�, �޼��� ���� ������ ù��° ���ڿ� �ε����� ��ȯ  firstInt
			int endInt = msg.indexOf(" ", firstInt);
			//firstInt���� ���� ������ �ִµ���  endInt 
			String targetID = msg.substring(firstInt, endInt);
			// subString return the fisrtInt till endInt.
			//indexOf�� firstInt �� endInt�� �ε��� �ѹ��� ã���� substring���� return
			String targetMsg = "[@" + id + "]" + msg.substring(endInt + 1);
			//�ӼӸ� �޴� ������� @�� �Ƶ�� ���� ���� @�� ������ �ӼӸ� ���±��� �˼�����
			sendOne(targetID, targetMsg);
		} else {
	
		sendAll("[" + id + "]" + msg);
	}

	}
	public void sendOne(String targetID,String targetMsg) {
		for (int i = 0; i < sList.size(); i++) {
			if (targetID.equals(sList.get(i).getMyId())) {
				sList.get(i).send(targetMsg);
			} 
		}
	}

	public void sendAll(String msg) {
		for (int i = 0; i < sList.size(); i++) {
			sList.get(i).send(msg);
		}
	}
}

/**
 * 
 */
package jimmy.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author JH
 *
 */
public class j2se {
	static Log4j log4j = new Log4j();
	static Logger logger = log4j.getLogger(j2se.class.getName());
	private String name; // ��������
	private String author; // ��������
	private String ISBN; // �������

	// Tips: ISBN�ǹ��ʱ�׼��ţ�ÿ���鱳��������뼴Ϊ����

	public j2se(String name, String author, String ISBN) throws IOException {
		// ���ù��췽����ʼ����
		this.name = name;
		this.author = author;
		this.ISBN = ISBN;

		// Q:�����this���÷���
	}

	public String getName() { // �������
		return name;
	}

	public String getAuthor() { // �������
		return author;
	}

	public String getISBN() { // ��ñ���
		return ISBN;
	}

	public String toupper(String str) {
		String upstr = null;
		upstr = str.toUpperCase();
		return upstr;
	}

	public void getlist() {
		List<Integer> list = new ArrayList<Integer>();
		// �����б�list

		for (int i = 0; i < 10; i++) {
			list.add(i);
			// ʹ��add()������ͨ��forѭ����list������10��Ԫ�أ�ÿ��Ԫ�ؼ���ǰѭ���Ĵ���ֵ
		}

		logger.info("Items in the list��");
		logger.info(list);
		// ����б���ȫ����Ԫ��
		System.out.println("");
		logger.info("Items in the list with odd index:");
		for (int i = 1; i < list.size(); i += 2) {
			// ����б������Ϊ������Ԫ�أ�ע�� i += 2 ���÷�

			logger.info(list.get(i) + "  ");
			// ʹ��get()������list��ȡ��Ԫ��
		}
	}

	public void getmap() {
		HashMap<String, String> phonebook = new HashMap<String, String>();
		// ����һ��HashMap��������Ϊphonebook����ֵ�Ե����;�ΪString

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		// ����ɨ�������ڻ�ȡ�û�������

		String keyword = new String();
		// ����һ����Ϊkeyword���ַ������ڴ���û�����Ĺؼ���

		phonebook = initPhoneBook(phonebook);
		// ������д��initPhoneBook����Ϊ�绰��װ�����ݣ�������ʼ���Ĳ���

		logger.info("Please input a name that you want to search(Bob,Steve,Peter):");
		// ��ʾ�û��������ѯ������

		keyword = scan.nextLine();
		// ���û���������Ϣװ���ַ���keyword��

		if (keyword.isEmpty()) {
			// ʹ��isEmpty()�����ж��û��Ƿ��������������ݣ���û������ʾ������
			logger.info("Please input a name!");
		} else {

			// ���û�������ĳ���ؼ��ʣ����ոùؼ�����phonebook�в������Ӧ�ĺ���
			logger.info("The result is :");
			logger.info(queryPhone(phonebook, keyword));
			// �����������д��queryPhone()����
		}
		for (int i = 0; i < 10; i++) {
			logger.info(queryPhone(phonebook, Integer.toString(i)));
		}

	}

	private static HashMap<String, String> initPhoneBook(
			HashMap<String, String> phonebook) {
		// �÷����������ָ��HashMap�ĳ�ʼ����Ϊ��װ��һЩ�������ݣ���Ҳ�����Զ�����Щ����
		// Q���㻹����βκ�ʵ�ε�������

		phonebook.put("Steve", "13012345678");
		phonebook.put("Bob", "028-80001234");
		phonebook.put("Peter", "182222233333");
		for (int i = 0; i < 100; i++) {
			phonebook.put(Integer.toString(i), Integer.toString(i * 100));
		}
		// ʹ��put()������������绰������������������Ϊphonebook��HashMap��
		// put()�����У���һ������Ϊ�ؼ���key���ڶ�������Ϊ���Ӧ��ֵvalue

		return phonebook;
		// �����޸ĺ��phonebook
	}

	private static String queryPhone(HashMap<String, String> phonebook,
			String keyword) {

		String result = new String();
		// ������Ž�����ַ���result

		result = phonebook.get(keyword);
		// ʹ��get()������ѯkeyword����Ӧ�ĵ绰���룬������result
		// put()�����У�����Ϊ�ؼ���key������ֵΪ���Ӧ��ֵvalue��δ�ҵ���Ӧֵʱ������ֵΪnull

		if (result == null)
			return "Can not find this user.";
		// ���δ�ҵ����û��ĵ绰���룬�򽫲�ѯ����޸�Ϊ��δ�ҵ����û���

		return result;
		// ���ز�ѯ���
	}

	// ��ȡǰ���������Ϣ����
	public List<String> getHeadNumber(int lang, int num) {
		List<String> list = new ArrayList<String>();
		// ������Ʊ��ǰ��κ��뼯��
		String lotteryNumber = "";
		for (int i = 1; i < lang; i++) {
			// ���ѭ����Ϊ�˳�ʼ����Ʊ��ǰ��κ��뼯��

			if (i < 10) {
				list.add("0" + i + "  ");
				// �ڼ��������0~9�ĺ��룬��Ϊ�Ǹ�λ����Ϊ����ʾ�ϸ������Ի���������Ҫ��ʮλ�ĵط���ӡ�0��
			} else {
				list.add("" + i + "  ");
				// �򼯺���Ӵ���9�ĺ��룬�����˫λ��
			}
		}

		int roundIndex = 0;
		// ��������ʼ�������

		List<String> lotteryList = new ArrayList<String>();
		// ����ǰ�κ����List����

		for (int j = 0; j < num; j++) {
			int amount = list.size(); // ��ȡǰ��κ���ĸ���
			Random r = new Random(); // ������ʵ����Random�Ķ���
			roundIndex = r.nextInt(amount); // ��ȡһ��0��amount-1�������
			lotteryNumber = list.get(roundIndex); // ��ȡ��Ʊ���֣���ҡ�ŵĺ���
			lotteryList.add(lotteryNumber); // ����Ʊ�������lotteryList��
			list.remove(roundIndex); // �Ƴ��ող����ĺ���
		}
		Collections.sort(lotteryList);
		// ��ǰ��κ���������������Ŀ����Ϊ���ý�����߿ɶ���
		return lotteryList;
		// ����ǰ��κ���
	}

	// ��ȡ��������
	public List<String> getRearNumber(int Rlang) {
		List<String> list = new ArrayList<String>();
		// �������κ��뼯�ϣ�Ҳ������������������

		String lotteryNumber = "";
		for (int i = 1; i < Rlang; i++) {
			// ��ʼ�����κ��뼯��

			if (i < 10) {
				list.add("0" + i + "  ");
				// ���0~9�ĺ��룬ԭ��ͬǰ���
			} else {
				list.add("" + i + "  ");
				// ��Ӵ���9�ĺ���
			}
		}
		int roundIndex = 0;
		// ��������ʼ�������

		List<String> lotteryList = new ArrayList<String>();
		// ������κ����List����

		for (int j = 0; j < 2; j++) {
			int amount = list.size(); // ��ȡ���κ���ĸ���
			Random r = new Random(); // ������ʵ����Random�Ķ���
			roundIndex = r.nextInt(amount); // ��ȡһ��0��amount-1�������
			lotteryNumber = list.get(roundIndex); // ҡ��
			lotteryList.add(lotteryNumber); // ����Ʊ�������lotteryList��
			list.remove(roundIndex); // �Ƴ��ող����ĺ���
		}

		Collections.sort(lotteryList);
		// �Ժ��κ����������
		return lotteryList;
	}

	// ��ȡ��Ʊ����
	public void generateLottery(String groupNum) {

		int groupNumber = 0;
		// Ϊ�˱��ⲻ��Ҫ�Ĵ���һ���ڴ�������ʱ��ҪΪ�丳��ʼֵ
		int Hlang = 36;
		int num = 5;
		int Rlang = 13;
		groupNumber = Integer.parseInt(groupNum);
		StringBuilder sbuiler = new StringBuilder();
		// �����ַ�������������ʹ���ַ����������ܹ���Ϊ��������ַ�����׷������

		for (int i = 0; i < groupNumber; i++) {
			List<String> startList = getHeadNumber(Hlang, num);
			// ��ò�Ʊǰ��κ���ļ���

			List<String> endList = getRearNumber(Rlang);
			// ��ò�Ʊ���κ���ļ���
			sbuiler.append("\n");
			for (int m = 0; m < startList.size(); m++) {

				sbuiler.append(startList.get(m));
				// append()��Ϊ׷�ӷ�����������Ӳ�Ʊ��ǰ��κ��뵽�ַ�����������
			}
			sbuiler.append("    ");
			for (int n = 0; n < endList.size(); n++) {
				sbuiler.append(endList.get(n));
				// ��Ӳ�Ʊ�ĺ��κ��뵽�ַ�����������
			}
			sbuiler.append("\n");
		}

		logger.info(sbuiler.toString());
		// ��ÿ�����ɺõĲ�Ʊ���뼴ʱ���
	}

	public static void main(String[] args) {
		logger.info("test");
		String name = " a11a-=Z ";
		int chr = 0;
		int num = 0;
		int eng = 0;
		for (int i = 0; i < name.trim().length(); i++) {
			char cat = name.trim().toLowerCase().charAt(i);
			if (cat >= '0' && cat <= '9') {
				logger.info("num��" + cat);
				num = num + 1;
			} else if (cat >= 'a' && cat <= 'z') {
				eng = eng + 1;
				logger.info("eng :" + cat);
			} else {
				chr = chr + 1;
				logger.info("char��" + cat);
			}
		}
		logger.info("���֣�" + num);
		logger.info("�ַ���" + chr);
		logger.info("eng��" + eng);
	}
}

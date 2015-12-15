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
	private String name; // 定义书名
	private String author; // 定义作者
	private String ISBN; // 定义编码

	// Tips: ISBN是国际标准书号，每本书背面的条形码即为此物

	public j2se(String name, String author, String ISBN) throws IOException {
		// 利用构造方法初始化域
		this.name = name;
		this.author = author;
		this.ISBN = ISBN;

		// Q:你清楚this的用法吗？
	}

	public String getName() { // 获得书名
		return name;
	}

	public String getAuthor() { // 获得作者
		return author;
	}

	public String getISBN() { // 获得编码
		return ISBN;
	}

	public String toupper(String str) {
		String upstr = null;
		upstr = str.toUpperCase();
		return upstr;
	}

	public void getlist() {
		List<Integer> list = new ArrayList<Integer>();
		// 创建列表list

		for (int i = 0; i < 10; i++) {
			list.add(i);
			// 使用add()方法，通过for循环向list中增加10个元素，每个元素即当前循环的次数值
		}

		logger.info("Items in the list：");
		logger.info(list);
		// 输出列表中全部的元素
		System.out.println("");
		logger.info("Items in the list with odd index:");
		for (int i = 1; i < list.size(); i += 2) {
			// 输出列表中序号为奇数的元素，注意 i += 2 的用法

			logger.info(list.get(i) + "  ");
			// 使用get()方法从list中取出元素
		}
	}

	public void getmap() {
		HashMap<String, String> phonebook = new HashMap<String, String>();
		// 创建一个HashMap对象，名称为phonebook，键值对的类型均为String

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		// 创建扫描器用于获取用户的输入

		String keyword = new String();
		// 创建一个名为keyword的字符串用于存放用户输入的关键词

		phonebook = initPhoneBook(phonebook);
		// 利用自写的initPhoneBook方法为电话簿装入数据，完成其初始化的步骤

		logger.info("Please input a name that you want to search(Bob,Steve,Peter):");
		// 提示用户输入待查询的姓名

		keyword = scan.nextLine();
		// 将用户的输入信息装入字符串keyword中

		if (keyword.isEmpty()) {
			// 使用isEmpty()方法判断用户是否真正输入了内容，如没有则提示其输入
			logger.info("Please input a name!");
		} else {

			// 若用户有输入某个关键词，则按照该关键词在phonebook中查找其对应的号码
			logger.info("The result is :");
			logger.info(queryPhone(phonebook, keyword));
			// 这里调用了自写的queryPhone()方法
		}
		for (int i = 0; i < 10; i++) {
			logger.info(queryPhone(phonebook, Integer.toString(i)));
		}

	}

	private static HashMap<String, String> initPhoneBook(
			HashMap<String, String> phonebook) {
		// 该方法用于完成指定HashMap的初始化，为其装入一些号码数据，你也可以自定义这些数据
		// Q：你还清楚形参和实参的区别吗？

		phonebook.put("Steve", "13012345678");
		phonebook.put("Bob", "028-80001234");
		phonebook.put("Peter", "182222233333");
		for (int i = 0; i < 100; i++) {
			phonebook.put(Integer.toString(i), Integer.toString(i * 100));
		}
		// 使用put()方法将姓名与电话号码相关联，存放在名为phonebook的HashMap中
		// put()方法中，第一个参数为关键词key，第二个参数为其对应的值value

		return phonebook;
		// 返回修改后的phonebook
	}

	private static String queryPhone(HashMap<String, String> phonebook,
			String keyword) {

		String result = new String();
		// 创建存放结果的字符串result

		result = phonebook.get(keyword);
		// 使用get()方法查询keyword所对应的电话号码，并赋给result
		// put()方法中，参数为关键词key，返回值为其对应的值value，未找到对应值时，返回值为null

		if (result == null)
			return "Can not find this user.";
		// 如果未找到该用户的电话号码，则将查询结果修改为“未找到该用户”

		return result;
		// 返回查询结果
	}

	// 获取前半段数字信息内容
	public List<String> getHeadNumber(int lang, int num) {
		List<String> list = new ArrayList<String>();
		// 创建彩票的前半段号码集合
		String lotteryNumber = "";
		for (int i = 1; i < lang; i++) {
			// 这个循环是为了初始化彩票的前半段号码集合

			if (i < 10) {
				list.add("0" + i + "  ");
				// 在集合中添加0~9的号码，因为是个位数，为了显示上更加人性化，所以需要在十位的地方添加“0”
			} else {
				list.add("" + i + "  ");
				// 向集合添加大于9的号码，即添加双位数
			}
		}

		int roundIndex = 0;
		// 创建并初始化随机数

		List<String> lotteryList = new ArrayList<String>();
		// 保存前段号码的List集合

		for (int j = 0; j < num; j++) {
			int amount = list.size(); // 获取前半段号码的个数
			Random r = new Random(); // 创建并实例化Random的对象
			roundIndex = r.nextInt(amount); // 获取一个0到amount-1的随机数
			lotteryNumber = list.get(roundIndex); // 获取彩票数字，即摇号的号码
			lotteryList.add(lotteryNumber); // 将彩票号码添加lotteryList中
			list.remove(roundIndex); // 移除刚刚产生的号码
		}
		Collections.sort(lotteryList);
		// 对前半段号码进行排序，排序的目的是为了让结果更具可读性
		return lotteryList;
		// 返回前半段号码
	}

	// 获取后半段内容
	public List<String> getRearNumber(int Rlang) {
		List<String> list = new ArrayList<String>();
		// 创建后半段号码集合，也就是最后两个球的数字

		String lotteryNumber = "";
		for (int i = 1; i < Rlang; i++) {
			// 初始化后半段号码集合

			if (i < 10) {
				list.add("0" + i + "  ");
				// 添加0~9的号码，原理同前半段
			} else {
				list.add("" + i + "  ");
				// 添加大于9的号码
			}
		}
		int roundIndex = 0;
		// 创建并初始化随机数

		List<String> lotteryList = new ArrayList<String>();
		// 保存后半段号码的List集合

		for (int j = 0; j < 2; j++) {
			int amount = list.size(); // 获取后半段号码的个数
			Random r = new Random(); // 创建并实例化Random的对象
			roundIndex = r.nextInt(amount); // 获取一个0到amount-1的随机数
			lotteryNumber = list.get(roundIndex); // 摇号
			lotteryList.add(lotteryNumber); // 将彩票号码添加lotteryList中
			list.remove(roundIndex); // 移除刚刚产生的号码
		}

		Collections.sort(lotteryList);
		// 对后半段号码进行排序
		return lotteryList;
	}

	// 获取彩票号码
	public void generateLottery(String groupNum) {

		int groupNumber = 0;
		// 为了避免不必要的错误，一般在创建变量时都要为其赋初始值
		int Hlang = 36;
		int num = 5;
		int Rlang = 13;
		groupNumber = Integer.parseInt(groupNum);
		StringBuilder sbuiler = new StringBuilder();
		// 创建字符串生成器对象，使用字符串生成器能够较为方便地在字符串中追加内容

		for (int i = 0; i < groupNumber; i++) {
			List<String> startList = getHeadNumber(Hlang, num);
			// 获得彩票前半段号码的集合

			List<String> endList = getRearNumber(Rlang);
			// 获得彩票后半段号码的集合
			sbuiler.append("\n");
			for (int m = 0; m < startList.size(); m++) {

				sbuiler.append(startList.get(m));
				// append()即为追加方法，用于添加彩票的前半段号码到字符串生成器中
			}
			sbuiler.append("    ");
			for (int n = 0; n < endList.size(); n++) {
				sbuiler.append(endList.get(n));
				// 添加彩票的后半段号码到字符串生成器中
			}
			sbuiler.append("\n");
		}

		logger.info(sbuiler.toString());
		// 将每组生成好的彩票号码即时输出
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
				logger.info("num：" + cat);
				num = num + 1;
			} else if (cat >= 'a' && cat <= 'z') {
				eng = eng + 1;
				logger.info("eng :" + cat);
			} else {
				chr = chr + 1;
				logger.info("char：" + cat);
			}
		}
		logger.info("数字：" + num);
		logger.info("字符：" + chr);
		logger.info("eng：" + eng);
	}
}

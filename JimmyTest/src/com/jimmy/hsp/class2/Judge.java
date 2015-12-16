/**
 * 
 */
package com.jimmy.hsp.class2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author jimmy
 *
 */
public class Judge {
	Logger logger = new Log4j().getLogger(Judge.class.getName());
	Scanner sc = new Scanner(System.in);
	float lastScore = 0.0f;

	public void getJudgeScore() {

		Random rd = new Random();

		try {
			while (true) {
				logger.info("跳水评分系统");
				logger.info("1、裁判打分");
				logger.info("2、查看得分");
				logger.info("3、查看最高分与最低分");
				logger.info("0、退出");
				Scanner sr = new Scanner(System.in);
				int num = sr.nextInt();

				// 裁判打分
				if (num == 1) {
					logger.info("请输入裁判人数:\n");

					int refereeNum = sr.nextInt();
					float[] fl = new float[refereeNum];
					for (int i = 0; i < refereeNum; i++) {
						fl[i] = (float) (Math.round(rd.nextFloat() * 10000) / 100.00);
					}
					Judge jd = new Judge();
					ArrayList<Referee> al = jd.getReferee(refereeNum, fl);
					float lastScore = (float) (Math
							.round(jd.getScore(al) * 100) / 100.00);
					logger.debug("FINAL SCORE IS :" + lastScore);
				}
				// 退出系统
				else if (num == 0) {
					logger.info("已正常退出系统");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 创建裁判组
	 * 
	 * @param refNum
	 *            裁判的人数
	 * @return 获取裁判数组
	 */
	public ArrayList<Referee> getReferee(int refNum, float[] score) {
		/**
		 * 
		 */
		ArrayList<Referee> al = new ArrayList();
		for (int i = 0; i < refNum; i++) {
			Referee ref = new Referee();
			ref.setRefereeName(i + "号裁判");
			ref.setScore(score[i]);
			al.add(ref);
			logger.info(ref.getRefereeName() + "给出评分是：" + ref.getScore());
		}
		return al;
	}

	/**
	 * 获取选手的分数
	 * 
	 * @param ref
	 * @return
	 */
	public float getScore(ArrayList<Referee> ref) {
		float score = 0.0f;
		Referee ref1 = new Referee();
		Referee ref2 = new Referee();
		for (int i = 0; i < ref.size() - 1; i++) {
			for (int j = 0; j < ref.size() - i - 1; j++) {
				if (ref.get(j).getScore() > ref.get(j + 1).getScore()) {
					ref1 = ref.get(j);
					ref2 = ref.get(j + 1);
					ref.set(j, ref2);
					ref.set(j + 1, ref1);
				}

			}

		}
		// for (Iterator<Referee> it = ref.iterator(); it.hasNext();) {
		// Referee refer = it.next();
		// logger.debug(refer.getRefereeName() + ":" + refer.getScore());
		// }
		for (int i = 1; i < ref.size() - 1; i++) {
			score = score + ref.get(i).getScore();
		}
		return score / (ref.size() - 2);
	}

	/**
	 * 获取最好裁判
	 * 
	 * @param ref
	 * @param lastscore
	 */
	public void getBestReferee(ArrayList<Referee> ref, float lastscore) {

	}

	/**
	 * 获取最差裁判
	 * 
	 * @param ref
	 * @param lastscore
	 */
	public void getWorstReferee(ArrayList<Referee> ref, float lastscore) {

	}
}

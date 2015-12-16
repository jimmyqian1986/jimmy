/**
 * @author jimmy
 */
package com.jimmy.hsp.class2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

public class Judge {
	Logger logger = new Log4j().getLogger(Judge.class.getName());
	Scanner sc = new Scanner(System.in);
	float lastScore = 0.0f;

	public void getJudgeScore() {

		Random rd = new Random();
		Scanner sr = new Scanner(System.in);

		Judge jd = new Judge();
		int refereeNum = 0;
		float[] fl = null;
		ArrayList<Referee> al = null;
		float lastScore = 0.0f;
		int num = 0;
		int times = 0;
		while (times < 3) {
			try {

				logger.info("跳水评分系统");
				logger.info("1、裁判打分");
				logger.info("2、查看得分");
				logger.info("3、查看最好与最差裁判");
				logger.info("4、查看所有裁判评分");
				logger.info("0、退出");
				num = sr.nextInt();
				// 裁判打分
				if (num < 0 || num > 4) {
					times++;
					logger.info("请输入0,1,2,3,4进行选择");
					continue;
				}
				if (num == 1) {
					logger.info("请输入裁判人数:\n");

					refereeNum = sr.nextInt();
					fl = new float[refereeNum];
					for (int i = 0; i < refereeNum; i++) {
						fl[i] = (float) (Math.round(rd.nextFloat() * 10000) / 100.00);
					}

				}
				if (refereeNum == 0) {
					logger.error("请先进行裁判打分");
					continue;
				}
				al = jd.getReferee(refereeNum, fl);
				if (num == 2) {

					lastScore = (float) (Math.round(jd.getScore(al) * 100) / 100.00);
					logger.info("FINAL SCORE IS :" + lastScore);
				}
				if (num == 3) {
					Referee refBest = al.get(jd.getBestReferee(al, lastScore));
					Referee refWorst = al
							.get(jd.getWorstReferee(al, lastScore));
					logger.info("{}裁判最好,分数为：{},{}裁判最差打分是:{}",
							refBest.getRefereeName(), refBest.getScore(),
							refWorst.getRefereeName(), refWorst.getScore());
				}
				if (num == 4) {
					jd.getAllRefereeScore(al);
				}
				// 退出系统
				if (num == 0) {
					sr.close();
					logger.info("已正常退出系统");
					System.exit(0);
				}

			} catch (InputMismatchException ie) {
				logger.error("请输入数字0-4，进行选择");
				// ie.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				logger.error("系统异常退出");
				System.exit(1);
			}
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
			// logger.info(ref.getRefereeName() + "给出评分是：" + ref.getScore());
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
	public int getBestReferee(ArrayList<Referee> ref, float lastscore) {
		// float lastFen = lastscore;
		float tempHao = 0f;
		// 我认为第一个评委打分是最好的
		int bestIndex = 0;
		float hao = Math.abs(ref.get(bestIndex).getScore() - lastscore);// 使用Math来返回一个数学绝对值
		for (int i = 1; i < ref.size(); i++) {
			tempHao = Math.abs(ref.get(i).getScore() - lastscore);
			if (hao > tempHao) {
				bestIndex = i;
				hao = tempHao;
			}
		}
		return bestIndex;
	}

	/**
	 * 获取最差裁判
	 * 
	 * @param ref
	 * @param lastscore
	 */
	public int getWorstReferee(ArrayList<Referee> ref, float lastscore) {
		float lastFen = lastscore;
		float tempHao = 0f;
		// 我认为第一个评委打分是最好的
		int worstIndex = 0;
		float hao = Math.abs(ref.get(worstIndex).getScore() - lastFen);// 使用Math来返回一个数学绝对值
		for (int i = 1; i < ref.size(); i++) {
			tempHao = Math.abs(ref.get(i).getScore() - lastFen);
			if (hao < tempHao) {
				worstIndex = i;
				hao = tempHao;
			}
		}
		return worstIndex;
	}

	public void getAllRefereeScore(ArrayList<Referee> ref) {
		String errmsg = "请先进行打分";
		if (ref.size() == 0) {
			logger.error("{}", errmsg);
		}
		for (Iterator<Referee> it = ref.iterator(); it.hasNext();) {
			Referee refer = it.next();
			logger.debug(refer.getRefereeName() + ":" + refer.getScore());
		}
	}
}

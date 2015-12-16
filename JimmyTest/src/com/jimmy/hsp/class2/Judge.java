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
				logger.info("��ˮ����ϵͳ");
				logger.info("1�����д��");
				logger.info("2���鿴�÷�");
				logger.info("3���鿴��߷�����ͷ�");
				logger.info("0���˳�");
				Scanner sr = new Scanner(System.in);
				int num = sr.nextInt();

				// ���д��
				if (num == 1) {
					logger.info("�������������:\n");

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
				// �˳�ϵͳ
				else if (num == 0) {
					logger.info("�������˳�ϵͳ");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����������
	 * 
	 * @param refNum
	 *            ���е�����
	 * @return ��ȡ��������
	 */
	public ArrayList<Referee> getReferee(int refNum, float[] score) {
		/**
		 * 
		 */
		ArrayList<Referee> al = new ArrayList();
		for (int i = 0; i < refNum; i++) {
			Referee ref = new Referee();
			ref.setRefereeName(i + "�Ų���");
			ref.setScore(score[i]);
			al.add(ref);
			logger.info(ref.getRefereeName() + "���������ǣ�" + ref.getScore());
		}
		return al;
	}

	/**
	 * ��ȡѡ�ֵķ���
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
	 * ��ȡ��ò���
	 * 
	 * @param ref
	 * @param lastscore
	 */
	public void getBestReferee(ArrayList<Referee> ref, float lastscore) {

	}

	/**
	 * ��ȡ������
	 * 
	 * @param ref
	 * @param lastscore
	 */
	public void getWorstReferee(ArrayList<Referee> ref, float lastscore) {

	}
}

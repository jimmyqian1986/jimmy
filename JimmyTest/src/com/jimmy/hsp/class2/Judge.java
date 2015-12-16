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

				logger.info("��ˮ����ϵͳ");
				logger.info("1�����д��");
				logger.info("2���鿴�÷�");
				logger.info("3���鿴�����������");
				logger.info("4���鿴���в�������");
				logger.info("0���˳�");
				num = sr.nextInt();
				// ���д��
				if (num < 0 || num > 4) {
					times++;
					logger.info("������0,1,2,3,4����ѡ��");
					continue;
				}
				if (num == 1) {
					logger.info("�������������:\n");

					refereeNum = sr.nextInt();
					fl = new float[refereeNum];
					for (int i = 0; i < refereeNum; i++) {
						fl[i] = (float) (Math.round(rd.nextFloat() * 10000) / 100.00);
					}

				}
				if (refereeNum == 0) {
					logger.error("���Ƚ��в��д��");
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
					logger.info("{}�������,����Ϊ��{},{}�����������:{}",
							refBest.getRefereeName(), refBest.getScore(),
							refWorst.getRefereeName(), refWorst.getScore());
				}
				if (num == 4) {
					jd.getAllRefereeScore(al);
				}
				// �˳�ϵͳ
				if (num == 0) {
					sr.close();
					logger.info("�������˳�ϵͳ");
					System.exit(0);
				}

			} catch (InputMismatchException ie) {
				logger.error("����������0-4������ѡ��");
				// ie.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				logger.error("ϵͳ�쳣�˳�");
				System.exit(1);
			}
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
			// logger.info(ref.getRefereeName() + "���������ǣ�" + ref.getScore());
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
	public int getBestReferee(ArrayList<Referee> ref, float lastscore) {
		// float lastFen = lastscore;
		float tempHao = 0f;
		// ����Ϊ��һ����ί�������õ�
		int bestIndex = 0;
		float hao = Math.abs(ref.get(bestIndex).getScore() - lastscore);// ʹ��Math������һ����ѧ����ֵ
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
	 * ��ȡ������
	 * 
	 * @param ref
	 * @param lastscore
	 */
	public int getWorstReferee(ArrayList<Referee> ref, float lastscore) {
		float lastFen = lastscore;
		float tempHao = 0f;
		// ����Ϊ��һ����ί�������õ�
		int worstIndex = 0;
		float hao = Math.abs(ref.get(worstIndex).getScore() - lastFen);// ʹ��Math������һ����ѧ����ֵ
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
		String errmsg = "���Ƚ��д��";
		if (ref.size() == 0) {
			logger.error("{}", errmsg);
		}
		for (Iterator<Referee> it = ref.iterator(); it.hasNext();) {
			Referee refer = it.next();
			logger.debug(refer.getRefereeName() + ":" + refer.getScore());
		}
	}
}

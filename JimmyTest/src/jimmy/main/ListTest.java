/**
 * 
 */
package jimmy.main;

/**
 * @author JH
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;




import org.apache.logging.log4j.Logger;

import jimmy.commons.Log4j;
/*
 * ����ѧ����
 */
public class ListTest {
/*
 * ��ȡlogger ���¼��־
 */
	Log4j log4j=new Log4j();
	Logger logger=log4j.getLogger(ListTest.class.getName());
    /*
     * ���ڴ��ѧ����List
     */
    public List students;
    public ListTest() {
        this.students = new ArrayList();
    }

    /*
     * ������students�����ѧ��
     */
    public void testAdd() {
        //����һ��ѧ�����󣬲�ͨ������add��������ӵ�ѧ������List��
        Student st1 = new Student("1", "����","��");
        students.add(st1);

        //��ӵ�List�е����;�ΪObject������ȡ��ʱ����Ҫǿת
        Student temp = (Student)students.get(0);
        logger.trace("�����ѧ����" + temp.id + ":" + temp.name+"���Ԅe��"+temp.sex);

        Student st2 = new Student("2","����","Ů");
        students.add(0, st2);
        Student temp2 = (Student)students.get(0);
        logger.trace("�����ѧ����" + temp2.id + ":" +temp2.name+"���Ԅe��"+temp.sex);

        Student[] student = {new Student("3", "����","��"),new Student("4", "����","Ů")};
        students.addAll(Arrays.asList(student));
        Student temp3 = (Student)students.get(2);
        Student temp4 = (Student)students.get(3);
        logger.trace("�����ѧ����" + temp3.id + ":" +temp3.name+"���Ԅe��"+temp.sex);
        logger.trace("�����ѧ����" + temp4.id + ":" +temp4.name+"���Ԅe��"+temp.sex);
        Student[] student2 = {new Student("5", "����","��"),new Student("6", "�԰�","��"),new Student("7","jimmy","��")};
        students.addAll(2,Arrays.asList(student2));
        Student temp5 = (Student)students.get(2);
        Student temp6 = (Student)students.get(3);
        logger.trace("�����ѧ����" + temp5.id + ":" +temp5.name+"���Ԅe��"+temp.sex);
        logger.trace("�����ѧ����" + temp6.id + ":" +temp6.name+"���Ԅe��"+temp.sex);

    }

    /*
     * ȡ��List�е�Ԫ�صķ���
     */
    public void testGet() {
        int size = students.size();
        for(int i = 0;i<size;i++){
            Student st = (Student)students.get(i);
            logger.trace("ѧ����" + st.id+":"+ st.name+"���Ԅe��"+st.sex);

        }
    }

    /*
     * ͨ��������������
     */
    public void testIterator() {
        //ͨ�����ϵ�iterator������ȡ�õ�����ʵ��
        Iterator it = students.iterator();
        logger.trace("������ѧ����ͨ�����������ʣ���");
        while(it.hasNext()){

            Student st = (Student)it.next();
            logger.trace("ѧ��" + st.id + ":" +st.name+"���Ԅe��"+st.sex);
        }
    }

    /**
     * ͨ��for each �������ʼ���Ԫ��
     * @param args
     */
    public void testForEach() {
        logger.trace("������ѧ����ͨ��for each����");
        for(Object obj:students){
            Student st = (Student)obj;
            logger.trace("ѧ����" + st.id + ":" + st.name+"���Ԅe��"+st.sex);
        }
    }

    /**
     * �޸�List�е�Ԫ��
     * @param args
     */
    public void testModify(){
        students.set(4,new Student("3","���","��"));
    }

    /**
     * ɾ��List�е�Ԫ��
     * @param args
     */
    public void  testRemove() {
        Student st = (Student)students.get(4);
        logger.trace("����ѧ����" + st.id + ":" + st.name + "���Ҽ�����ɾ��"+"���Ԅe��"+st.sex);
        students.remove(st);
        logger.trace("�ɹ�ɾ��ѧ����");
        testForEach();

    }

    public static void main(String[] args) {
        ListTest lt =  new ListTest();
        lt.testAdd();
        lt.testGet();
        lt.testIterator();
        lt.testModify();
        lt.testForEach();
        lt.testRemove();

    }
}
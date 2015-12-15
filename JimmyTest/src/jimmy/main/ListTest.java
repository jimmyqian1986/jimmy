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
 * 管理学生类
 */
public class ListTest {
/*
 * 获取logger 类记录日志
 */
	Log4j log4j=new Log4j();
	Logger logger=log4j.getLogger(ListTest.class.getName());
    /*
     * 用于存放学生的List
     */
    public List students;
    public ListTest() {
        this.students = new ArrayList();
    }

    /*
     * 用于往students中添加学生
     */
    public void testAdd() {
        //创建一个学生对象，并通过调用add方法，添加到学生管理List中
        Student st1 = new Student("1", "张三","男");
        students.add(st1);

        //添加到List中的类型均为Object，所以取出时还需要强转
        Student temp = (Student)students.get(0);
        logger.trace("添加了学生：" + temp.id + ":" + temp.name+"，性別："+temp.sex);

        Student st2 = new Student("2","李四","女");
        students.add(0, st2);
        Student temp2 = (Student)students.get(0);
        logger.trace("添加了学生：" + temp2.id + ":" +temp2.name+"，性別："+temp.sex);

        Student[] student = {new Student("3", "王五","男"),new Student("4", "马六","女")};
        students.addAll(Arrays.asList(student));
        Student temp3 = (Student)students.get(2);
        Student temp4 = (Student)students.get(3);
        logger.trace("添加了学生：" + temp3.id + ":" +temp3.name+"，性別："+temp.sex);
        logger.trace("添加了学生：" + temp4.id + ":" +temp4.name+"，性別："+temp.sex);
        Student[] student2 = {new Student("5", "周七","男"),new Student("6", "赵八","男"),new Student("7","jimmy","男")};
        students.addAll(2,Arrays.asList(student2));
        Student temp5 = (Student)students.get(2);
        Student temp6 = (Student)students.get(3);
        logger.trace("添加了学生：" + temp5.id + ":" +temp5.name+"，性別："+temp.sex);
        logger.trace("添加了学生：" + temp6.id + ":" +temp6.name+"，性別："+temp.sex);

    }

    /*
     * 取得List中的元素的方法
     */
    public void testGet() {
        int size = students.size();
        for(int i = 0;i<size;i++){
            Student st = (Student)students.get(i);
            logger.trace("学生：" + st.id+":"+ st.name+"，性別："+st.sex);

        }
    }

    /*
     * 通过迭代器来遍历
     */
    public void testIterator() {
        //通过集合的iterator方法，取得迭代器实例
        Iterator it = students.iterator();
        logger.trace("有如下学生（通过迭代器访问）：");
        while(it.hasNext()){

            Student st = (Student)it.next();
            logger.trace("学生" + st.id + ":" +st.name+"，性別："+st.sex);
        }
    }

    /**
     * 通过for each 方法访问集合元素
     * @param args
     */
    public void testForEach() {
        logger.trace("有如下学生（通过for each）：");
        for(Object obj:students){
            Student st = (Student)obj;
            logger.trace("学生：" + st.id + ":" + st.name+"，性別："+st.sex);
        }
    }

    /**
     * 修改List中的元素
     * @param args
     */
    public void testModify(){
        students.set(4,new Student("3","吴酒","男"));
    }

    /**
     * 删除List中的元素
     * @param args
     */
    public void  testRemove() {
        Student st = (Student)students.get(4);
        logger.trace("我是学生：" + st.id + ":" + st.name + "，我即将被删除"+"，性別："+st.sex);
        students.remove(st);
        logger.trace("成功删除学生！");
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
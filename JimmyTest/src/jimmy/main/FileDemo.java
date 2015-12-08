/**
 * 
 */
package jimmy.main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;
/**
 * @author JH
 *
 */
public class  FileDemo {
	 Log4j log4j = new Log4j();
	 Logger logger = log4j.getLogger(FileDemo.class.getName());
    public  void getfile(){
        //�����ҵ����ϵ�·����ͬѧ�ǿ���ֱ�������������ļ�������·��Ϊ"/root/Desktop"
        //���캯��File(String pathname)
        File f1 =new
        File("logs/1.txt");
        //File(String parent,String child)
        File f2 =new File("logs","2.txt");
        //separator ��ƽ̨�ָ���
        File f3 =new File("logs"+File.separator+"201509");
        File f4 =new File(f3,"3.txt");

        try {
            logger.info(f1);
                //���ļ�����ʱ���� false��������ʱ���� true
               logger.info(f2.createNewFile());
                //���ļ�������ʱ���� false
               logger.info(f3.delete());
        }catch(IOException e) {
                e.printStackTrace();
        }

        //�г������µ��ļ����ļ���
        File[] files =File.listRoots();
        for(File file:files){
           logger.info(file);
            if(file.length()>0){
                String[] filenames =file.list();
                for(String filename:filenames){
                   logger.info(filename);
                }
            }
        }

    }
    public  void randomfile(){

        int data_arr[] = {12, 32, 43, 45, 1, 5};
        try {
            RandomAccessFile randf=new RandomAccessFile("logs/temp.txt","rw");
            for(int i = 0; i < data_arr.length; i++){
                randf.writeInt(data_arr[i]);
            }
            for(int i = data_arr.length-1 ; i >= 0; i--){
                //int ����ռ4���ֽ�
                randf.seek(i * 4L);
               logger.info(randf.readInt());
            }
            randf.close();
        }catch(IOException e){
           logger.info("File access error" + e);
        }
}
}
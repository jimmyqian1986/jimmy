package jimmy.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;

import org.apache.logging.log4j.Logger;

import jimmy.commons.Log4j;

public class InputOutput {
static Log4j log4j=new Log4j();
static Logger logger=log4j.getLogger(InputOutput.class.getName());
    /**
     * ���������е��������ݸ�ֵ���������
     * @param in
     * @param out
     * @throws IOException
     */
    public void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new  byte[4096];
        int len = in.read(buf);
        //read ��һ���ֽ�һ���ֽڵض����ֽ����Ľ�β��־��-1
        while (len != -1){
            out.write(buf, 0, len);
            len = in.read(buf);
        }
    }
    @SuppressWarnings("resource")
	public void getIOput() throws IOException {
        // TODO Auto-generated method stub
        //InputOutput t = new InputOutput();
        logger.info("�����ַ���");
        //t.copy(System.in, System.out);
        try {
            //inFile ��Ϊ�������������ļ�������ڣ������׳��쳣
            File inFile = new File("logs/app.log");

            //file2.txtû�У�ϵͳ���Դ������� workspace �� Test ��Ŀ�¿����ҵ�
            File outFile = new File("logs/file2.txt");
            FileInputStream fis = new FileInputStream(inFile);
            //boolean append = true;
			FileOutputStream fos = new FileOutputStream(outFile, true);
            int c;
            while((c = fis.read()) != -1){
                fos.write(c);
            }
            //�����ļ�һ��Ҫ���Źأ��ͷ�ϵͳ��Դ
            fis.close();
            fos.close();
        }catch(FileNotFoundException e) {
            logger.error("FileStreamsTest:" + e);
        }catch(IOException e){
            logger.error("FileStreamTest:" + e);
        }
        
        
        }
    
    @SuppressWarnings("deprecation")
	public void getSeqOutput() throws FileNotFoundException{
    	FileInputStream f1,f2;
        String s;
        f1 = new FileInputStream("logs/test.log");
        f2 = new FileInputStream("logs/app.log");
        SequenceInputStream fs = new SequenceInputStream(f1,f2);
        DataInputStream ds = new DataInputStream(fs);
        try {
			while((s = ds.readLine()) != null) {
				logger.info(s+"\n"+s.length());
				//logger.info(s);
   }
		} catch (IOException e) {
			logger.error("getSeqOutput ����");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void readfile(String filename) throws FileNotFoundException{
    	FileInputStream f;
    	f=new FileInputStream(filename);
    	int data=0;
    	//char out;
    	try {
			while((data=f.read())!=-1){
				logger.info("this is test for data:"+((char)data));
				//out=((char)data).append(" ");
			}
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /**
     * һ��һ�ж�ȡ�ļ����ʺ��ַ���ȡ������ȡ�����ַ�ʱ���������
     * 
     * ���Ĺر�˳���ȴ򿪵ĺ�أ���򿪵��ȹأ�
     *       �����п��ܳ���java.io.IOException: Stream closed�쳣
     * 
     * @throws IOException 
     */

    public void readFileByLine(String filename) throws IOException {
        FileReader fr=new FileReader(filename);
        BufferedReader br=new BufferedReader(fr);
        String line="";
        StringBuffer out=new StringBuffer();
        String[] arrs=null;
        while ((line=br.readLine())!=null) {
            arrs=line.split("");
            for(int i=0;i<arrs.length;i++){
            	out.append(arrs[i]);
            }
            logger.info(out);
            out.delete(0, out.length()-1);
        }
        br.close();
        fr.close();
    }
    
    /**
     * һ��һ�ж�ȡ�ļ��������ȡ�����ַ�ʱ��������
     * 
     * ���Ĺر�˳���ȴ򿪵ĺ�أ���򿪵��ȹأ�
     *       �����п��ܳ���java.io.IOException: Stream closed�쳣
     * 
     * @throws IOException 
     */

    public void readFileByLineUTF(String filename) throws IOException {
        FileInputStream fis=new FileInputStream(filename);
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        //��д����
        //BufferedReader br = new BufferedReader(new InputStreamReader(
        //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
        String line="";
        String[] arrs=null;
        while ((line=br.readLine())!=null) {
            arrs=line.split(",");
            logger.info(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);
        }
        br.close();
        isr.close();
        fis.close();
    }
    
    /**
     * һ��һ��д���ļ����ʺ��ַ�д�룬��д�������ַ�ʱ���������
     * 
     * ���Ĺر�˳���ȴ򿪵ĺ�أ���򿪵��ȹأ�
     *       �����п��ܳ���java.io.IOException: Stream closed�쳣
     * 
     * @throws IOException 
     */
    public void writeFileByLine(String filename) throws IOException {
        String[] arrs={
            "zhangsan,23,FuJian",
            "lisi,30,ShangHai",
            "wangwu,43,BeiJing",
            "laolin,21,ChongQing",
            "ximenqing,67,GuiZhou"
        };
        FileWriter fw=new FileWriter(new File(filename),true);
        //д�������ַ�ʱ���������
        BufferedWriter  bw=new BufferedWriter(fw);
        //BufferedWriter  bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8")));

        for(String arr:arrs){
            bw.write(arr+"\n");
        }
        bw.close();
        fw.close();
    }
    
    /**
     * һ��һ��д���ļ������д�������ַ�ʱ��������
     * 
     * ���Ĺر�˳���ȴ򿪵ĺ�أ���򿪵��ȹأ�
     *       �����п��ܳ���java.io.IOException: Stream closed�쳣
     * 
     * @throws IOException 
     */
    public void writeFileByLineUTF(String filename) throws IOException {
        String[] arrs={
                "zhangsan,23,����",
                "lisi,30,�Ϻ�",
                "wangwu,43,����",
                "laolin,21,����",
                "ximenqing,67,����"
        };
        //д�������ַ�ʱ���������������
        FileOutputStream fos=new FileOutputStream(new File(filename));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //��д���£�
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for(String arr:arrs){
            bw.write(arr+"\t\n");
        }
        
        //ע��رյ��Ⱥ�˳���ȴ򿪵ĺ�رգ���򿪵��ȹر�
        bw.close();
        osw.close();
        fos.close();
    }
}
    
    
  



    


       
           
        

    

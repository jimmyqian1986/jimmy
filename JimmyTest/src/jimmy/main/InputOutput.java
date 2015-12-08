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
     * 把输入流中的所有内容赋值到输出流中
     * @param in
     * @param out
     * @throws IOException
     */
    public void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new  byte[4096];
        int len = in.read(buf);
        //read 是一个字节一个字节地读，字节流的结尾标志是-1
        while (len != -1){
            out.write(buf, 0, len);
            len = in.read(buf);
        }
    }
    @SuppressWarnings("resource")
	public void getIOput() throws IOException {
        // TODO Auto-generated method stub
        //InputOutput t = new InputOutput();
        logger.info("输入字符：");
        //t.copy(System.in, System.out);
        try {
            //inFile 作为输入流的数据文件必须存在，否则抛出异常
            File inFile = new File("logs/app.log");

            //file2.txt没有，系统可以创建，在 workspace 的 Test 项目下可以找到
            File outFile = new File("logs/file2.txt");
            FileInputStream fis = new FileInputStream(inFile);
            //boolean append = true;
			FileOutputStream fos = new FileOutputStream(outFile, true);
            int c;
            while((c = fis.read()) != -1){
                fos.write(c);
            }
            //打开了文件一定要记着关，释放系统资源
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
			logger.error("getSeqOutput 出错");
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
     * 一行一行读取文件，适合字符读取，若读取中文字符时会出现乱码
     * 
     * 流的关闭顺序：先打开的后关，后打开的先关，
     *       否则有可能出现java.io.IOException: Stream closed异常
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
     * 一行一行读取文件，解决读取中文字符时出现乱码
     * 
     * 流的关闭顺序：先打开的后关，后打开的先关，
     *       否则有可能出现java.io.IOException: Stream closed异常
     * 
     * @throws IOException 
     */

    public void readFileByLineUTF(String filename) throws IOException {
        FileInputStream fis=new FileInputStream(filename);
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        //简写如下
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
     * 一行一行写入文件，适合字符写入，若写入中文字符时会出现乱码
     * 
     * 流的关闭顺序：先打开的后关，后打开的先关，
     *       否则有可能出现java.io.IOException: Stream closed异常
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
        //写入中文字符时会出现乱码
        BufferedWriter  bw=new BufferedWriter(fw);
        //BufferedWriter  bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8")));

        for(String arr:arrs){
            bw.write(arr+"\n");
        }
        bw.close();
        fw.close();
    }
    
    /**
     * 一行一行写入文件，解决写入中文字符时出现乱码
     * 
     * 流的关闭顺序：先打开的后关，后打开的先关，
     *       否则有可能出现java.io.IOException: Stream closed异常
     * 
     * @throws IOException 
     */
    public void writeFileByLineUTF(String filename) throws IOException {
        String[] arrs={
                "zhangsan,23,福建",
                "lisi,30,上海",
                "wangwu,43,北京",
                "laolin,21,重庆",
                "ximenqing,67,贵州"
        };
        //写入中文字符时解决中文乱码问题
        FileOutputStream fos=new FileOutputStream(new File(filename));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //简写如下：
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for(String arr:arrs){
            bw.write(arr+"\t\n");
        }
        
        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
        bw.close();
        osw.close();
        fos.close();
    }
}
    
    
  



    


       
           
        

    

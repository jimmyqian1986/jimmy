/**
 * �����LOG4J2����־����ʵ������
 */
package jimmy.commons; 
import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;



import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
/**
 * @author JH
 *
 */
public class Log4j implements Runnable {

	/**
	 * @param args
	 */
	//static Logger logger=LogManager.getLogger(Log4j2.class.getName());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("This An Test For LOG4J2");
test2();
	}

		/**
		 * log4j 2��ȡ�����ļ�
		 * log4j 2��ȡ�������ļ����Է�Ϊ���ࣺsrc�µ������ļ�������·���������ļ������·���������ļ�
		 */
		
		//��һ��  ����src�µ������ļ�
		public static void test0(){
			//src�µ������ļ���Ĭ�ϵı�log4j�Ŀ�ܼ��أ����ǾͲ���ʾ�ļ�����
			//ֱ�Ӳ���
			//logger.info("�Ҵ�ӡ��.......");
			//�������
			//2014-09-01 15:49:30,229 INFO  [main] test.ConfigTest (ConfigTest.java:18) - �Ҵ�ӡ��.......
		}
		
		//�ڶ���  ����·���������ļ�
		public static void test1(){
			//���ǽ�log4j2.xml����D����
			//������Ҫ�ֶ��ļ���
			//����·�������ļ�		
			ConfigurationSource source;
			try {
				//����1  ʹ��  public ConfigurationSource(InputStream stream) throws IOException ���캯��
				source = new ConfigurationSource(new FileInputStream("D:\\Android_work\\MyEclipse2015\\JimmyTest\\conf\\log4j2.xml"));
				
				//����2 ʹ�� public ConfigurationSource(InputStream stream, File file)���캯��
				File config=new File("D:\\Android_work\\MyEclipse2015\\JimmyTest\\conf\\log4j2.xml");
				source = new ConfigurationSource(new FileInputStream(config),config);
				
				//����3 ʹ�� public ConfigurationSource(InputStream stream, URL url) ���캯��
				String path="D:\\Android_work\\MyEclipse2015\\JimmyTest\\conf\\log4j2.xml";
				source = new ConfigurationSource(new FileInputStream(path),new File(path).toURL());
				
				//source.setFile(new File("D:\log4j2.xml"));		
				//source.setInputStream(new FileInputStream("D:\log4j2.xml"));		
				Configurator.initialize(null, source);				
				Logger logger = LogManager.getLogger(Log4j.class.getName()); 	
				
				logger.trace("trace...");		
				logger.debug("debug...");		
				logger.info("info...");		
				logger.warn("warn...");		
				logger.error("error...");		
				logger.fatal("fatal...");
				//һ��������Ч��
				/*2014-09-01 16:03:07,331 DEBUG [main] test.ConfigTest (ConfigTest.java:42) - debug...
				2014-09-01 16:03:07,331 INFO  [main] test.ConfigTest (ConfigTest.java:43) - info...
				2014-09-01 16:03:07,331 WARN  [main] test.ConfigTest (ConfigTest.java:44) - warn...
				2014-09-01 16:03:07,331 ERROR [main] test.ConfigTest (ConfigTest.java:45) - error...
				2014-09-01 16:03:07,331 FATAL [main] test.ConfigTest (ConfigTest.java:46) - fatal...*/
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		//������  ���·���������ļ�����
		public static void test2(){
			//������Ҫע��·���в�Ҫ�������ĺͿո�����������ģ���ʹ��urlת��
			ConfigurationSource source;
			long threadid=Thread.currentThread().getId();
			try {
				//����1  ʹ��getResource()
//				String path="conf/log4j2.xml";
//				URL url=Log4j2.class.getResource(path);
//				source = new ConfigurationSource(new FileInputStream(new File(url.getPath())),url);
//				Configurator.initialize(null, source);	
//				
				//����2 ʹ��System.getProperty
				String config=System.getProperty("user.dir");
				source = new ConfigurationSource(new FileInputStream(config+"\\conf\\log4j2.xml"));
				Configurator.initialize(null, source);
				Logger logger = LogManager.getLogger(Log4j.class.getName()); 		
				for(int i=0;i<1000;i++){
				logger.trace("This Is An LogTest trace..."+i+" Thread ID is :"+threadid);		
				logger.debug("This Is An LogTest debug..."+i+" Thread ID is :"+threadid);		
				logger.info("This Is An LogTest info..."+i+" Thread ID is :"+threadid);		
				logger.warn("This Is An LogTest warn..."+i+" Thread ID is :"+threadid);		
				logger.error("This Is An LogTest error..."+i+" Thread ID is :"+threadid);		
				logger.fatal("This Is An LogTest fatal..."+i+" Thread ID is :"+threadid);
				}
				//System.out.println(logger.error("This is "));
				//�������
				/*2014-09-01 16:32:19,746 DEBUG [main] test.ConfigTest (ConfigTest.java:53) - debug...
				2014-09-01 16:32:19,746 INFO  [main] test.ConfigTest (ConfigTest.java:54) - info...
				2014-09-01 16:32:19,746 WARN  [main] test.ConfigTest (ConfigTest.java:55) - warn...
				2014-09-01 16:32:19,746 ERROR [main] test.ConfigTest (ConfigTest.java:56) - error...
				2014-09-01 16:32:19,746 FATAL [main] test.ConfigTest (ConfigTest.java:57) - fatal...*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run(){
			test2();
		}
		public Logger getLogger(String name){
			//������Ҫע��·���в�Ҫ�������ĺͿո�����������ģ���ʹ��urlת��
			ConfigurationSource source;
			Logger logger=null;
			try {//����2 ʹ��System.getProperty
				String config=System.getProperty("user.dir");
				source = new ConfigurationSource(new FileInputStream(config+"\\conf\\log4j2.xml"));
				Configurator.initialize(null, source);
				 logger = LogManager.getLogger(name); 		
				} catch (Exception e) {
					System.out.println("����LOG4J2�������ļ�ʱ����,����confĿ¼�µ�log4j2.xml�ļ�");
				e.printStackTrace();
			}
			return logger;
			
		}
	}


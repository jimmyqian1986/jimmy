/**
 * 这个是LOG4J2的日志输入实例代码
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
		 * log4j 2读取配置文件
		 * log4j 2读取的配置文件可以分为三类：src下的配置文件、绝对路径的配置文件、相对路径的配置文件
		 */
		
		//第一类  加载src下的配置文件
		public static void test0(){
			//src下的配置文件会默认的被log4j的框架加载，我们就不显示的加载了
			//直接测试
			//logger.info("我打印了.......");
			//输出内容
			//2014-09-01 15:49:30,229 INFO  [main] test.ConfigTest (ConfigTest.java:18) - 我打印了.......
		}
		
		//第二类  绝对路径的配置文件
		public static void test1(){
			//我们将log4j2.xml放在D盘下
			//这是需要手动的加载
			//绝对路径配置文件		
			ConfigurationSource source;
			try {
				//方法1  使用  public ConfigurationSource(InputStream stream) throws IOException 构造函数
				source = new ConfigurationSource(new FileInputStream("D:\\Android_work\\MyEclipse2015\\JimmyTest\\conf\\log4j2.xml"));
				
				//方法2 使用 public ConfigurationSource(InputStream stream, File file)构造函数
				File config=new File("D:\\Android_work\\MyEclipse2015\\JimmyTest\\conf\\log4j2.xml");
				source = new ConfigurationSource(new FileInputStream(config),config);
				
				//方法3 使用 public ConfigurationSource(InputStream stream, URL url) 构造函数
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
				//一下是运行效果
				/*2014-09-01 16:03:07,331 DEBUG [main] test.ConfigTest (ConfigTest.java:42) - debug...
				2014-09-01 16:03:07,331 INFO  [main] test.ConfigTest (ConfigTest.java:43) - info...
				2014-09-01 16:03:07,331 WARN  [main] test.ConfigTest (ConfigTest.java:44) - warn...
				2014-09-01 16:03:07,331 ERROR [main] test.ConfigTest (ConfigTest.java:45) - error...
				2014-09-01 16:03:07,331 FATAL [main] test.ConfigTest (ConfigTest.java:46) - fatal...*/
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		//第三类  相对路径的配置文件加载
		public static void test2(){
			//这里需要注意路径中不要出现中文和空格，如果存在中文，请使用url转码
			ConfigurationSource source;
			long threadid=Thread.currentThread().getId();
			try {
				//方法1  使用getResource()
//				String path="conf/log4j2.xml";
//				URL url=Log4j2.class.getResource(path);
//				source = new ConfigurationSource(new FileInputStream(new File(url.getPath())),url);
//				Configurator.initialize(null, source);	
//				
				//方法2 使用System.getProperty
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
				//输出内容
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
			//这里需要注意路径中不要出现中文和空格，如果存在中文，请使用url转码
			ConfigurationSource source;
			Logger logger=null;
			try {//方法2 使用System.getProperty
				String config=System.getProperty("user.dir");
				source = new ConfigurationSource(new FileInputStream(config+"\\conf\\log4j2.xml"));
				Configurator.initialize(null, source);
				 logger = LogManager.getLogger(name); 		
				} catch (Exception e) {
					System.out.println("加载LOG4J2的配置文件时出错,请检查conf目录下的log4j2.xml文件");
				e.printStackTrace();
			}
			return logger;
			
		}
	}


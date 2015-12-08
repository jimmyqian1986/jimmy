/**
 * 
 */
package jimmy.main;

import org.apache.logging.log4j.Logger;

import jimmy.commons.Log4j;

/**
 * @author JH
 *
 */

public class Tread extends Thread {
	Log4j log4j=new Log4j();
	Logger logger=log4j.getLogger(Tread.class.getName());
	public void run(){
		for(int i=0;i<100;i++){
			logger.info("this is 中文，中文，中文 :"+i);
		}
	}
}

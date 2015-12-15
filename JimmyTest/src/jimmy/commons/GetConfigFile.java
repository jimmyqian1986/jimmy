/**
 * 
 */
package jimmy.commons;
import java.util.Date;
import jimmy.sec.Encode;
import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * @author JH
 *
 */
public class GetConfigFile {
	/**
	 * @param args
	 * @author JH
	 */
	static Log4j log4j=new Log4j();
	 //初始化加解密算法类
	static Encode encode=new Encode();
	static Logger logger=log4j.getLogger(GetConfigFile.class.getName());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		logger.trace("THIS IS AN TEST FOR GETCONFIGFILE");
		String strDate, strTime = ""; 
        Date objDate = new Date();
        logger.debug("今天的日期是：" + objDate);
        long time = objDate.getTime();
        logger.debug("自1970年1月1日起以毫秒为单位的时间（GMT）：" + time);
        strDate = objDate.toString();
        //提取 GMT 时间
        strTime = strDate.substring(11,(strDate.length() - 4));
        //按小时、分钟和秒提取时间
        strTime = "时间：" + strTime.substring(0,8);
        logger.debug(strTime);
        logger.debug(strDate);
        logger.debug("START TO CreateXML");
        String filename="./conf/conf.xml";
//        try {
//			CreateXML(filename);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.error(e.toString());
//		}
        logger.debug("START TO ReateXML");
        try{
        	ReadXML(filename);
        } catch (Exception e){
        	e.printStackTrace();
        	logger.error(e.toString());
        }
	}


    public static void CreateXML(String filename) throws Exception
    {
        // 第一种方式：创建文档，并创建根元素
        // 创建文档:使用了一个Helper类
        Document document = DocumentHelper.createDocument();

        // 创建根节点并添加进文档
        Element root = DocumentHelper.createElement(filename.substring(0, filename.length()-4));
        document.setRootElement(root);

        // 第二种方式:创建文档并设置文档的根元素节点
        Element root2 = DocumentHelper.createElement(filename.substring(0, filename.length()-4));
        Document document2 = DocumentHelper.createDocument(root2);

        // 添加属性
        root2.addAttribute("name",encode.encrypt( "zhangsan",encode.key));
        // 添加子节点:add之后就返回这个元素
        Element helloElement = root2.addElement("hello");
        Element worldElement = root2.addElement("world");
        Element contryElement =worldElement.addElement("contry");
        Element nameElement=worldElement.addElement("name");
        contryElement.setText("china");
        helloElement.setText("hello Text");
        nameElement.setText("jimmyqian");

        // 输出
        // 输出到控制台
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(document);

        // 输出到文件
        // 格式
        OutputFormat format = new OutputFormat("    ", true);// 设置缩进为4个空格，并且另起一行为true
        XMLWriter xmlWriter2 = new XMLWriter(
                new FileOutputStream(filename), format);
        xmlWriter2.write(document2);

        // 另一种输出方式，记得要调用flush()方法,否则输出的文件中显示空白
        XMLWriter xmlWriter3 = new XMLWriter(new FileWriter(filename.substring(0, filename.length()-3).concat("2.xml")),
                format);
        xmlWriter3.write(document2);
        xmlWriter3.flush();
        // close()方法也可以

    }
  public static void ReadXML(String filename) throws Exception
        {
            SAXReader saxReader = new SAXReader();

            Document document = saxReader.read(new File(filename));

            // 获取根元素
            Element root = document.getRootElement();
            logger.trace("Root: " + root.getName());

            // 获取所有子元素
            List<Element> childList = root.elements();
            logger.trace("logDB child count: " + childList.size());

            // 获取特定名称的子元素
            List<Element> childList2 = root.elements("MYSQL");
            logger.trace("MYSQL child: " + childList2.size());

            // 获取名字为指定名称的第一个子元素
            Element firstWorldElement = root.element("MYSQL");
            // 输出其属性
//           logger.trace("first World Attr: "
//                   + firstWorldElement.attribute(1).getName() + "="
//                   + firstWorldElement.attributeValue("name"));

            logger.trace("迭代输出-----------------------");
            // 迭代输出
            for (Iterator iter = root.elementIterator(); iter.hasNext();)
            {
                Element e = (Element) iter.next();
                logger.trace(e.attributeValue("MYSQL"));
                logger.trace(e.getStringValue());
            }

            logger.trace("用DOMReader-----------------------");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 注意要用完整类名
            org.w3c.dom.Document document2 = db.parse(new File(filename));

            DOMReader domReader = new DOMReader();

            // 将JAXP的Document转换为dom4j的Document
            Document document3 = domReader.read(document2);

            Element rootElement = document3.getRootElement();

            logger.trace("Root: " + rootElement.getName());
            //logger.trace("name "+rootElement.getStringValue());

        }

    

}

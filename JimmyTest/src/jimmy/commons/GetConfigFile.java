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
	 //��ʼ���ӽ����㷨��
	static Encode encode=new Encode();
	static Logger logger=log4j.getLogger(GetConfigFile.class.getName());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		logger.trace("THIS IS AN TEST FOR GETCONFIGFILE");
		String strDate, strTime = ""; 
        Date objDate = new Date();
        logger.debug("����������ǣ�" + objDate);
        long time = objDate.getTime();
        logger.debug("��1970��1��1�����Ժ���Ϊ��λ��ʱ�䣨GMT����" + time);
        strDate = objDate.toString();
        //��ȡ GMT ʱ��
        strTime = strDate.substring(11,(strDate.length() - 4));
        //��Сʱ�����Ӻ�����ȡʱ��
        strTime = "ʱ�䣺" + strTime.substring(0,8);
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
        // ��һ�ַ�ʽ�������ĵ�����������Ԫ��
        // �����ĵ�:ʹ����һ��Helper��
        Document document = DocumentHelper.createDocument();

        // �������ڵ㲢��ӽ��ĵ�
        Element root = DocumentHelper.createElement(filename.substring(0, filename.length()-4));
        document.setRootElement(root);

        // �ڶ��ַ�ʽ:�����ĵ��������ĵ��ĸ�Ԫ�ؽڵ�
        Element root2 = DocumentHelper.createElement(filename.substring(0, filename.length()-4));
        Document document2 = DocumentHelper.createDocument(root2);

        // �������
        root2.addAttribute("name",encode.encrypt( "zhangsan",encode.key));
        // ����ӽڵ�:add֮��ͷ������Ԫ��
        Element helloElement = root2.addElement("hello");
        Element worldElement = root2.addElement("world");
        Element contryElement =worldElement.addElement("contry");
        Element nameElement=worldElement.addElement("name");
        contryElement.setText("china");
        helloElement.setText("hello Text");
        nameElement.setText("jimmyqian");

        // ���
        // ���������̨
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(document);

        // ������ļ�
        // ��ʽ
        OutputFormat format = new OutputFormat("    ", true);// ��������Ϊ4���ո񣬲�������һ��Ϊtrue
        XMLWriter xmlWriter2 = new XMLWriter(
                new FileOutputStream(filename), format);
        xmlWriter2.write(document2);

        // ��һ�������ʽ���ǵ�Ҫ����flush()����,����������ļ�����ʾ�հ�
        XMLWriter xmlWriter3 = new XMLWriter(new FileWriter(filename.substring(0, filename.length()-3).concat("2.xml")),
                format);
        xmlWriter3.write(document2);
        xmlWriter3.flush();
        // close()����Ҳ����

    }
  public static void ReadXML(String filename) throws Exception
        {
            SAXReader saxReader = new SAXReader();

            Document document = saxReader.read(new File(filename));

            // ��ȡ��Ԫ��
            Element root = document.getRootElement();
            logger.trace("Root: " + root.getName());

            // ��ȡ������Ԫ��
            List<Element> childList = root.elements();
            logger.trace("logDB child count: " + childList.size());

            // ��ȡ�ض����Ƶ���Ԫ��
            List<Element> childList2 = root.elements("MYSQL");
            logger.trace("MYSQL child: " + childList2.size());

            // ��ȡ����Ϊָ�����Ƶĵ�һ����Ԫ��
            Element firstWorldElement = root.element("MYSQL");
            // ���������
//           logger.trace("first World Attr: "
//                   + firstWorldElement.attribute(1).getName() + "="
//                   + firstWorldElement.attributeValue("name"));

            logger.trace("�������-----------------------");
            // �������
            for (Iterator iter = root.elementIterator(); iter.hasNext();)
            {
                Element e = (Element) iter.next();
                logger.trace(e.attributeValue("MYSQL"));
                logger.trace(e.getStringValue());
            }

            logger.trace("��DOMReader-----------------------");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            // ע��Ҫ����������
            org.w3c.dom.Document document2 = db.parse(new File(filename));

            DOMReader domReader = new DOMReader();

            // ��JAXP��Documentת��Ϊdom4j��Document
            Document document3 = domReader.read(document2);

            Element rootElement = document3.getRootElement();

            logger.trace("Root: " + rootElement.getName());
            //logger.trace("name "+rootElement.getStringValue());

        }

    

}

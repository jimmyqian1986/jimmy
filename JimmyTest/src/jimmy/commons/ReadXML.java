package jimmy.commons;

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
 * dom4j���ѧϰ ʹ��dom4j��ܴ���xml�ĵ����������
 * 
 */
public class ReadXML
{

    public static void main(String[] args) throws Exception
    {
        // ��һ�ַ�ʽ�������ĵ�����������Ԫ��
        // �����ĵ�:ʹ����һ��Helper��
        Document document = DocumentHelper.createDocument();

        // �������ڵ㲢��ӽ��ĵ�
        Element root = DocumentHelper.createElement("student");
        document.setRootElement(root);

        // �ڶ��ַ�ʽ:�����ĵ��������ĵ��ĸ�Ԫ�ؽڵ�
        Element root2 = DocumentHelper.createElement("student");
        Document document2 = DocumentHelper.createDocument(root2);

        // �������
        root2.addAttribute("name", "zhangsan");
        // ����ӽڵ�:add֮��ͷ������Ԫ��
        Element helloElement = root2.addElement("hello");
        Element worldElement = root2.addElement("world");

        helloElement.setText("hello Text");
        worldElement.setText("world text");

        // ���
        // ���������̨
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(document);

        // ������ļ�
        // ��ʽ
        OutputFormat format = new OutputFormat("    ", true);// ��������Ϊ4���ո񣬲�������һ��Ϊtrue
        XMLWriter xmlWriter2 = new XMLWriter(
                new FileOutputStream("student.xml"), format);
        xmlWriter2.write(document2);

        // ��һ�������ʽ���ǵ�Ҫ����flush()����,����������ļ�����ʾ�հ�
        XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student2.xml"),
                format);
        xmlWriter3.write(document2);
        xmlWriter3.flush();
        // close()����Ҳ����

    }
  public void readXml() throws Exception
        {
            SAXReader saxReader = new SAXReader();

            Document document = saxReader.read(new File("students.xml"));

            // ��ȡ��Ԫ��
            Element root = document.getRootElement();
            System.out.println("Root: " + root.getName());

            // ��ȡ������Ԫ��
            List<Element> childList = root.elements();
            System.out.println("total child count: " + childList.size());

            // ��ȡ�ض����Ƶ���Ԫ��
            List<Element> childList2 = root.elements("hello");
            System.out.println("hello child: " + childList2.size());

            // ��ȡ����Ϊָ�����Ƶĵ�һ����Ԫ��
            Element firstWorldElement = root.element("world");
            // ���������
            System.out.println("first World Attr: "
                    + firstWorldElement.attribute(0).getName() + "="
                    + firstWorldElement.attributeValue("name"));

            System.out.println("�������-----------------------");
            // �������
            for (Iterator iter = root.elementIterator(); iter.hasNext();)
            {
                Element e = (Element) iter.next();
                System.out.println(e.attributeValue("name"));

            }

            System.out.println("��DOMReader-----------------------");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            // ע��Ҫ����������
            org.w3c.dom.Document document2 = db.parse(new File("students.xml "));

            DOMReader domReader = new DOMReader();

            // ��JAXP��Documentת��Ϊdom4j��Document
            Document document3 = domReader.read(document2);

            Element rootElement = document3.getRootElement();

            System.out.println("Root: " + rootElement.getName());

        }

    
}
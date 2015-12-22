package com.model;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;

public class HdfsDAO {

    //HDFS���ʵ�ַ
    private static final String HDFS = "hdfs://192.168.1.104:9000";
 
 
    
    public HdfsDAO(Configuration conf) {
        this(HDFS, conf);
    }

    public HdfsDAO(String hdfs, Configuration conf) {
        this.hdfsPath = hdfs;
        this.conf = conf;
    }

    //hdfs·��
    private String hdfsPath;
    //Hadoopϵͳ����
    private Configuration conf;

     
    
    //��������
    public static void main(String[] args) throws IOException {
        JobConf conf = config();
        HdfsDAO hdfs = new HdfsDAO(conf);
        //hdfs.mkdirs("/Tom");
        //hdfs.copyFile("C:\\files", "/wgc/");
         hdfs.ls("hdfs://192.168.1.104:9000/wgc/files");
        //hdfs.rmr("/wgc/files");
        //hdfs.download("/wgc/��3��windows��hadoop+eclipse�����.docx", "c:\\");
        //System.out.println("success!");
    }        
    
    //����Hadoop�����ļ�
    public  static JobConf config(){
        JobConf conf = new JobConf(HdfsDAO.class);
        conf.setJobName("HdfsDAO");
        conf.addResource("classpath:/hadoop/core-site.xml");
        conf.addResource("classpath:/hadoop/hdfs-site.xml");
        conf.addResource("classpath:/hadoop/mapred-site.xml");
        return conf;
    }

    //�ڸ�Ŀ¼�´����ļ���
    public void mkdirs(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        if (!fs.exists(path)) {
            fs.mkdirs(path);
            System.out.println("Create: " + folder);
        }
        fs.close();
    }
    
    //ĳ���ļ��е��ļ��б�
    public FileStatus[] ls(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        FileStatus[] list = fs.listStatus(path);
        System.out.println("ls: " + folder);
        System.out.println("==========================================================");
        if(list != null)
        for (FileStatus f : list) {
            //System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDir(), f.getLen());
                System.out.printf("%s, folder: %s, ��С: %dK\n", f.getPath().getName(), (f.isDir()?"Ŀ¼":"�ļ�"), f.getLen()/1024);
        }
        System.out.println("==========================================================");
        fs.close();
        
        return  list;
    }
    
    
    public void copyFile(String local, String remote) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        //remote---/�û�/�û��µ��ļ����ļ���
        fs.copyFromLocalFile(new Path(local), new Path(remote));
         System.out.println("copy from: " + local + " to " + remote);
        fs.close();
    }
    
    //ɾ���ļ����ļ���
    public void rmr(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.deleteOnExit(path);
        System.out.println("Delete: " + folder);
        fs.close();
    }
    
    
    //�����ļ�������ϵͳ
    public void download(String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.copyToLocalFile(path, new Path(local));
        System.out.println("download: from" + remote + " to " + local);
        fs.close();
    }
    
    
}
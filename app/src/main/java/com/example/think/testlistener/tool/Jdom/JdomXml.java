package com.example.think.testlistener.tool.Jdom;

import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by think on 2015/8/2.
 */
public class JdomXml {
    private String workInfo;
    public static final String DOWN_LOADPATH = Environment.getExternalStorageDirectory().
            getAbsolutePath()+"/listener/";

    public JdomXml(String workInfo) throws IOException {

        this.workInfo = workInfo;

    }

    public void buildXml(String netInfo, String batteryInfo) throws FileNotFoundException {
        //生成子节点
        Element root, network, phone, battery;


        root = new Element("手机信息");
        network = new Element("网络状况");
        phone = new Element("手机");
        battery = new Element("电池容量");


        //设置节点信息
        network.setText(netInfo);
        battery.setText(batteryInfo);


        //添加二级节点
        phone.addContent(network);
        phone.addContent(battery);

        //根节点添加子节点
        root.addContent(phone);

        //生成Document类
        Document doc = new Document(root);


        //设置格式
        Format format = Format.getCompactFormat();
        format.setEncoding("UTF-8");
        format.setIndent("    ");


        //开始准备生成
        XMLOutputter outputter = new XMLOutputter(format);

        //设置文件储存路径
        File dir = new File(DOWN_LOADPATH);

        //如果这个路径的文件不存在，那就重新创建一个这个路径
        if(!dir.exists()){
            dir.mkdir();
        }

        //生成文件
        File file = new File(dir, "phoneinfo.xml");

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        try {

            outputter.output(doc, fileOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


    }


}

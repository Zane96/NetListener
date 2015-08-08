package com.example.think.testlistener.tool.Jdom;

import android.os.Environment;
import android.util.Log;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * Created by think on 2015/8/5.
 */
public class JdomReadXml {

    private static FileInputStream fileInputStream = null;
    private static File file = null;
    private static Document document;



     public static String readXml() throws JDOMException, IOException {

        String netInfo = "";

         file = new File(Environment.getExternalStorageDirectory()
                 .getAbsolutePath()+"/listener/phoneinfo.xml");
         Log.i("Zane", "1");
         fileInputStream = new FileInputStream(file);
         Log.i("Zane", "2");

         SAXBuilder builder = new SAXBuilder();
         Log.i("Zane", "3");

         try {
             document = builder.build(fileInputStream);
             Log.i("Zane", "4");
         }
         catch (Exception e){
             e.printStackTrace();
         }

        Element phoneInfo = document.getRootElement();
         //Log.i("Zane", String.valueOf(document));
        List<Element> list = phoneInfo.getChildren("手机");
         //Log.i("Zane", String.valueOf(phoneInfo.getChildren("手机")));
         //Log.i("Zane", String.valueOf(list.size()));

        for (int i = 0; i < list.size(); i++){
            Element e = list.get(i);
            //Log.i("Zane", String.valueOf(e));
            netInfo = e.getChildText("网络状况");
            Log.i("Zane", netInfo);
        }

        return netInfo;
    }

}

package com.example.think.testlistener.tool.Jdom.html;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;





/**
 * Created by think on 2015/8/9.
 */
public class RenderSnakeHtml {

    public static final String DOWN_LOADPATH = Environment.getExternalStorageDirectory().
            getAbsolutePath()+"/listener2/";
    private String networkInfo;
    private String batteryInfo;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public RenderSnakeHtml(String networkInfo, String batteryInfo){
        this.networkInfo = networkInfo;
        this.batteryInfo = batteryInfo;
    }

    public void buildHtml(String networkInfo, String batteryInfo) throws IOException {
//        HtmlCanvas html = new HtmlCanvas();
//        html.html()
//                .head()
//                    .title()
//                        .content("PhoneInfo")
//                    ._title()
//                ._head()
//                .body()
//                    .content("This is some text!")
//                ._body()
//        ._html()
//        ;
//
//        final String rendered = html.toHtml();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<html>")
                .append("<head>")
                .append("<meta charset = ")
                //html输出的时候必须也要加一个utf-8
                .append("\"UTF-8\">")
                .append("<title>")
                .append("phoneinfo")
                .append("</title>")
                .append("</head>")
                .append("<body>")
                //.append("<font size = 6>")
                .append("<h1 align = \"center\">")
                .append(networkInfo)
                //.append("</font>")
                .append("</h1>")
                .append("<br>")
                .append("<h1 align = \"center\">")
                .append(batteryInfo)
                .append("</h1>")
                .append("</body>")
                .append("</html>");

        String rendered = String.valueOf(stringBuilder);

        Log.i("html", rendered);

        final File dir = new File(DOWN_LOADPATH);
        if(!dir.exists()){
            dir.mkdir();
        }

        final File file = new File(dir, "phoneinfo.html");

        fileWriter = new FileWriter(file);
        bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(rendered);

        bufferedWriter.close();
    }
}

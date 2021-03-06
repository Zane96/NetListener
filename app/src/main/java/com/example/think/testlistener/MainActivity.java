package com.example.think.testlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.think.testlistener.model.PhoneInfo;


import com.example.think.testlistener.tool.Jdom.html.RenderSnakeHtml;
import com.example.think.testlistener.tool.Jdom.webservice.WebService;



import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 1.在主活动里面注册系统级别的广播接收器来监听网络是否可用
 2.在接收器里面改变标志位的值
 3.分别new不同的jdom类，传Element的值进去
 4.在jdom类里面生成xml文件
 5.添加butterkinef和jdom架包
 6.加Toast测试
 7.导入nanohttpd java包
 8.写HttpService类，在里面用jdom解析xml文件，然后用InputStream来读取，
 最后Response传入  ok, text/xml, inputOutstream, len（totalbyes）。
 9.Activity里面启动HttpService
 */
public class MainActivity extends ActionBarActivity {

    private MyBroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();
    @Bind(R.id.startlistener) Button button;
    //private JdomXml jdomXml = null;
    private WebService webService = null;
    private PhoneInfo phoneInfo = new PhoneInfo();

    //test html
    private RenderSnakeHtml renderSnakeHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        webService = new WebService();
        try {
            webService.start();
        } catch (IOException e) {
            Log.i("Zane", String.valueOf(e));
        }

    }

    @OnClick(R.id.startlistener) void registerBroadcast(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(getIntent().ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        webService.stop();
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        private String batteryInfo = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             * 这是网络服务信息的接受代码
             */
            //系统网络服务类
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.
                    CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            /**
             * 这是电池信息的接受代码
             */
            if (intent.getAction().equals(intent.ACTION_BATTERY_CHANGED)) {
                //电池剩余容量
                int level = intent.getIntExtra("level", 0);
                Log.i("Zane", String.valueOf(level));
                //电池总容量大小
                int scale = intent.getIntExtra("scale", 100);
                Log.i("Zane", String.valueOf(scale));
                //电池容量比
                double percentage = (double)(level / scale) * 100;
                //Log.i("Zane", String.valueOf(percentage));
                //电池信息
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("电池剩余: ").append(level).append("%");
                batteryInfo = String.valueOf(stringBuilder);
            }

            if (networkInfo != null && networkInfo.isAvailable() && batteryInfo != null){
                String netInfo = "网络可用";
                responsesToInfo(netInfo, batteryInfo);
                responsesToHtml(netInfo, batteryInfo);
            }
            else {
                String netInfo = "网络不可用";
                responsesToInfo(netInfo, batteryInfo);
                responsesToHtml(netInfo, batteryInfo);
            }
        }

        //test html
        public void responsesToHtml(String netInfo, String batteryInfo){

            renderSnakeHtml = new RenderSnakeHtml(netInfo, batteryInfo);

            try {
                renderSnakeHtml.buildHtml(netInfo, batteryInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





        public void responsesToInfo(String netInfo, String batteryInfo){
            Toast.makeText(MainActivity.this, netInfo, Toast.LENGTH_SHORT).show();
            phoneInfo.setNetInfo(netInfo);
            phoneInfo.setBatteryInfo(batteryInfo);
//            try {
//
//                jdomXml = new JdomXml(phoneInfo.getNetInfo());
//                jdomXml.buildXml(phoneInfo.getNetInfo(), phoneInfo.getBatteryInfo());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}

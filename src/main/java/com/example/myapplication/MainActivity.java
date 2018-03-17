package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.util.Logger;
import com.example.myapplication.util.ThreadUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.head).setOnClickListener(this);
        findViewById(R.id.post).setOnClickListener(this);
    }


    private void getRequest(){
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                executeGet();
            }
        });
    }

    /**
     * 此方法在子线程中执行  主线程不能执行耗时操作
     */
    private void executeGet(){
        HttpURLConnection httpURLConnection = null;
        InputStream in = null;
        try {
            //1,建立连接
            URL url = new URL("http://www.baidu.com");
//            InputStream is = url.openStream();
             httpURLConnection = (HttpURLConnection)url.openConnection();//openStream
//            if(httpURLConnection instanceof  HttpsURLConnection){
//                ((HttpsURLConnection)httpURLConnection).setSSLSocketFactory();
//                ((HttpsURLConnection)httpURLConnection).setHostnameVerifier();
//            }

            //设置请求方法GET
            httpURLConnection.setRequestMethod("GET");//默认GET
            //2,发送数据
//            httpURLConnection.getOutputStream();//GET请求不能拿到输出流

            //3,连接 拿到响应
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == 200){//请求成功
                in = httpURLConnection.getInputStream();
                readData(in);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
            if(in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void readData(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1){//-1 已读取到末尾
//            Logger.i("读取长度："+len);
//            Logger.i("读取内容："+new String(buffer));
            //在网络传输中我们往往要传输很多变量，我们可以利用ByteArrayOutputStream把所有的变量收集到一起，然后一次性把数据发送出去
            outputStream.write(buffer);
        }
        outputStream.close();
        String rst = new String(outputStream.toByteArray());
        Logger.i(rst);
    }

    private void headRequest(){

    }

    private void postRequest(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get:{
                getRequest();
                break;
            }

            case R.id.head:{
                headRequest();
                break;
            }

            case R.id.post:{
                postRequest();
                break;
            }
            default:
                break;
        }
    }
}

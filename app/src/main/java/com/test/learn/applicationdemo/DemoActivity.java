package com.test.learn.applicationdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.Date;
import java.util.List;

/**
 * Created by pain on 16/7/9.
 */
@EActivity(R.layout.activity_main)
public class DemoActivity extends Activity {
    //单个View的注入
    @ViewById(R.id.button1)
    protected Button button;
    //多个View的注入
    @ViewsById({R.id.textView1,R.id.textView2})
    List<TextView> textViewlist;
    //这个用来实例化之前声明为EBean的东西
    @Bean
    void setBean(DemoBean demoBean){
        demoBean.setName("pain");
        Log.w("Bean", "setBean: "+demoBean.getName()+"+++++++++++++++++++++++++++++++++");
    }
    //单个点击
    @Click(R.id.button1)
    void buttonClick(){
        runInBackground();
    }
    //耗时操作
    @Background
     void runInBackground() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runUiThread();
    }
    //UI操作
    @UiThread
    void runUiThread(){
        textViewlist.get(0).setText("后台运行结束");
        Toast.makeText(DemoActivity.this, "over", Toast.LENGTH_SHORT).show();
    }

    //多个点击
    @Click({R.id.textView1,R.id.textView2})
    void textClick(View view){
        switch (view.getId()){
            case R.id.textView1:
                Toast.makeText(DemoActivity.this, "文本1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView2:
                Toast.makeText(DemoActivity.this, "文本2", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    //Views初始化之后要做的事情，相当于onCreate后一部分的东西
    @AfterViews
    protected void init(){
        for (TextView text:textViewlist){
            text.setText(new Date()+"");
        }
    }

}

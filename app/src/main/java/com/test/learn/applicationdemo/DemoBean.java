package com.test.learn.applicationdemo;

import org.androidannotations.annotations.EBean;

/**
 * Created by pain on 16/7/9.
 */
@EBean
/*
*bean 的声明为在注解上@EBean
*这个AA框架要求只能存在bean一个构造方法
**/
public class DemoBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   private String name;
}

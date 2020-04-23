package com.ysh.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class HelloCrontroller  {
 private static final Logger logger= LoggerFactory.getLogger(HelloCrontroller.class);

    public static final int code=1;
    public static final String name="jack";
    @Override
    @ResponseBody
    @RequestMapping("/hello")
    public String toString() {
        final String s = "hello world!";
        System.out.println(s);
        System.out.println("s = " + s);
        return s;

    }

    public static void main(String[] args) {
        String[]array=new String[]{"tony","jack","lion"};
        if (array == null) {

        }
        for (int i = 0; i < array.length; i++) {

        }
        for (int i = 0; i <array.length ; i++) {

        }
        System.out.println("args = " + args);
        System.out.println("HelloCrontroller.main");
        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println();


    }
    public String getydmessage(){
        String d="";
        if (d == null) {

        }
        return d;
    }
}

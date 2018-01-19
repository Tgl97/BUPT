package com.org.run;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Genglintong on 2017/4/23.
 * 控制线程运行
 */
public class ServerInvoke implements Runnable{
    private Server obj;
    public ServerInvoke(Server obj){
        this.obj = obj;
    }
    public int i;

    public void run(){
        while(true){
            try{
                Thread.sleep(60000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            try {
                obj.invoke();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

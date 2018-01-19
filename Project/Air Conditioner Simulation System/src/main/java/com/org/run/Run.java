package com.org.run;

import com.org.help.DBOperator;
import com.org.model.operationLog.HostOperationLog;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Genglintong on 2017/4/23.
 * 服务机运行类
 * 用以设置缺省参数、判断客户机的各项参数的合理性
 */
 
public class Run {
    private static Server server;

    private static ServerInvoke invoke;

    private static Thread socket;

    private static int _up_temp = 30;
    private static int _low_temp = 10;
    public static boolean _is_cool = true;
    private static int _default_temp = 26;
    private static int _default_wind = 1;
	//获取当前所有客户机的实例列表
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        HashMap<Integer, User> user_list = Server.getUsers();
        Iterator<Integer> iter = user_list.keySet().iterator();

        while (iter.hasNext())
            users.add(user_list.get(iter.next()));
            
        return users;
    }

    public static HashMap<Integer, User> getUser_list() {
        return server.getUser_list();
    }
	//设置缺省参数并开机
    public static void setAttri(int up_temp,int low_temp,boolean is_cool,int default_temp,int default_wind) {
        _up_temp = up_temp;
        _low_temp = low_temp;
        _is_cool = is_cool;
        _default_temp = default_temp;
        _default_wind = default_wind;
        server.start(up_temp, low_temp, is_cool, default_temp, default_wind);
    }
	//半段各参数设置的合理性
    public static String cmpAttri(int up_temp,int low_temp,boolean is_cool,int default_temp,int default_wind) {
        StringBuilder ret = new StringBuilder();

        if (up_temp < 50 && low_temp > 0) {
            if (up_temp != _up_temp) {
                ret.append(" 设置温度上限: " + up_temp);
            }

            if (low_temp != _low_temp) {
                ret.append(" 设置温度下限: " + low_temp);
            }
        }
        else if (up_temp >= 50){
            ret.append("超过上限温度范围");
        }
        else if (low_temp <= 0) {
            ret.append("超过下限温度范围");
        }

        if (is_cool != _is_cool) {
            if (is_cool == true)
                ret.append(" 设置为制冷");
            else
                ret.append(" 设置为制热");
        }

        if (default_temp != _default_temp && (default_temp <= _up_temp || default_temp >= _low_temp)) {
            ret.append(" 设置目标温度: " + default_temp);
        }
        else if (default_temp > _up_temp || default_temp < _low_temp) {
            ret.append("超过温度范围");
        }

        if (default_wind != _default_wind) {
            ret.append(" 设置初始风速: ");
            if (default_wind == 1)
                ret.append("低风速");
            else if (default_wind == 2)
                ret.append("中风速");
            else if (default_wind == 3)
                ret.append("高风速");
            else
                ret.append("风速参数错误");
        }

        if (up_temp == _up_temp && low_temp == _low_temp && is_cool == _is_cool && default_temp == _default_temp && default_wind == _default_wind) {
            ret.append("无变化操作");
        }

        return ret.toString();
    }
	//运行方法
    public  static void run(){
        server  = new Server();
        server.start(_up_temp,_low_temp,_is_cool,_default_temp,_default_wind);

        socket = new Socket(server);
        socket.start();

        invoke  = new ServerInvoke(server);
        Thread thread_invoke = new Thread(invoke);

        thread_invoke.start();

    }
}

package com.org.run;

import com.org.help.DBOperator;
import com.org.model.operationLog.SlaveOperationLog;
import com.org.model.statistic.OnOffStatistic;

import java.io.*;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Genglintong, Tiankuo on 2017/4/23.
 * socket类实现
 * 设置相关通信信息
 */
public class Socket extends Thread implements Runnable {
    private ServerSocket server;
    private Server airServer;
    private int port = 5000;
    private java.net.Socket socket = new java.net.Socket();
    DBOperator op = new DBOperator();

    private OutputStream outToClient;
    private InputStream inFromClient;

    public Socket(Server s){
        try {
            airServer = s;
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void invoke(final java.net.Socket s)throws IOException{
        new Thread(new Runnable() {
            private int id = -1;
            @Override
            public void run() {
                try {
                    outToClient = socket.getOutputStream();
                    inFromClient = socket.getInputStream();
                    DataInputStream in = new DataInputStream(inFromClient);
                    DataOutputStream out = new DataOutputStream(outToClient);
                    Scanner scan = new Scanner(System.in);
                    FileOutputStream out1 = new FileOutputStream("E:/output1.txt",true);
                    byte[] buffer = new byte[2];
                    in.read(buffer);
                    String s = new String (buffer,"utf-8");
                    while(!s.equals("bye")) {
						//服务机收到客户机开机信号
                        if (s.charAt(0) == 'A') {
                            User user = new User();
                            user.setFree(0);
                            user.setServ_time(0);
                            user.setOn_time(new Date());
                            user.setLow_wind_time(0);
                            user.setMedium_wind_time(0);
                            user.setHigh_wind_time(0);
                            user.setIs_open(false);
                            user.setOut(out);
                            user.setUser_id(s.charAt(1) - '0');
							//向数据库写入一次开关机信息
                            OnOffStatistic onoff = new OnOffStatistic();
                            user.setOnOff(onoff);
                            onoff = user.getOnOff();
                            insert(onoff);
                            user.setKeyId(onoff.getKeyId());
                            SlaveOperationLog slvoplog = new SlaveOperationLog(user.getOn_time(), "开机", user.getUser_id());
                            insert(slvoplog);
                            id = s.charAt(1) - '0';
                            airServer.addUser(id,user);
                            Server.getUsers().put(id, user);
                            String send = "";

                            //发送制冷制热信号
                            if (airServer.isIs_cool()) send = "G1";
                            else send = "G0";
                            out.writeUTF(send);
                            out.flush();

                            //发送温度上下限
                            send = 'H' + String.valueOf((char) airServer.getUp_temp());
                            out.writeUTF(send);
                            out.flush();
                            send = 'I' + String.valueOf((char) airServer.getLow_temp());
                            out.writeUTF(send);
                            out.flush();

                            //发送缺省信息
                            send = 'J' + String.valueOf((char) airServer.getDefault_temp());
                            user.setFinal_temp(airServer.getDefault_temp());
                            out.writeUTF(send);
                            out.flush();
                            send = 'K' + String.valueOf((char) (airServer.getDefault_wind()+'0'));
                            out.writeUTF(send);
                            user.setWind(airServer.getDefault_wind());
                            out.flush();

                        }
						//设置客户机当前温度			
						else if (s.charAt(0) == 'B') {
                            airServer.getUser_list().get(id).setIs_open(true);
                            airServer.getUser_list().get(id).setStart_temp(s.charAt(1));
                            airServer.getUser_list().get(id).setNow_temp(s.charAt(1));
                        } 
						//收到客户机目标温度的改变信号，向数据库中插入一次从机操作信息
						else if (s.charAt(0) == 'C') {
                            airServer.getUser_list().get(id).setFinal_temp(s.charAt(1));
                            User user = airServer.getUser_list().get(id);
                            SlaveOperationLog slvoplog = new SlaveOperationLog(new Date(), "目标温度改变为" + user.getFinal_temp() + "℃", user.getUser_id());
                            insert(slvoplog);
                        }
						//收到客户机风速改变信号，向数据库中插入一次从机操作日志
						else if (s.charAt(0) == 'D') {
                            airServer.getUser_list().get(id).setWind(s.charAt(1) - '0');
                            User user = airServer.getUser_list().get(id);
                            String wind = "";
                            if(user.getWind() == 1 ){
                                wind = "低速风";
                            }else if(user.getWind() == 2){
                                wind = "中速风";
                            }
                            else if(user.getWind() == 3){
                                wind = "高速风";
                            }
                            SlaveOperationLog slvoplog = new SlaveOperationLog(new Date(), "风速改变为" + wind, user.getUser_id());
                            insert(slvoplog);
                        } 
						//收到温度变化信号
						else if (s.charAt(0) == 'E') {
                            airServer.getUser_list().get(id).setNow_temp(s.charAt(1));
                        } 
						//收到客户机关机信号，向数据库插入一次开关机信息
						else if (s.charAt(0) == 'L') {
                            airServer.getUser_list().get(id).setIs_open(false);
                            airServer.getUser_list().get(id).setOff_time(new Date());
                            User user = airServer.getUser_list().get(id);
                            OnOffStatistic onoff = new OnOffStatistic();
                            user.setOnOff(onoff);
                            onoff = user.getOnOff();
                            update(onoff);
                            SlaveOperationLog slvoplog = new SlaveOperationLog(new Date(), "关机", user.getUser_id());
                            insert(slvoplog);
                        }
                        in.read(buffer);
                        s = new String(buffer, "utf-8");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void run(){
        try{
            System.out.println("Socket   "+Thread.currentThread().getName());
            while(true)
            {
                System.out.print("\n");
                socket = server.accept();
                invoke(socket);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void update(OnOffStatistic e) {
        try {
            DBOperator.setUp();
            DBOperator.updateEntity(e);
            DBOperator.tearDown();
        }
        catch (Exception exc) {
            DBOperator.tearDown();
        }
    }

    private <T> void insert(T e) {
        try {
            DBOperator.setUp();
            DBOperator.insertEntity(e);
            DBOperator.tearDown();
        }
        catch (Exception exc) {
            DBOperator.tearDown();
        }
    }
}

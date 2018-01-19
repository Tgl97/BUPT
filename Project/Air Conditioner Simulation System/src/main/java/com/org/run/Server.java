package com.org.run;

import com.org.help.DBOperator;
import com.org.model.stateLog.HostStateLog;
import com.org.model.stateLog.SlaveStateLog;
import com.org.model.statistic.OnOffStatistic;
import com.org.model.statistic.RequestStopStatistic;
import com.org.repository.RequestStopStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Genglintong, Tiankuo on 2017/4/23.
 * 服务机实例
 * 完成服务机的各种功能，包括调度各从机的运行状态，数据库交互等
 */
@Controller
public class Server {

    private int up_temp;//温度上限
    private int low_temp;//温度下限
    private boolean is_cool;//是否制冷
    private int default_wind;//默认风速
    private int default_temp;//默认目标温度

    public int getDefault_wind() {
        return default_wind;
    }

    public int getDefault_temp() {
        return default_temp;
    }

    public boolean isIs_cool() {
        return is_cool;
    }

    public int getLow_temp() {
        return low_temp;
    }

    public int getUp_temp() {
        return up_temp;
    }

    private static HashMap<Integer,User> users = new HashMap<Integer,User>(); // bool缓存数组

    private HashMap<Integer,User> user_list = new HashMap();//连接从机链表

    public HashMap<Integer,User> getUser_list() {
        return user_list;
    }
    
    public static HashMap<Integer,User> getUsers() {return users;}

    //设置默认信息
    public void start(int up_temp,int low_temp,boolean is_cool,int default_temp,int default_wind){
        this.default_temp = default_temp;
        this.default_wind = default_wind;
        this.is_cool = is_cool;
        this.low_temp = low_temp;
        this.up_temp = up_temp;
    }

    //列表中添加一个客户机实例
    public void addUser(int id,User user){
        user_list.put(id,user);
    }
	//调度策略模块
    public void choice(){
        Iterator iter = user_list.entrySet().iterator();
        User user,user_Hang = null;

        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            user = user_list.get(entry.getKey());
            if(user.isIs_dd()){
                if(user_Hang == null) user_Hang = user;
                else{
                    if((user_Hang.getWind() > user.getWind()) || ((user_Hang.getWind() == user.getWind()) && user_Hang.getDd_num()<user.getDd_num())) {
                        user_Hang = user;
                    }
                }
            }else {
                try {
                    user.getOut().writeUTF("F1");
                    user.getOut().flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Date cur_date = new Date();
                user.setStart_time(cur_date);
                user.setFormer_wind(user.getWind());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(cur_date);
                RequestStopStatistic intr = new RequestStopStatistic();
                user.setIntr(intr);
                intr = user.getIntr();
                user.setDd_num(0);
                user.setIs_dd(true);
            }
        }
        try {
            user_Hang.getOut().writeUTF("F0");
            user_Hang.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date cur_date = new Date();
        user_Hang.setEnd_time(cur_date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = sdf.format(cur_date);
        String str2 = sdf.format(user_Hang.getStart_time());
        RequestStopStatistic intr = new RequestStopStatistic();
        user_Hang.setIntr(intr);
        intr = user_Hang.getIntr();
        insert(intr);
        user_Hang.setIs_dd(false);
    }

    //获得一个时间片的金额
    public double getFree(int wind){
        double PreFree = 0;
        if(wind == 1 ){
            PreFree = 0.0125;
        }else if(wind == 2){
            PreFree = 0.0167;
        }
        else if(wind == 3){
            PreFree = 0.0208;
        }
        return PreFree * 60;
    }

    public boolean Judge(User a,User b){
        return false;
    }

    public synchronized void invoke() throws IOException {
		FileOutputStream out = new FileOutputStream("E:/output.txt",true);
        int num = user_list.size();
        User user;
        //客户机连接数小于3，直接调度
		if(num <= 3) {
           Iterator iter = user_list.entrySet().iterator();
           while (iter.hasNext()) {
               Map.Entry entry = (Map.Entry) iter.next();
               user = user_list.get(entry.getKey());
               if (user.isIs_open()) {
                   if (user.isIs_dd()) {
					   //列表中此客户机处于开机状态且正在被调度，到达目标温度
                       if ((is_cool) ? (user.getNow_temp() <= user.getFinal_temp()) : (user.getNow_temp() >= user.getFinal_temp())) {
                           try {
							   //发送挂起请求
                               user.getOut().writeUTF("F0");
                               user.getOut().flush();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
						   //向数据库中写入一次调度信息
                           Date cur_date = new Date();
                           user.setEnd_time(cur_date);
                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                           String str1 = sdf.format(cur_date);
                           String str2 = sdf.format(user.getStart_time());
                           RequestStopStatistic intr = new RequestStopStatistic();
                           user.setIntr(intr);
                           intr = user.getIntr();
                           insert(intr);
                           user.setIs_dd(false);
                       }
					   //未到达目标温度
                       else{
						   //客户机风速变化
                           if ( user.getWind() != user.getFormer_wind()){
							   //向数据库写入一次调度信息
                               Date cur_date = new Date();
                               user.setEnd_time(cur_date);
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               String str1 = sdf.format(cur_date);
                               String str2 = sdf.format(user.getStart_time());
                               RequestStopStatistic intr = new RequestStopStatistic();
                               user.setIntr(intr);
                               intr = user.getIntr();
                               insert(intr);
                               user.setStart_time(cur_date);
                               user.setFormer_wind(user.getWind());
                           }
                       }
                   }
				   //客户机处于挂起状态
                   else if (!user.isIs_dd()) {
					   //未到达目标温度
                        if ((is_cool) ? (user.getNow_temp() > user.getFinal_temp()) : (user.getNow_temp() < user.getFinal_temp())) {
							//发送调度请求
                            try {
                                user.getOut().writeUTF("F1");
                                user.getOut().flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
							//向数据库中写入一次调度信息
                            Date cur_date = new Date();
                            user.setStart_time(cur_date);
                            user.setFormer_wind(user.getWind());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String str = sdf.format(cur_date);
                            RequestStopStatistic intr = new RequestStopStatistic();
                            user.setIntr(intr);
                            intr = user.getIntr();
                            user.setDd_num(0);
                            user.setIs_dd(true);
                        }
                    }

               }
               else {
                   user.setIs_dd(false);
               }
           }
       }
       //客户机连接数等于4
        else if (num == 4){
           Iterator iter = user_list.entrySet().iterator();
           while(iter.hasNext()) {
               Map.Entry entry = (Map.Entry) iter.next();
               user = user_list.get(entry.getKey());
               if (user.isIs_open()) {
                   if (user.isIs_dd()) {
					   //列表中此客户机处于开机状态且正在被调度，到达目标温度
                       if ((is_cool) ? (user.getNow_temp() <= user.getFinal_temp()) : (user.getNow_temp() >= user.getFinal_temp())) {
                           try {
                               user.getOut().writeUTF("F0");
                               user.getOut().flush();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
						   //向数据库中写入一次调度信息
                           Date cur_date = new Date();
                           user.setEnd_time(cur_date);
                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                           String str1 = sdf.format(cur_date);
                           String str2 = sdf.format(user.getStart_time());
                           RequestStopStatistic intr = new RequestStopStatistic();
                           user.setIntr(intr);
                           intr = user.getIntr();
                           insert(intr);
                           user.setIs_dd(false);
                       }
                       else{
                           if ( user.getWind() != user.getFormer_wind()){
                               Date cur_date = new Date();
                               user.setEnd_time(cur_date);
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               String str1 = sdf.format(cur_date);
                               String str2 = sdf.format(user.getStart_time());
                               RequestStopStatistic intr = new RequestStopStatistic();
                               user.setIntr(intr);
                               intr = user.getIntr();
                               insert(intr);
                               out.write((String.valueOf(user.getUser_id()) + "\t" + String.valueOf(user.getStart_temp()) + "\t"
                                       + String.valueOf(user.getFinal_temp()) + "\t" + String.valueOf(user.getFormer_wind()) + "\t"
                                       + str2 + "\t" + str1 + "\r\n").getBytes());
                               user.setStart_time(cur_date);
                               user.setFormer_wind(user.getWind());
                           }
                       }
                   }
               }
               else {
                   user.setIs_dd(false);
               }
           }
           //可以被调度的从机
           if(num_can_dd() > 3 ){
               if(num_dd() == 0) {
                   int i = 1;
                   iter = user_list.entrySet().iterator();
                   while(iter.hasNext()) {
                       Map.Entry entry = (Map.Entry) iter.next();
                       user = user_list.get(entry.getKey());
                       if(user.getWind()<user_list.get(i).getWind()){
                           i = user.getUser_id();
                       }
                   }
                   iter = user_list.entrySet().iterator();
                   while(iter.hasNext()) {
                       Map.Entry entry = (Map.Entry) iter.next();
                       user = user_list.get(entry.getKey());
                       if(user.getUser_id() != i){
                           try {
                               user.getOut().writeUTF("F1");
                               user.getOut().flush();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                           Date cur_date = new Date();
                           user.setStart_time(cur_date);
                           user.setFormer_wind(user.getWind());
                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                           String str = sdf.format(cur_date);
                           user.setDd_num(0);
                           user.setIs_dd(true);
                       }
                   }
               }
               else{
                   //从已经调度的状态中选择一个挂起，并且把所有挂起态全部调度
                   choice();
               }
           }
           else{
               Iterator iter1 = user_list.entrySet().iterator();
               while(iter1.hasNext()) {
                   Map.Entry entry = (Map.Entry) iter1.next();
                   user = user_list.get(entry.getKey());
                   if (user.isIs_open()) {
                       if(!user.isIs_dd()){
                           if((is_cool)?(user.getNow_temp()  > user.getFinal_temp()):(user.getNow_temp()  < user.getFinal_temp())){
                               try {
                                   user.getOut().writeUTF("F1");
                                   user.getOut().flush();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                               Date cur_date = new Date();
                               user.setStart_time(cur_date);
                               user.setFormer_wind(user.getWind());
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               String str = sdf.format(cur_date);
                               user.setDd_num(0);
                               user.setIs_dd(true);
                           }
                       }
                   }
               }
           }
       }
        //遍历计费
        Iterator iter = user_list.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int key = (int) entry.getKey();
            user = user_list.get(entry.getKey());
            if(user.isIs_dd() && user.isIs_open()){
                user.setFree(user.getFree() + getFree(user.getWind()));
                user.setDd_num(user.getDd_num()+1);
                user.setServ_time(user.getServ_time()+1);
                if (user.getWind() == 1) {
                    user.setLow_wind_time(user.getLow_wind_time() + 1);
                }
                if (user.getWind() == 2) {
                    user.setMedium_wind_time(user.getMedium_wind_time() + 1);
                }
                if (user.getWind() == 3) {
                    user.setHigh_wind_time(user.getHigh_wind_time() + 1);
                }
                int mode;
                if ( is_cool == true ) mode = 0;
                else mode = 1;

                SlaveStateLog slvstlog = new SlaveStateLog(user.getOn_time(), new Date(), (byte)1, user.getUser_id(), user.getNow_temp(), user.getFinal_temp(), user.getWind(), user.getWind(), mode, 0, (int)user.getFree(), (byte)1);
                insert(slvstlog);
            }
            else if ( !user.isIs_dd() && user.isIs_open() ) {
                int mode;
                if ( is_cool == true ) mode = 0;
                else mode = 1;

                SlaveStateLog slvstlog = new SlaveStateLog(user.getOn_time(), new Date(), (byte)1, user.getUser_id(), user.getNow_temp(), user.getFinal_temp(), user.getWind(), user.getWind(), mode, 1, (int)user.getFree(), (byte)1);
                insert(slvstlog);
            }
            else if ( !user.isIs_open() ){
                int mode;
                if ( is_cool == true ) mode = 0;
                else mode = 1;

                SlaveStateLog slvstlog = new SlaveStateLog(user.getOn_time(), new Date(), (byte)0, user.getUser_id(), user.getNow_temp(), user.getFinal_temp(), user.getWind(), user.getWind(), mode, 1, (int)user.getFree(), (byte)1);
                insert(slvstlog);
            }
            users.replace(key, user);
        }
        int mode = 0;
        if (is_cool)
            mode = 0;
        else
            mode = 1;
        insert(new HostStateLog(null, null, (byte)1, user_list.size(), num_dd(), mode));
    }

    //当前可以被调度的从机数
    private int num_can_dd() {
        int num = 0;
        Iterator iter = user_list.entrySet().iterator();
        User user;

        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            user = user_list.get(entry.getKey());
            if(user.isIs_open())
                if(user.isIs_dd() || (is_cool)?(user.getNow_temp()  > user.getFinal_temp()):(user.getNow_temp()  < user.getFinal_temp()))
                    num ++;
        }
        return num;
    }

    //判断此前处于调度状态从机数
    private int num_dd() {
        int num = 0;
        Iterator iter = user_list.entrySet().iterator();
        User user;

        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            user = user_list.get(entry.getKey());
            if(user.isIs_dd())
                num ++;
        }
        return num;
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

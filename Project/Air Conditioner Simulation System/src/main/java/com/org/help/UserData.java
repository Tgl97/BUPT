package com.org.help;

import com.org.run.User;

/**
 * Created by lizhongwei on 2017/4/28.
 * 数据管理类：
 * 用户数据管理
 */
public class UserData {
    // 用户
    private User user;
    // 制热制冷：mode = false制热，mode = true制冷
    private boolean mode;
    // 费用
    private String fee;

    /**
     * @desc 构造函数
     * @author Created by lizhongwei on 2017/4/28.
     * @param user 用户
     * @param mode 制热制冷：mode = false制热，mode = true制冷
     * @param fee 费用
     */
    public UserData(User user, boolean mode, String fee) {
        this.user = user;
        this.mode = mode;
        this.fee = fee;
    }

    /**
     * @desc 获取用户
     * @author Created by lizhongwei on 2017/4/28.
     * @return 用户
     */
    public User getUser() {
        return user;
    }

    /**
     * @desc 设置用户
     * @author Created by lizhongwei on 2017/4/28.
     * @param user 用户
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @desc 获取制热或制冷模式
     * @author Created by lizhongwei on 2017/4/28.
     * @return mode = false制热，mode = true制冷
     */
    public boolean getMode() {
        return mode;
    }

    /**
     * @desc 设置模式
     * @author Created by lizhongwei on 2017/4/28.
     * @param mode 模式。mode = false制热，mode = true制冷
     */
    public void setMode(boolean mode) {
        this.mode = mode;
    }

    /**
     * @desc 获取费用
     * @author Created by lizhongwei on 2017/4/28.
     * @return 费用
     */
    public String getFee() {
        return fee;
    }

    /**
     * @desc 设置费用
     * @author Created by lizhongwei on 2017/4/28.
     * @param fee 费用
     */
    public void setFee(String fee) {
        this.fee = fee;
    }

}

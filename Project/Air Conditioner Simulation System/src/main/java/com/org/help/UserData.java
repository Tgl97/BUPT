package com.org.help;

import com.org.run.User;

/**
 * Created by lizhongwei on 2017/4/28.
 * ���ݹ����ࣺ
 * �û����ݹ���
 */
public class UserData {
    // �û�
    private User user;
    // �������䣺mode = false���ȣ�mode = true����
    private boolean mode;
    // ����
    private String fee;

    /**
     * @desc ���캯��
     * @author Created by lizhongwei on 2017/4/28.
     * @param user �û�
     * @param mode �������䣺mode = false���ȣ�mode = true����
     * @param fee ����
     */
    public UserData(User user, boolean mode, String fee) {
        this.user = user;
        this.mode = mode;
        this.fee = fee;
    }

    /**
     * @desc ��ȡ�û�
     * @author Created by lizhongwei on 2017/4/28.
     * @return �û�
     */
    public User getUser() {
        return user;
    }

    /**
     * @desc �����û�
     * @author Created by lizhongwei on 2017/4/28.
     * @param user �û�
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @desc ��ȡ���Ȼ�����ģʽ
     * @author Created by lizhongwei on 2017/4/28.
     * @return mode = false���ȣ�mode = true����
     */
    public boolean getMode() {
        return mode;
    }

    /**
     * @desc ����ģʽ
     * @author Created by lizhongwei on 2017/4/28.
     * @param mode ģʽ��mode = false���ȣ�mode = true����
     */
    public void setMode(boolean mode) {
        this.mode = mode;
    }

    /**
     * @desc ��ȡ����
     * @author Created by lizhongwei on 2017/4/28.
     * @return ����
     */
    public String getFee() {
        return fee;
    }

    /**
     * @desc ���÷���
     * @author Created by lizhongwei on 2017/4/28.
     * @param fee ����
     */
    public void setFee(String fee) {
        this.fee = fee;
    }

}

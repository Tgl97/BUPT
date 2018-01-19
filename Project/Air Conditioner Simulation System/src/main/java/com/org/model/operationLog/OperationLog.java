package com.org.model.operationLog;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lizhongwei on 2017/4/6.
 * 操作日志实现
 */
@MappedSuperclass
public class OperationLog implements Serializable{
    protected static final long serialVersionUID = 1L;

    protected int keyId;
    protected Date operationTime;
    protected String operationContent;

    public OperationLog() {
        operationTime = null;
        operationContent = null;
    }
	//写入日志相关信息
    public OperationLog(int keyId, Date operationTime, String operationContent) {
        this.keyId = keyId;
        this.operationTime = operationTime;
        this.operationContent = operationContent;
    }

    public OperationLog(Date operationTime, String operationContent) {
        this.operationTime = operationTime;
        this.operationContent = operationContent;
    }

    @Id
    @Column(name = "key_id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getKeyId() {
        return keyId;
    }

    @Basic
    @Column(name = "operation_time")
    public Date getOperationTime() {
        return operationTime;
    }

    @Basic
    @Column(name = "operation_content")
    public String getOperationContent() {
        return operationContent;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}

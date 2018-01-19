package com.org.model.operationLog;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Lizhongwei on 2017/4/6.
 * 主机操作日志实例
 * 实现主机相关日志的保存
 */
@Entity
//操作的表为host_operation_log_table
@Table(name = "host_operation_log_table", schema = "dtcs_db")
public class HostOperationLog extends OperationLog {
    private String operationAccount;

    public HostOperationLog() {
        super();
        operationAccount = null;
    }
	//调用父类的方法
    public HostOperationLog(int keyId, Date operationTime,
                            String operationAccount, String operationContent) {
        super(keyId, operationTime, operationContent);
        this.operationAccount = operationAccount;
    }
	//调用父类的方法
    public HostOperationLog(Date operationTime, String operationContent, String operationAccount) {
        super(operationTime, operationContent);
        this.operationAccount = operationAccount;
    }

    @Basic
    @Column(name = "operation_account")
    public String getOperationAccount() {
        return operationAccount;
    }

    public void setOperationAccount(String operationAccount) {
        this.operationAccount = operationAccount;
    }
}

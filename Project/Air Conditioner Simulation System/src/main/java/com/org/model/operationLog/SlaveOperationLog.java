package com.org.model.operationLog;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Lizhongwei on 2017/4/6.
 * 从机操作日志实例
 */
@Entity
@Table(name = "slave_operation_log_table", schema = "dtcs_db")
public class SlaveOperationLog extends OperationLog {
    private int slaveId;
    public SlaveOperationLog() {
        super();
        slaveId = 0;
    }

    public SlaveOperationLog(int keyId, Date operationTime,
                             int slaveId, String operationContent) {
        super(keyId, operationTime, operationContent);
        this.slaveId = slaveId;
    }

    public SlaveOperationLog(Date operationTime, String operationContent, int slaveId) {
        super(operationTime, operationContent);
        this.slaveId = slaveId;
    }

    @Basic
    @Column(name = "slave_id")
    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }
}

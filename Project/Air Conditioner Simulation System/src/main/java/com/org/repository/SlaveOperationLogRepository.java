package com.org.repository;

import com.org.model.operationLog.SlaveOperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lizhongwei on 2017/4/15.
 * ���ݿ�����ӿڣ�
 * ��SlaveOperationLogʵ�����Ӧ�ı�Ĳ����ӿ�
 */
@Repository
public interface SlaveOperationLogRepository extends JpaRepository<SlaveOperationLog, Integer>{

    /**
     * @desc ����@Query����ʽ���в���
     * @author Created by lizhongwei on 2017/4/15.
     * @param beginDate ��ʼ����
     * @param endDate ��������
     * @return ���в��ҵ���ʵ�������
     */
    @Transactional
    @Query("SELECT t FROM SlaveOperationLog t WHERE t.operationTime >= :qBeginDate AND t.operationTime <= :qEndDate")
    public List<SlaveOperationLog> find(@Param("qBeginDate")Date beginDate, @Param("qEndDate")Date endDate);
}

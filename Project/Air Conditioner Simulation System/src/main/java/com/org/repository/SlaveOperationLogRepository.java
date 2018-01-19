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
 * 数据库操作接口：
 * 对SlaveOperationLog实体类对应的表的操作接口
 */
@Repository
public interface SlaveOperationLogRepository extends JpaRepository<SlaveOperationLog, Integer>{

    /**
     * @desc 根据@Query的形式进行查找
     * @author Created by lizhongwei on 2017/4/15.
     * @param beginDate 起始日期
     * @param endDate 结束日期
     * @return 所有查找到的实体类对象
     */
    @Transactional
    @Query("SELECT t FROM SlaveOperationLog t WHERE t.operationTime >= :qBeginDate AND t.operationTime <= :qEndDate")
    public List<SlaveOperationLog> find(@Param("qBeginDate")Date beginDate, @Param("qEndDate")Date endDate);
}

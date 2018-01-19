package com.org.repository;

import com.org.model.statistic.RequestStopStatistic;
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
 * 对RequestStopStatistic实体类对应的表的操作接口
 */
@Repository
public interface RequestStopStatisticRepository extends JpaRepository<RequestStopStatistic, Integer>{

    /**
     * @desc 根据@Query的形式进行查找
     * @author Created by lizhongwei on 2017/4/15.
     * @param id 对应外键
     * @param beginDate 起始日期
     * @param endDate 结束日期
     * @return 所有查找到的实体类对象
     */
    @Transactional
    @Query("SELECT t FROM RequestStopStatistic t WHERE t.ID = :userId AND t.requestTime >= :qBeginDate AND t.stopTime <= :qEndDate")
    public List<RequestStopStatistic> find(@Param("userId")Integer id, @Param("qBeginDate")Date beginDate, @Param("qEndDate")Date endDate);
}

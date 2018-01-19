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
 * ���ݿ�����ӿڣ�
 * ��RequestStopStatisticʵ�����Ӧ�ı�Ĳ����ӿ�
 */
@Repository
public interface RequestStopStatisticRepository extends JpaRepository<RequestStopStatistic, Integer>{

    /**
     * @desc ����@Query����ʽ���в���
     * @author Created by lizhongwei on 2017/4/15.
     * @param id ��Ӧ���
     * @param beginDate ��ʼ����
     * @param endDate ��������
     * @return ���в��ҵ���ʵ�������
     */
    @Transactional
    @Query("SELECT t FROM RequestStopStatistic t WHERE t.ID = :userId AND t.requestTime >= :qBeginDate AND t.stopTime <= :qEndDate")
    public List<RequestStopStatistic> find(@Param("userId")Integer id, @Param("qBeginDate")Date beginDate, @Param("qEndDate")Date endDate);
}

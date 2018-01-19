package com.org.repository;

import com.org.model.statistic.OnOffStatistic;
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
 * ��OnOffStatisticʵ�����Ӧ�ı�Ĳ����ӿ�
 */
@Repository
public interface OnOffStatisticRepository extends JpaRepository<OnOffStatistic, Integer>{
    @Transactional

    /**
     * @desc ����@Query����ʽ���в���
     * @author Created by lizhongwei on 2017/4/15.
     * @param id �ӻ���
     * @param beginDate ��ʼ����
     * @param endDate ��������
     * @return ���в��ҵ���ʵ�������
     */
    @Query("SELECT t FROM OnOffStatistic t WHERE t.slaveId = :userId AND t.powerOnTime >= :qBeginDate AND t.powerOffTime <= :qEndDate")
    public List<OnOffStatistic> find(@Param("userId")Integer id, @Param("qBeginDate")Date beginDate, @Param("qEndDate")Date endDate);
}

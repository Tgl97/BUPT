package com.org.help;

import com.org.model.statistic.OnOffStatistic;
import com.org.model.statistic.RequestStopStatistic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by lizhongwei on 2017/4/11.
 * ���ݿ�����ࣺ
 * ���ݵĲ��롢����
 */
public class DBOperator {
    private static EntityManagerFactory entityManagerFactory;

    /**
     * @desc ��ʼ��ʵ�������
     * Created by lizhongwei on 2017/4/11.
     */
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    /**
     * @desc �ر�ʵ�������
     * Created by lizhongwei on 2017/4/11.
     */
    public static void tearDown() {
        entityManagerFactory.close();
    }

    /**
     * @desc ���ݲ������
     * @author Created by lizhongwei on 2017/4/11.
     * @param e �����������ʵ�����
     * @param <T>
     */
    public static <T> void insertEntity(T e){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(e);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * @desc �Ա�on_off_statistic�ĸ��²���
     * @param e ���µ�����
     */
    public static void updateEntity(OnOffStatistic e) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("UPDATE OnOffStatistic SET powerOffTime=:offTime WHERE keyId = " + e.getKeyId()).setParameter("offTime", e.getPowerOffTime()).executeUpdate();
        entityManager.createQuery("UPDATE OnOffStatistic SET lowLevelWindTime=:lowWindTime WHERE keyId = " + e.getKeyId()).setParameter("lowWindTime", e.getLowLevelWindTime()).executeUpdate();
        entityManager.createQuery("UPDATE OnOffStatistic SET middleLevelWindTime=:middleWindTime WHERE keyId = " + e.getKeyId()).setParameter("middleWindTime", e.getMiddleLevelWindTime()).executeUpdate();
        entityManager.createQuery("UPDATE OnOffStatistic SET highLevelWindTime=:highWindTime WHERE keyId = " + e.getKeyId()).setParameter("highWindTime", e.getHighLevelWindTime()).executeUpdate();
        entityManager.createQuery("UPDATE OnOffStatistic SET totalFee=:fee WHERE keyId = " + e.getKeyId()).setParameter("fee", e.getTotalFee()).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

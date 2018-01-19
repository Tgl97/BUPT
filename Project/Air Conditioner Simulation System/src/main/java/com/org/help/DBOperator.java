package com.org.help;

import com.org.model.statistic.OnOffStatistic;
import com.org.model.statistic.RequestStopStatistic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by lizhongwei on 2017/4/11.
 * 数据库操作类：
 * 数据的插入、更新
 */
public class DBOperator {
    private static EntityManagerFactory entityManagerFactory;

    /**
     * @desc 初始化实体管理工厂
     * Created by lizhongwei on 2017/4/11.
     */
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    /**
     * @desc 关闭实体管理工厂
     * Created by lizhongwei on 2017/4/11.
     */
    public static void tearDown() {
        entityManagerFactory.close();
    }

    /**
     * @desc 数据插入操作
     * @author Created by lizhongwei on 2017/4/11.
     * @param e 被插入的数据实体对象
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
     * @desc 对表on_off_statistic的更新操作
     * @param e 更新的数据
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

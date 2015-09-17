package com.dong.sitserver.common;


import java.util.List;

/**
 * Created by rxia on 2015/9/2.
 */
public interface BaseDAO<T> {
    /**
     * 添加
     *
     * @param t
     * @return
     */
    Integer addBean(T t);

    /**
     * 更新
     *
     * @param t
     */
    void updateBean(T t);

    /**
     * 查询
     *
     * @param t
     * @return
     */
    List<T> queryBeans(T t);


    /**
     * 统计
     *
     * @param t
     * @return
     */
    Integer countBean(T t);


    /**
     * 查找
     *
     * @param t
     * @return
     */
    T findBean(T t);

    void deleteBean(T t);
}

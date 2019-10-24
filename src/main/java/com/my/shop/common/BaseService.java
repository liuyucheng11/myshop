package com.my.shop.common;

/**
 * <p>基础操作DAO接口</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-23  16:08
 * @version 1.0
 */
public interface BaseService<T> {

    int updateById(T entity);

    int insertEntity(T entity);

    int deleteById(long id);

}

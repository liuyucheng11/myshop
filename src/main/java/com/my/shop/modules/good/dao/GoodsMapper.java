package com.my.shop.modules.good.dao;

import com.my.shop.common.QueryParam;
import com.my.shop.modules.good.pojo.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectPage(QueryParam param);
}
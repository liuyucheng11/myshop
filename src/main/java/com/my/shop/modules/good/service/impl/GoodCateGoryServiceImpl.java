package com.my.shop.modules.good.service.impl;

import com.my.shop.modules.good.dao.GoodsCategoryMapper;
import com.my.shop.modules.good.pojo.GoodsCategory;
import com.my.shop.modules.good.service.GoodCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * @author liu.yucheng
 * Date: 2019-10-23  16:16
 * @version 1.0
 */
@Service("goodCategoryService")
public class GoodCateGoryServiceImpl implements GoodCateGoryService {
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public int updateById(GoodsCategory entity) {
        return goodsCategoryMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int insertEntity(GoodsCategory entity) {
        return goodsCategoryMapper.insertSelective(entity);
    }

    @Override
    public int deleteById(long id) {
        return goodsCategoryMapper.deleteByPrimaryKey(id);
    }
}

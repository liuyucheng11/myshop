package com.my.shop.modules.good.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.my.shop.common.QueryParam;
import com.my.shop.common.ResultPage;
import com.my.shop.modules.good.dao.GoodsMapper;
import com.my.shop.modules.good.pojo.Goods;
import com.my.shop.modules.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liu.yucheng
 * Date: 2019-10-23  16:12
 * @version 1.0
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int updateById(Goods entity) {
        return goodsMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int insertEntity(Goods entity) {
        return goodsMapper.insertSelective(entity);
    }

    @Override
    public int deleteById(long id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ResultPage getResultPage(QueryParam queryParam) {
        Page<?> page = PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        List<Goods> list = goodsMapper.selectPage(queryParam);
        return new ResultPage(list, (int) page.getTotal(), queryParam.getLimit(), queryParam.getPage());

    }
}

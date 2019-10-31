package com.my.shop.modules.good.controller;

import com.my.shop.common.QueryParam;
import com.my.shop.common.Result;
import com.my.shop.config.annotation.MonitorConfig;
import com.my.shop.config.annotation.TokenConfig;
import com.my.shop.modules.good.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liu.yucheng
 * Date: 2019-10-22  14:14
 * @version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    GoodService goodService;

    @RequestMapping("/addGoods")
    @MonitorConfig(needLogin = true)
    @TokenConfig
    public Object addGoods() {
        return new String("aaaa");
    }

    @RequestMapping(value = "/deleteGoods")
    @MonitorConfig(needLogin = true)
    @TokenConfig
    public Object deleteGoods() {

        return "删除商品";
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Result getGoods(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("page",currentPage);
        param.put("limit",pageSize);
        QueryParam queryParam = new QueryParam(param);
        return Result.ok().put("data",goodService.getResultPage(queryParam));

    }
}

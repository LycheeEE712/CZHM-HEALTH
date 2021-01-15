package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/15
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {
    @Reference
    private SetmealService setmealService;

    /**
     * 套餐列表
     *
     * @return
     */
    @GetMapping("/getSetmeal")
    public Result getSetmeal() {
        // 调用服务来查询所有的套餐
        List<Setmeal> list = setmealService.findAll();
        // 拼接图片的完整路径
        list.forEach(s -> s.setImg(QiNiuUtils.DOMAIN + s.getImg()));
        // 返回给页面
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, list);
    }

    /**
     * 通过id查询套餐详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findDetailById")
    public Result findDetailById(int id) {
        //调用服务查询套餐详情
        Setmeal setmeal = setmealService.findDetailById(id);
        //拼接图片的完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 通过id查询套餐详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findDetailById2")
    public Result findDetailById2(int id) {
        // 调用服务查询套餐详情
        Setmeal setmeal = setmealService.findDetailById2(id);
        // 拼接图片的完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 通过id查询套餐详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findDetailById3")
    public Result findDetailById3(int id) {
        // 调用服务查询套餐详情
        Setmeal setmeal = setmealService.findDetailById3(id);
        // 拼接图片的完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }
}

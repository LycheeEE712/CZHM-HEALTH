package com.itheima.health.service;

import com.itheima.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author: LYCHEE
 * @since: 2021/1/13
 */
public interface OrderSettingService {
    /**
     * 批量导入预约设置
     *
     * @param orderSettingList
     */
    void addBatch(List<OrderSetting> orderSettingList);

    /**
     * 通过月份查询预约设置信息
     *
     * @param month
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMonth(String month);

    /**
     * 通过日期设置可预约的最大数
     *
     * @param orderSetting
     * @return
     */
    void editNumberByDate(OrderSetting orderSetting);
}

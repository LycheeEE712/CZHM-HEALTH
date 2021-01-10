package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/6
 */
public interface CheckItemService {

    /**
     * 添加检查项
     *
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 查询所有
     *
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 分页查询检查项
     *
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id删除
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * 编辑检查项
     *
     * @param checkItem
     */
    void update(CheckItem checkItem);
}

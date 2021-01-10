package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/6
 */
public interface CheckItemDao {
    /**
     * 查询所有
     *
     * @return
     */
    public List<CheckItem> findAll();


    /**
     * 添加检查项
     *
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 条件查询
     *
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 统计使用这个检查项的数量
     *
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

    /**
     * 删除检查项
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

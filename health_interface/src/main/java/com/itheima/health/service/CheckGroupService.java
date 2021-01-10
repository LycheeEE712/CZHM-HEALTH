package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/9
 */
public interface CheckGroupService {

    /**
     * 添加检查组服务
     *
     * @param checkgroup
     * @param checkitemIds
     */
    void add(CheckGroup checkgroup, Integer[] checkitemIds);

    /**
     * 分页查询检查组
     *
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过检查组id查询勾选的检查项id
     *
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 编辑检查组信息
     * 选中的检查项id数组
     *
     * @param checkgroup
     * @param checkitemIds
     */
    void update(CheckGroup checkgroup, Integer[] checkitemIds);
}

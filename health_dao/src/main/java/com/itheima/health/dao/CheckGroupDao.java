package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/9
 */
public interface CheckGroupDao {
    /**
     * 添加检查组
     *
     * @param checkgroup
     */
    void add(CheckGroup checkgroup);

    /**
     * 添加检查组与检查项关系
     *
     * @param checkgroupId
     * @param checkitemId
     */
    //参数类型一模一样且都是基础数据类型,要用@Param取别名,因为反射是通过类型来区分的
    void addCheckGroupCheckItem(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 检查组按条件查询
     *
     * @param queryString
     * @return
     */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 通过id查询检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过检查组id查询选中的检查项id
     *
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 编辑检查组
     *
     * @param checkgroup
     */
    void update(CheckGroup checkgroup);

    /**
     * 删除旧关系
     *
     * @param id
     */
    void deleteCheckGroupCheckItem(Integer id);
}

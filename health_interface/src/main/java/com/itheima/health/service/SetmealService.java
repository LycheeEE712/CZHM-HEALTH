package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Setmeal;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/11
 */
public interface SetmealService {
    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 查询选中的检查组id集合
     * @param id
     * @return
     */
    List<Integer> findCheckGroupIdsBySetmealId(int id);

    /**
     * 修改套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 通过id删除套餐
     * @param id
     */
    void deleteById(int id) throws MyException;

    /**
     * 查询套餐的所有图片
     * @return
     */
    List<String> findImgs();

    /**
     * 套餐列表
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 通过id查询套餐详情
     * @param id
     * @return
     */
    Setmeal findDetailById(int id);

    Setmeal findDetailById2(int id);

    Setmeal findDetailById3(int id);
}

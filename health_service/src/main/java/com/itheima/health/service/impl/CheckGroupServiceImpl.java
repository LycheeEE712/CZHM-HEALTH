package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/9
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组实现类方法
     *
     * @param checkgroup   检查组数据
     * @param checkitemIds 勾选的检查项的id
     */

    //- 添加事务控制
    @Transactional
    @Override
    public void add(CheckGroup checkgroup, Integer[] checkitemIds) {
        //- 先添加检查组
        checkGroupDao.add(checkgroup);
        //- 获取检查组的id
        Integer checkgroupId = checkgroup.getId();
        //- 遍历选中的检查项id的数组
        if (null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                //添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkgroupId, checkitemId);
            }
        }
    }

    /**
     * 检查项的分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        // pagesize不可以无限大,后台要限制大小
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //条件查询
        if (StringUtils.isNotEmpty(queryPageBean.getQueryString())) {
            //模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        //Page extends ArrayList 所以可以以数组形式接收数据
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckGroup> pageResult = new PageResult<CheckGroup>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 通过id查询检查组
     *
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 通过检查组id查询选中的检查项id
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 编辑检查组
     *
     * @param checkgroup   检查组信息
     * @param checkitemIds 选中的检查项id数组
     */
    @Override
    @Transactional
    public void update(CheckGroup checkgroup, Integer[] checkitemIds) {
        checkGroupDao.update(checkgroup);
        //删除旧关系
        checkGroupDao.deleteCheckGroupCheckItem(checkgroup.getId());
        if (null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkgroup.getId(), checkitemId);
            }
        }
    }

    /**
     * 通过id删除检查组
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        //通过检查组id查询是否被套餐使用了
        int count = checkGroupDao.findCountByCheckGroupId(id);
        // 使用了，抛出异常
        if (count > 0) {
            throw new MyException("该检查组被套餐使用了，不能删除");
        }
        //没使用，删除检查组与检查项的关系
        checkGroupDao.deleteCheckGroupCheckItem(id);
        //删除检查组
        checkGroupDao.deleteById(id);
        //事务控制
    }

    /**
     * 查询所有的检查组
     *
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}

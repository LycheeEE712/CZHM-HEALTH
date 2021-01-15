package com.itheima.health.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LYCHEE
 * @since: 2021/1/12
 */

/**
 * 创建health_job工程
 * 引入依赖，quartz, health_interface
 * 创建任务类 定义清理垃圾图片的方法
 * @compoent, @Scheduled(表达式）
 * 配置文件
 * task:annotation-driven, ThreadPoolTaskScheduler
 * 应用名称
 * 注册中心在哪
 * dubbo注解扫包(controller时的扫包)
 * 创建启动类, 加载配置文件
 */

@Component
public class CleanImgJob {

    @Reference
    private SetmealService setmealService;

    private static final Logger log = LoggerFactory.getLogger(CleanImgJob.class);


    @Scheduled(cron = "0 0 4 * * ?")
    public void cleanImg() {
        log.info("开始执行清理垃圾图片....");

        //调用QiNiuUtils.查询所有的图片
        List<String> imgIn7Niu = QiNiuUtils.listFile();
        log.debug("7牛上一共有{}张图片", imgIn7Niu.size());

        //调用setmealService查询数据库的所有图片
        List<String> imgInDb = setmealService.findImgs();
        log.debug("数据库里一共有{}张图片", imgInDb == null ? 0 : imgInDb.size());

        //把七牛上的减去数据库的，剩下的就是要删除的图片
        imgIn7Niu.removeAll(imgInDb);
        if (imgIn7Niu.size() > 0) {
            //调用七牛工具删除垃圾图片
            log.debug("要清理的垃圾图片有{}张", imgIn7Niu.size());
            QiNiuUtils.removeFiles(imgIn7Niu.toArray(new String[]{}));
        } else {
            log.debug("没有需要清理的垃圾图片");
        }
        log.info("清理垃圾图片完成....");
    }

}

package com.dfe.plateform.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * 自定义注解 + AOP的方式实现数据源动态切换。
 */
@Aspect
// 该切面应当先于 @Transactional 执行
@Order(-1)
@Component
public class DynamicDataSourceAspect {
    /** * Switch DataSource * * @param point * @param targetDataSource */
    @Before("@annotation(targetDataSource))")
    public void switchDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(targetDataSource.value())) {
        } else {
            // 切换数据源
            DynamicDataSourceContextHolder.setDataSourceKey(targetDataSource.value());
        }
    }

    /** * Restore DataSource * * @param point * @param targetDataSource */
    @After("@annotation(targetDataSource))")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }
}


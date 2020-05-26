package com.example.demo.dataSource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/***
 * 调用不同的service,
 * 使用aop切换数据源
 * @author wangjingpei
 *
 */
@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class DataSourceSwitchAspect {

	@Pointcut("execution(* com.example.demo.service.db1..*.*(..))")
    private void db1Aspect() {
    }

    @Pointcut("execution(* com.example.demo.service.db2..*.*(..))")
    private void db2Aspect() {
    }

    @Before( "db1Aspect()" )
    public void db1() {
        System.out.println("切换到db1 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }

    @Before("db2Aspect()" )
    public void db2 () {
    	System.out.println("切换到db2 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }
}

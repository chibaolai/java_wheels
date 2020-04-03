package com.bolly.stub.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Seifon
 * @Description:
 * @Date: Created in 10:24 2019/1/7
 */
@Aspect
@Component
public class FeignMockAspect {

    private static final Logger LOG = LoggerFactory.getLogger(FeignMockAspect.class);

    @Pointcut("execution(* com.bolly.stub.client..mock.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp){
        String name = StringUtils.join(pjp.getTarget().getClass().getName(), ".", pjp.getSignature().getName());
        LOG.info("-----【{}】---- 进入Mock模式... request: 【{}】", name, JSON.toJSON(pjp.getArgs()));
        try {
            Object proceed = pjp.proceed();
            LOG.info("-----【{}】---- 退出Mock模式... request: 【{}】, response: 【{}】", name, JSON.toJSON(pjp.getArgs()), JSON.toJSON(proceed));
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

}

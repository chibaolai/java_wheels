package com.bolly.stub.aspect;

import com.bolly.support.utils.JacksonUtils;
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
public class FeignStubAspect {

    private static final Logger LOG = LoggerFactory.getLogger(FeignStubAspect.class);

    @Pointcut("execution(* com.bolly.stub.client..stub.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp){
        String name = StringUtils.join(pjp.getTarget().getClass().getName(), ".", pjp.getSignature().getName());
        LOG.info("-----【{}】---- 进入挡板模式... request: 【{}】", name, JacksonUtils.marshal(pjp.getArgs()));
        try {
            Object proceed = pjp.proceed();
            LOG.info("-----【{}】---- 退出挡板模式... request: 【{}】, response: 【{}】", name, JacksonUtils.marshal(pjp.getArgs()), JacksonUtils.marshal(proceed));
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

}

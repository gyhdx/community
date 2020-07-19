package com.wf.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/19 23:06
 */
//@Component
//@Aspect
//public class AlphaAspect {
//
//    @Pointcut("execution(* com.wf.community.service.*.*(..))")
//    public void pointcut(){}
//
//    @Before("pointcut()")
//    public void before(){
//        System.out.println("-----before Advice----");
//    }
//
//    @After("pointcut()")
//    public void after(){
//        System.out.println("-----after Advice----");
//    }
//
//    @AfterReturning("pointcut()")
//    public void afterReturning(){
//        System.out.println("-----afterReturning Advice----");
//    }
//
//    @AfterThrowing("pointcut()")
//    public void afterThrowing(){
//        System.out.println("-----afterThrowing Advice----");
//    }
//
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("around before");
//        Object obj = null;
//        try {
//            // 要执行的方法
//            obj = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            System.out.println("-----afterThrowing Advice----");
//            throwable.printStackTrace();
//        } finally {
//            System.out.println("-----afterReturning Advice----");
//        }
//        System.out.println("around after");
//        return obj;
//    }
//}

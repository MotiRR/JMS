package com.geekbrains.book.store.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ServiceAspect {

    private Map<String, Integer> map = new HashMap<>();

    @Pointcut("execution(* com.geekbrains.book.store.services.BookService.*(..))")
    public void logBeforeBookService() {
    }

    @Before("logBeforeBookService()")
    public void logBeforeBookService(JoinPoint joinPoint) {
        fillMap("Сервис по работе с книгами (BookService)");
    }

    @Pointcut("execution(* com.geekbrains.book.store.services.OrderItemService.*(..))")
    public void logBeforeOrderItemService() {
    }

    @Before("logBeforeOrderItemService()")
    public void logBeforeOrderItemService(JoinPoint joinPoint) {
        fillMap("Сервис по работе с экземплярами заказа (OrderItemService)");
    }

    @Pointcut("execution(* com.geekbrains.book.store.services.OrderService.*(..))")
    public void logBeforeOrderService() {
    }

    @Before("logBeforeOrderService()")
    public void logBeforeOrderService(JoinPoint joinPoint) {
        fillMap("Сервис по работе с заказами (OrderService)");
    }

    @Pointcut("execution(* com.geekbrains.book.store.services.UserService.*(..))")
    public void logBeforeUserService() {
    }

    @Before("logBeforeUserService()")
    public void logBeforeUserService(JoinPoint joinPoint) {
        fillMap("Сервис по работе с пользователями (UserService)");
    }

    private void fillMap(String key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public Map<String, Integer> getMap() {
        return map;
    }
}

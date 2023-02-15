package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
    MyAdvice myAdvice=new MyAdvice();

    Class myClass=Class.forName("com.fastcampus.ch3.aop.MyClass");
    Object obj=myClass.newInstance();//클래스 객체로부터 객체 생성

        //반복문을 이용해 마이클래스의 메서드를 하나씩 호출한다
        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }
    }
}
class MyAdvice{
//    Pattern p = Pattern.compile("a.*");

//    boolean matches(Method m) {
////        Matcher matcher = p.matcher(m.getName());
////        return matcher.matches();
//    }
    void invoke(Method m,Object obj,Object... args)throws Exception {
        if(m.getAnnotation(Transactional.class)!=null)
        System.out.println("[before]");
        m.invoke(obj, args); //aaa,aaa2,bbb를 호출 가능
        if(m.getAnnotation(Transactional.class)!=null)
        System.out.println("[after]");
    }
}
class MyClass{
    @Transactional
    void aaa(){
        System.out.println("aaa is called");
    }
    @Transactional
    void aaa2(){
        System.out.println("aaa2 is called");
    }
    @Transactional
    void bbb(){
        System.out.println("bbb is called");
    }
}

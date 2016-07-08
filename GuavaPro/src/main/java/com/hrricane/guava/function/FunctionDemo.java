package com.hrricane.guava.function;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.hrricane.guava.function.impl.StateToCityString;
import com.hrricane.guava.function.impl.test;
import com.hrricane.guava.function.pojo.State;
import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhenbiao.shen on 2016/7/8
 * 接受函数当作输入（参数）和输出（返回值）。和指令式编程相比，函数式编程强调函数的计算比指令的执行重要。和过程化编程相比，函数式编程里，函数的计算可随时调用。.
 *
 * Guava提供两个基本的函数式接口：

    Function<A, B>，它声明了单个方法B apply(A input)。Function对象通常被预期为引用透明的——没有副作用——并且引用透明性中的”相等”语义与equals一致，如a.equals(b)意味着function.apply(a).equals(function.apply(b))。
    Predicate<T>，它声明了单个方法boolean apply(T input)。Predicate对象通常也被预期为无副作用函数，并且”相等”语义与equals一致。
 */
public class FunctionDemo {

    public static void main(String[] args) {
//        Map<String,State> stateMap=new HashMap<String,State>();
//        State s=new State("New York");
//        s.addCity("new");
//        s.addCity("york");
//        stateMap.put("NY",s);
//
//        //Functions.forMap 接收一个Map集合作为参数，返回一个Function，用于执行Map集合的查找
//        Function<String,State> lookup= Functions.forMap(stateMap);
//        Function<State,String> stateFunction=new StateToCityString();
//
//        //Function<A, C> compose(Function<B, C> g, Function<A, ? extends B> f)
//        //接收两个Function作为参数，返回两个Function的组合
//        //f的输出会作为g的输入，g输出为最终作为compose的输出
//        Function<String,String> composed= Functions.compose(stateFunction,lookup);
//        String str=composed.apply("NY");
//        System.out.println(str);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        System.out.println(Joiner.on("-").join(year,month,date,hour));
    }
}

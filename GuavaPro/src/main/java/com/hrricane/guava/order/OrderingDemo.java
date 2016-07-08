package com.hrricane.guava.order;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.hrricane.guava.pojo.Foo;

import java.util.List;

/**
 * Created by zhenbiao.shen on 2016/7/7.\
 * ”流畅风格比较器”
 *
 *创建排序器：常见的排序器可以由下面的静态方法创建

             方法	描述
             natural()	对可排序类型做自然排序，如数字按大小，日期按先后排序
             usingToString()	按对象的字符串形式做字典排序[lexicographical ordering]
             from(Comparator)	把给定的Comparator转化为排序器
 *
 *
 * 链式调用方法：通过链式调用，可以由给定的排序器衍生出其它排序器
              reverse()	获取语义相反的排序器
               nullsFirst()	使用当前排序器，但额外把null值排到最前面。
               nullsLast()	使用当前排序器，但额外把null值排到最后面。
               compound(Comparator)	合成另一个比较器，以处理当前排序器中的相等情况。
               lexicographical()	基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
               onResultOf(Function)	对集合中元素调用Function，再按返回值用当前排序器排序。
 //当阅读链式调用产生的排序器时，应该从后往前读。上面的例子中，排序器首先调用apply方法获取sortedBy值，并把sortedBy为null的元素都放到最前面，然后把剩下的元素按sortedBy进行自然排序。之所以要从后往前读，是因为每次链式调用都是用后面的方法包装了前面的排序器。
 Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
     public String apply(Foo foo) {
        return foo.sortedBy;
     }
 });


 *
 *
 *
 *
 *
 *
 * 运用排序器：Guava的排序器实现有若干操纵集合或元素值的方法

             方法	描述	另请参见
             greatestOf(Iterable iterable, int k)	获取可迭代对象中最大的k个元素。	leastOf
             isOrdered(Iterable)	判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。	isStrictlyOrdered
             sortedCopy(Iterable)	判断可迭代对象是否已严格按排序器排序：不允许排序值相等的元素。	immutableSortedCopy
             min(E, E)	返回两个参数中最小的那个。如果相等，则返回第一个参数。	max(E, E)
             min(E, E, E, E...)	返回多个参数中最小的那个。如果有超过一个参数都最小，则返回第一个最小的参数。	max(E, E, E, E...)
             min(Iterable)	返回迭代器中最小的元素。如果可迭代对象中没有元素，则抛出NoSuchElementException。	max(Iterable), min(Iterator), max(Iterator)
 *
 *  http://ifeve.com/google-guava-ordering/
 */
public class OrderingDemo {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural();
        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));

        List<Integer> listReduce= Lists.newArrayList();
        for(int i=9;i>0;i--){
            listReduce.add(i);
        }

        List<Integer> listtest= Lists.newArrayList();
        listtest.add(1);
        listtest.add(1);
        listtest.add(1);
        listtest.add(2);


        Ordering<Integer> naturalIntReduceOrdering = Ordering.natural();

        System.out.println("listtest:"+ listtest);
        System.out.println(naturalIntReduceOrdering.isOrdered(listtest));
        System.out.println(naturalIntReduceOrdering.isStrictlyOrdered(listtest));


        System.out.println("naturalIntReduceOrdering:"+ naturalIntReduceOrdering.sortedCopy(listReduce));
        System.out.println("listReduce:"+ listReduce);


        System.out.println(naturalIntReduceOrdering.isOrdered(naturalIntReduceOrdering.sortedCopy(listReduce)));
        System.out.println(naturalIntReduceOrdering.isStrictlyOrdered(naturalIntReduceOrdering.sortedCopy(listReduce)));


        Ordering<String> natural = Ordering.natural();

        List<String> abc = ImmutableList.of("a", "b", "c");
        System.out.println(natural.isOrdered(abc));
        System.out.println(natural.isStrictlyOrdered(abc));

        System.out.println("isOrdered reverse :"+ natural.reverse().isOrdered(abc));

        List<String> cba = ImmutableList.of("c", "b", "a");
        System.out.println(natural.isOrdered(cba));
        System.out.println(natural.isStrictlyOrdered(cba));
        System.out.println(cba = natural.sortedCopy(cba));

        System.out.println("max:"+natural.max(cba));
        System.out.println("min:"+natural.min(cba));

        System.out.println("leastOf:"+natural.leastOf(cba, 2));
        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("leastOf list:"+naturalOrdering.leastOf(list, 3));
        System.out.println("greatestOf:"+naturalOrdering.greatestOf(list, 3));
        System.out.println("reverse list :"+ naturalOrdering.reverse().sortedCopy(list));
        System.out.println("isOrdered list :"+ naturalOrdering.isOrdered(list));
        System.out.println("isOrdered list :"+ naturalOrdering.reverse().isOrdered(list));
        list.add(null);
        System.out.println(" add null list:"+list);
        System.out.println("nullsFirst list :"+ naturalOrdering.nullsFirst().sortedCopy(list));
        System.out.println("nullsLast list :"+ naturalOrdering.nullsLast().sortedCopy(list));
    }
}

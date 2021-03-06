package com.ztom.juc.streamdemo;


import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 题目: 请按照给出数据,找出同时满足以下条件的用户,也即以下条件全部满足
 * 偶数ID且年龄大于24且用户名转为大写却用户名字母倒排序
 * 只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(15, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        }).filter(t -> {return t.getAge()> 24;}).map(m->{return m.getUserName().toUpperCase();}).sorted((o1, o2) ->{
            return o2.compareTo(o1);
        }).forEach(System.out::println);

//======================================================================================================================
////    Function<String, Integer> function = new Function<String, Integer>() {
////        @Override
////        public Integer apply(String s) {
////            return 1024;
////        }
////    };
//        Function<String, Integer> function = s -> {
//            return s.length();
//        };
//        System.out.println(function.apply("abc"));
//
////        Predicate<String> predicate = new Predicate<String>() {
////            @Override
////            public boolean test(String s) {
////                return s.isEmpty();
////            }
////        }
//
//        Predicate<String> predicate = s -> {
//            return s.isEmpty();
//        };
//        System.out.println(predicate.test("test"));
//
////        Consumer<String> consumer = new Consumer<String>() {
////            @Override
////            public void accept(String s) {
////
////            }
////        };
//        Consumer<String> consumer = s -> {
//            System.out.println(s);
//        };
//        consumer.accept("asdqwe");
//
////        Supplier<String> supplier = new Supplier<String>() {
////            @Override
////            public String get() {
////                return "123456";
////            }
////        };
//        Supplier<String> supplier = ()->{return "java0222";};
//        System.out.println(supplier.get());

    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private Integer id;
    private String userName;
    private int age;
}

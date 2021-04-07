package com.ztom.juc.notsafedemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 
 * 题目: 请举例说明集合类不是安全的
 * 
 * 1 故障现象
 * 	java.util.ConcurrentModificationException
 * 
 * 2 导致原因
 * 
 * 
 * 3 解决方案
 * 	3.1 Vector
 * 	3.2 Collections.synchronizedList(new ArrayList<>())
 *  3.3 CopyOnWriteArrayList
 *  
 * 4 优化建议
 * 
 * 写时复制
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候,不直接往当前容器Object[]添加,而是先将当前容器Object[]进行Copy,
 * 复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements里添加元素,添加完元素之后,
 * 再将原容器的引用指向新的容器 setArray(newElements); 这样做的好处是可以对CopyOnWrite容器进行并发的读,
 * 而不需要加锁,因为当前容器不会添加任何元素.所以CopyOnWrite容器也是一种读写分离的思想,读和写不同的容器
 */
public class NotSafeDemo {

	public static void main(String[] args) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();// Collections.synchronizedMap(new HashMap<String, String>()); // new HashMap<>(); // 初始容量16,负载因子 0.75
		
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}
	
	public static void setNotSafe() {
		Set<String> set = new CopyOnWriteArraySet<String>();// Collections.synchronizedSet(new HashSet());// new HashSet<>();
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
	}
	
	public static void listNotSafe() {
//		List<String> list = Arrays.asList("a", "b", "c");
//		list.forEach(System.out::println);

		List<String> list = new CopyOnWriteArrayList<>(); // Collections.synchronizedList(new ArrayList<>());// new Vector<>(); // new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}

}

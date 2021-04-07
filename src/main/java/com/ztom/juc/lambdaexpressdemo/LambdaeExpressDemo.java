package com.ztom.juc.lambdaexpressdemo;

@FunctionalInterface
interface Foo {
	// public void sayHello();
	public void add(int x, int y);
	
	default void div(int x, int y) {
		System.out.println(x/y);
	} 
}
/**
 * 2 Lambda Express
 *  2.1 口诀:
 *  	拷贝小括号,写死右箭头,落地大括号
 *	2.2 @FunctionalInterface 函数式接口注解
 *	2.3 default 接口实现方法 1.8特性
 * @author 61959
 *
 */
public class LambdaeExpressDemo {
	public static void main(String[] args) {
//		Foo foo = new Foo() {
//
//			@Override
//			public void sayHello() {
//				System.out.println("******Hello");
//			}
//			
//		};
//		Foo foo2 = ()->{
//			System.out.println("******Hello LambdaExpress");
//		};
//		foo.sayHello();
//		foo2.sayHello();
		//int x = 10, y = 11;
		Foo foo = (int x, int y) -> {
			System.out.println(x + y);
		};
		foo.add(3, 5);
		foo.div(6, 2);
	}
}

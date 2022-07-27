package com.Lyoto.Utils;

import java.util.function.Consumer;

/**
 @author Lyoto
 @Description
 @Date 2022-07-27 12:36
 @Version
 **/
public class CommonUtils {

	 public static <T> void doSomethingByCondition (boolean condition , DoSomething something){
		 if (condition) {
			 something.doIt();
		 }
	}
	 public static void doSomething ( DoSomething something){
		 doSomethingByCondition(true,something);
	}


	/**
	 @author Lyoto
	 @Description
	 @Date 2022-07-27 13:47
	 @Version
	 **/
	@FunctionalInterface
	public interface DoSomething {
		/**
		 * 抽象做事
		 */
		void doIt();
	}
}

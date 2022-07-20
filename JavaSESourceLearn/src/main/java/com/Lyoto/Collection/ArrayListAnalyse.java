package com.Lyoto.Collection;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

/**
 @author Lyoto
 @Description
 @Date 2022-07-20 11:52
 @Version
 **/
public class ArrayListAnalyse {

	/**
	 * #java.util.ArrayList.EMPTY_ELEMENTDATA
	 */
	@Test
	public void emptyElementData() {
		//长度为0时使用EMPTY_ELEMENTDATA 共享
		ArrayList<String> list = new ArrayList<>(0);

		ArrayList<String> list1 = new ArrayList<>(0);
		//输出为true
		System.out.println(list.equals(list1));
	}
	@Test
	public void defaultCapacityEmptyElementData(){
		// 无参构造器创建ArrayList对象默认赋值DEFAULTCAPACITY_EMPTY_ELEMENTDATA空数组
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> list1 = new ArrayList<>();
		//输出为true
		System.out.println(list.equals(list1));

	}
	@Test
	public void addTs(){
		ArrayList<String> arrayList = new ArrayList();
		arrayList.add("this");
	}
	/**
	 *
	 */
	public void foreachTest() {

	}
}

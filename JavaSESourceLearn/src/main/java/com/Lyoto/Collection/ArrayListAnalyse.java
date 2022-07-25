package com.Lyoto.Collection;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	@Test
	public void foreachTest() {
		ArrayList<String> list = new ArrayList<>();
		list.forEach(System.out::println);
		list.removeIf(list::contains);
		list.replaceAll(str->{
			if (str.isEmpty()){
			str = "this is empty";
			}
			return str;
		});
	}
	@Test
	public void subListTest() throws NoSuchFieldException {
		ArrayList<String> arrayList = Stream.of("1", "2", "3", "4").collect(Collectors.toCollection(ArrayList::new));

		List<String> list = arrayList.subList(1, 3);
		list.clear();
		arrayList.forEach(System.out::println);
	}


}

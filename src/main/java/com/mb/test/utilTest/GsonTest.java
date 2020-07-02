package com.mb.test.utilTest;

import java.util.List;

import com.mb.util.GsonUtil;

public class GsonTest {

	private static void testStudent() {
		String jsonData = "{'name':'John', 'age':20,'grade':{'course':'English','score':100,'level':'A'}}";
		Student student = GsonUtil.parseJsonWithGson(jsonData, Student.class);
		System.out.println(student.getGrade().getLevel());
	}

	private static void testPersons() {
		// Json数组最外层要加"[]"
		String jsonData = "[{'name':'John', 'grade':[{'course':'English','score':100},{'course':'Math','score':78}]},{'name':'Tom', 'grade':[{'course':'English','score':86},{'course':'Math','score':90}]}]";
		List<Person> persons = GsonUtil.fromJsonList(jsonData, Person.class);
		System.out.println(persons.get(0).getGrade());
	}

	public static void main(String[] args) {
		testStudent();
		testPersons();
	}

}

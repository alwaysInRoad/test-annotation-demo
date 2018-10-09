package com.example.testannotationdemo;

import com.example.testannotationdemo.utils.ClassUtils;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAnnotationDemoApplicationTests {
	//设置要扫描的包
	String packageName = "com.example.testannotationdemo.controller";
	//调用ClassUtils类的getClassName方法，得到类名字
	Set<String> classNames = ClassUtils.getClassName(packageName, false);

	/**
	 * 反射获取类上@RequestMapping注解里的value值
	 */
	@Test
	public void testGetRequestMapping() {
		System.out.println("获取@RequestMapping注解里的value值");
		//迭代得到的类名
		for (String string : classNames){
			//新建Class
			Class cl1;
			//classPath： 类上的RequestMapping注解的value值
			String classPath = null;
			try {
				//通过反射获取到类
				cl1 = Class.forName(string);
				//获取RequestMapping注解
				RequestMapping anno = (RequestMapping) cl1.getAnnotation(RequestMapping.class);
				if (anno != null) {
					//获取类注解的value值
					String[] value = anno.value();
					//将字符串数组转成字符串
					StringBuilder sb = new StringBuilder();
					for (String ele : value) {
						sb.append(ele);
					}
					classPath = sb.toString();
				}
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(classPath);
		}
	}

	/**
	 * 反射获取方法上的 @GetMapping @PutMapping @PostMapping @DeleteMapping 注解的value值
	 */
	@Test
	public void testGetMethod() {
		System.out.println("获取请求方法注解里的value值");
		for (String string : classNames){
			//新建Class
			Class cl1;
			//requestMothedPath： 方法上的注解的请求的value值
			String requestMothedPath = null;
			try {
				//通过反射获取到类
				cl1 = Class.forName(string);
				//获取类中所有的方法
				Method[] methods = cl1.getDeclaredMethods();
				for (Method method : methods) {
					String[] value = null;
					//请求方式
					String detailedMethodName = null;
					GetMapping getRequestMothed = (GetMapping) method.getAnnotation(GetMapping.class);
					PutMapping putRequestMothed = (PutMapping) method.getAnnotation(PutMapping.class);
					PostMapping postRequestMothed = (PostMapping) method.getAnnotation(PostMapping.class);
					DeleteMapping deleteRequestMothed = (DeleteMapping) method.getAnnotation(DeleteMapping.class);
					if (deleteRequestMothed != null) {
						//获取类注解的value值
						value = deleteRequestMothed.value();
						//获取详细的请求方式
						detailedMethodName = "delete";
					}
					if (putRequestMothed != null) {
						//获取类注解的value值
						value = putRequestMothed.value();
						detailedMethodName = "put";
					}
					if (getRequestMothed != null) {
						//获取类注解的value值
						value = getRequestMothed.value();
						detailedMethodName = "get";
					}
					if (postRequestMothed != null) {
						//获取类注解的value值
					 	 value = postRequestMothed.value();
						detailedMethodName = "post";
					}
					//将字符串数组转成字符串
					StringBuilder sb = new StringBuilder();
					for (String ele : value) {
						sb.append(ele);
					}
					requestMothedPath = sb.toString();
					System.out.println(detailedMethodName+":"+requestMothedPath);
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 反射获取swagger的 @ApiOperation 注解 value值
	 */
	@Test
	public void testGetApiOperation() {
		System.out.println("获取swagger的 @ApiOperation 注解 value值");
		//apiOperationValue： 方法注释值
		String apiOperationValue = null;
		//迭代得到的类名
		for (String string : classNames){
			//新建Class
			Class cl1;
			try {
				//通过反射获取到类
				cl1 = Class.forName(string);
				//获取类中所有的方法
				Method[] methods = cl1.getDeclaredMethods();
				for (Method method : methods) {
					ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
					if (apiOperation != null) {
						//获取方法上@ApiOperation注解的value值
						apiOperationValue = apiOperation.value();
						System.out.println(apiOperationValue);
					}
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过反射获取方法参数注解的值
	 */
	@Test
	public void testGetPrameterAnnotations() {
		System.out.println("通过反射获取方法参数注解的值");
		//迭代得到的类名
		for (String string : classNames){
			//新建Class
			Class cl1;
			try {
				//通过反射获取到类
				cl1 = Class.forName(string);
				//获取类中所有的方法
				Method[] methods = cl1.getDeclaredMethods();
				for (Method method : methods) {
					//获取方法参数注解
					Annotation[][] parameterAnnotations = method.getParameterAnnotations();
					for (Annotation[] annotations : parameterAnnotations) {
						for (Annotation annotation : annotations) {
							//获取简单类名
							String name = annotation.annotationType().getSimpleName();
							System.out.println(name);
						}
					}
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}

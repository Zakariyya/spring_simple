package com.simple.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simple.annotion.AutoWrited;
import com.simple.annotion.Controller;
import com.simple.annotion.RequestMapping;
import com.simple.annotion.Service;
import com.simple.controller.SimpleController;


/**
 * Servlet implementation class DispatchServlet
 */
@WebServlet("/")
public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> classNames = new ArrayList<>();
	
	private Map<String,Object> instanceMap = new HashMap<String,Object>();
	
	private Map<String,Method> methodMap = new HashMap<String,Method>();
	

	private Field[] Field[];
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
	/**
	 * 1.找到bean
	 * 2.生成并注册bean
	 * 3.注入bean
	 */
    	//实现文件加载过程
    	scanBase("com.simple");
    	System.out.println("scanBase  finsh");
    	
    	try {
    		//生成注册bean
			fileterAndInstance();
			System.out.println("fileterAndInstance  finsh");
	
			//注入bean
			springDi();
			System.out.println("springDi  finsh");
			
			springMVC();
			System.out.println("springMVC  finsh");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }

    private void springMVC() {
    	if(instanceMap.size() == 0){
    		return;
    	}
    	for(Map.Entry<String, Object> entry: instanceMap.entrySet()){
    		if(entry.getValue().getClass().isAnnotationPresent(Controller.class)){
    			String ctrUrl = ((Controller)entry.getValue().getClass().getAnnotation(Controller.class)).value();
    			Method[] methods = entry.getValue().getClass().getMethods();
    			for(Method method: methods){
    				if(method.isAnnotationPresent(RequestMapping.class)){
    					String reqUrl =  ((RequestMapping)method.getAnnotation(RequestMapping.class)).value();
    					String dispatchUrl ="/"+ ctrUrl+"/"+reqUrl;
    					methodMap.put(dispatchUrl, method);
    				}
    			}
    		}
    		else{
    			continue;
    		}
    	}
    }
    
    
    private void springDi() throws IllegalArgumentException, IllegalAccessException{
    	if(instanceMap.size() == 0){
    		return;
    	}
    	for(Map.Entry<String, Object> entry: instanceMap.entrySet()){
    		Field[] fields = entry.getValue().getClass().getDeclaredFields();
    		for(Field field:fields){
    			if(field.isAnnotationPresent(AutoWrited.class)){
    				String key = ((AutoWrited)field.getAnnotation(AutoWrited.class)).value();
    				field.setAccessible(true);
    				field.set(entry.getValue(), instanceMap.get(key));
    			}
    		}
    	}
    }
    
    
    
    /**
     * 生成并注册bean
     * @throws Exception
     */
    //把className循环，生成后加入
    private void fileterAndInstance() throws Exception{
    	if(classNames.size() == 0){
    		return;
    	}
    	for(String className:classNames){
    		Class clazz = Class.forName(className.replace(".class", ""));
    		if(clazz.isAnnotationPresent(Controller.class)){
    			/*获取到一个bean*/
    			Object instance = clazz.newInstance();
    		 	/*获取到注解的value*/
    			String key = ((Controller)clazz.getAnnotation(Controller.class)).value();
    			/*将bean交付给IOC*/
    			instanceMap.put(key, instance);
    			
    			
    		}else if(clazz.isAnnotationPresent(Service.class)){
    			/*获取到一个bean*/
    			Object instance = clazz.newInstance();
    		 	/*获取到注解的value*/
    			String key = ((Service)clazz.getAnnotation(Service.class)).value();
    			/*将bean交付给IOC*/
    			instanceMap.put(key, instance);
    		}else{
    			continue;
    		}
    	}
    }
    
    private void scanBase(String basePackages){
    	URL  url = this.getClass().getClassLoader().getResource("/"+replacePath(basePackages));
    	String path = url.getFile();
    	File file = new File(path);
    	String[] strFiles = file.list();
    	for(String strFile: strFiles){
    		File eachFile = new File(path+strFile);
    		if(eachFile.isDirectory()){
    			scanBase(basePackages+"."+eachFile.getName());
    		}else{
    			System.out.println("class Names ="+ eachFile.getName());
    			classNames.add(basePackages+"."+eachFile.getName());
    		}
    	}
    }
    
    private String replacePath(String path){
    	return path.replaceAll("\\.", "/");
    }
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet   run ");
		
		/*  springioc/simple/get  */
		String uri = request.getRequestURI();
		System.out.println("uri======"+uri);
		
		String projectName = request.getContextPath();// springioc
		String path = uri.replaceAll(projectName, ""); // simple/get
		Method method = methodMap.get(path);
	
		String className = uri.split("/")[2];
		SimpleController simpleController = (SimpleController)instanceMap.get(className); 
	
		try {
			method.invoke(simpleController, new Object[] {request,response,null});
			
						
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("doGet   over ");
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

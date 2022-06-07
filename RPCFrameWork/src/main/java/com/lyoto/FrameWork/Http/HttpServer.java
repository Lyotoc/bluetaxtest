package com.lyoto.FrameWork.Http;

import java.util.LinkedList;

import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 15:20
 @Version
 **/
public class HttpServer {
	public void start(String hostName,Integer port){
		Tomcat tomcat = new Tomcat();

		Server server = tomcat.getServer();
		Service service = server.findService("Tomcat");

		//设置连接--默认使用HTTP/1.1
		Connector connector  = new Connector();
		connector.setPort(port);
		StandardEngine engine = new StandardEngine();
		engine.setDefaultHost(hostName);

		Host host = new StandardHost();
		host.setName(hostName);

		final String defaultContextPath = "";
		StandardContext context = new StandardContext();
		context.setPath(defaultContextPath);
		context.addLifecycleListener(new Tomcat.FixContextListener());

	//	向上装配
		host.addChild(context);
		engine.addChild(host);

		service.setContainer(engine);
		service.addConnector(connector);

		tomcat.addServlet(defaultContextPath,DispatcherServlet.class.getSimpleName(),new DispatcherServlet());
		context.addServletMappingDecoded("/*",DispatcherServlet.class.getSimpleName());
		try {
			tomcat.start();
			tomcat.getServer().await();
		}
		catch (LifecycleException e) {
			throw new RuntimeException(e);
		}

	}
}

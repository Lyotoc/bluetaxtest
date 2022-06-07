package com.lyoto.FrameWork.Http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;


/**
 @author Lyoto
 @Description
 @Date 2022-06-02 15:51
 @Version
 **/
public class DispatcherServlet extends HttpServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		new HttpServletHandler().handler(req,res);
	}
}

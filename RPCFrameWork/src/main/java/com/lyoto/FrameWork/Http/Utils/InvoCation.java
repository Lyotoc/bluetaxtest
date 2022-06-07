package com.lyoto.FrameWork.Http.Utils;

import java.io.Serializable;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 16:19
 @Version
 **/

public class InvoCation implements Serializable {
	private String interfaceName;
	private String methodName;
	private Class[] paramTypes;
	private Object[] params;

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public static Builder builder() {
		return new Builder();
	}
	public InvoCation(){}
	private InvoCation(Builder builder){
		this.interfaceName = builder.interfaceName;
		this.methodName = builder.methodName;
		this.params = builder.params;
		this.paramTypes = builder.paramTypes;
	}

	public static class Builder {
		String interfaceName;
		String methodName;
		Class[] paramTypes;
		Object[] params;

		public Builder interfaceName(String interfaceName) {
			this.interfaceName = interfaceName;
			return this;
		}

		public Builder methodName(String methodName) {
			this.methodName = methodName;
			return this;
		}

		public Builder paramTypes(Class[] paramTypes){
			this.paramTypes = paramTypes;
			return this;
		}
		public Builder params(Object[] params){
			this.params = params;
			return this;
		}
		public InvoCation build(){
			return new InvoCation(this);
		}
	}

}

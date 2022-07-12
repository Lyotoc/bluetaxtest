package com.lyoto.FrameWork.Http.Utils;

/**
 @author Lyoto
 @Description
 @Date 2022-06-06 15:36
 @Version
 **/
public class URL {
	private String hostName;

	private String port;

	public String getHostName() {
		return hostName;
	}

	public String getPort() {
		return port;
	}

	public static Builder builder() {
		return new Builder(new URL());
	}


	public static class Builder {
		private URL url;

		Builder(URL url) {
			this.url = url;
		}

		public Builder hostName(String hostName) {
			url.hostName = hostName;
			return this;
		}

		public Builder port(String port) {
			url.port = port;
			return this;
		}

		public URL build() {
			return url;
		}
	}

}

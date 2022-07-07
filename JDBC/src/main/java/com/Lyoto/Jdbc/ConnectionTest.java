package com.Lyoto.Jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import org.junit.Test;

/**
 @author Lyoto
 @Description
 @Date 2022-07-07 15:16
 @Version
 **/
public class ConnectionTest {
	@Test
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, SQLException {
		InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(resourceAsStream);
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String url = properties.getProperty("url");
		String driverClass = properties.getProperty("driverClass");

		Class.forName(driverClass);
		Connection connection = DriverManager.getConnection(url, user, password);
		if (Objects.nonNull(connection)){
			System.out.println("yes");
		}else{
			System.out.println("fail");
		}

		PreparedStatement preparedStatement = connection.prepareStatement("");
		preparedStatement.setObject(1,"");
		preparedStatement.execute();
	}

}

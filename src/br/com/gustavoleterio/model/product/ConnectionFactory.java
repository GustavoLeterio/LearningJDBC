package br.com.gustavoleterio.model.product;
import java.sql.Connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//This is a Design Pattern called FactoryMethods, it encapsulate a method that makes an object.
public class ConnectionFactory {
	private String url;
	private String user;
	private String password;
	public DataSource dataSource;
	
	public ConnectionFactory() {
		this.url = "jdbc:mysql://localhost/loja?useTimezone=true&serverTimezone=UTC";
		this.user = "root";
		this.password = "Bancodedadosmysql123!";
		cpdsHandler();
	}

	public ConnectionFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		cpdsHandler();
	}

	public void cpdsHandler() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(url);
		cpds.setUser(user);
		cpds.setPassword(password);
		cpds.setMaxPoolSize(15);
		
		this.dataSource = cpds;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}

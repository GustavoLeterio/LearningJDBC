package br.com.gustavoleterio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.gustavoleterio.model.category.Category;

public class CategoryDAO {

	Connection connection;

	public CategoryDAO(Connection connection) throws SQLException {
		this.connection = connection;
		connection.setAutoCommit(false);
	}

	public Map<String, Category> list() throws SQLException {
		try (PreparedStatement qrStatement = connection.prepareStatement("SELECT * FROM CATEGORY")) {
			qrStatement.execute();
			connection.commit();
			ResultSet results = qrStatement.getResultSet();

			Map<String, Category> categories = new HashMap<>();

			// Populating HashMap
			while (results.next()) {
				String id = results.getString("ID");
				String name = results.getString("NAME");

				categories.put(id, new Category(id, name));
			}
			return categories;
		}
	}

}

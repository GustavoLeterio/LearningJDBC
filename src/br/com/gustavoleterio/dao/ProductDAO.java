package br.com.gustavoleterio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import br.com.gustavoleterio.model.category.Category;
import br.com.gustavoleterio.model.product.Product;

public class ProductDAO {
	Connection connection;

	public ProductDAO(Connection connection) throws SQLException {
		this.connection = connection;
		connection.setAutoCommit(false);
	}

	public HashMap<String, Product> list() throws SQLException {
		try (PreparedStatement qrStatement = connection.prepareStatement(
				"SELECT P.ID AS PRODID,P.NAME AS PRODNAME, P.DESCRIPTION ,P.CATEGORYID AS CATID,C.NAME "
						+ "AS CATNAME FROM PRODUCT AS P LEFT JOIN CATEGORY AS C ON P.categoryid = C.id ORDER BY PRODID")) {
			qrStatement.execute();
			connection.commit();
			ResultSet results = qrStatement.getResultSet();

			HashMap<String, Product> products = new HashMap<>();

			// Populating HashMap
			while (results.next()) {
				String prodId = results.getString("PRODID");
				String prodName = results.getString("PRODNAME");
				String prodDescription = results.getString("DESCRIPTION");

				String catId = results.getString("CATID");
				String catName = results.getString("CATNAME");

				products.put(prodId, new Product(prodId, prodName, prodDescription, new Category(catId, catName)));
			}

			return products;
		}
	}

	public Product find(String category) throws SQLException {
		try (PreparedStatement qrStatement = connection.prepareStatement(
				"SELECT P.*,C.name FROM PRODUCT AS P LEFT JOIN CATEGORY AS C WHERE P.id = C.categoryid")) {
			qrStatement.setString(1, category);
			qrStatement.execute();
			connection.commit();
			ResultSet results = qrStatement.getResultSet();

			// Populating HashMap
			String prodId = null;
			String prodName = null;
			String prodDescription = null;
			String catId = null;
			String catName = null;
			while (results.next()) {
				prodId = results.getString("PRODID");
				prodName = results.getString("PRODNAME");
				prodDescription = results.getString("DESCRIPTION");
				catId = results.getString("CATID");
				catName = results.getString("CATNAME");
			}
			return new Product(prodId, prodName, prodDescription, new Category(catId, catName));
		}
	}

	public void insert(String name, String description) throws SQLException {
		try (PreparedStatement qrStatement = connection.prepareStatement(
				"INSERT INTO PRODUCT (NAME,DESCRIPTION) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
			qrStatement.setString(1, name);
			qrStatement.setString(2, description);
			qrStatement.execute();

			try (ResultSet results = qrStatement.getGeneratedKeys()) {
				while (results.next()) {
					System.out.println("Item criado com ID " + results.getString(1));
				}
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Rollback");
			connection.rollback();
			System.out.println("Any item commited, please try again later. :*(");
		}
	}

	public void delete(String id) throws SQLException {

		PreparedStatement qrStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID > ?");
		qrStatement.setString(1, id);
		qrStatement.execute();

		connection.commit();
		System.out.println("Modified Lines: " + qrStatement.getUpdateCount());
	}

}

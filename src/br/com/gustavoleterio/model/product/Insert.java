package br.com.gustavoleterio.model.product;

import java.sql.Connection;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import br.com.gustavoleterio.dao.ProductDAO;

public class Insert {
	public static void main(String[] args) throws SQLException {
		// Start Connection
		// Connection is auto closable, so it will close after the try/catch.
		try (Connection connection = new ConnectionFactory().getConnection()) {
			// Removes the JDBC capability of commit the query,
			// that will be done manually by the coder
			ProductDAO productDAO = new ProductDAO(connection);
			productDAO.insert(JOptionPane.showInputDialog("Name:"), JOptionPane.showInputDialog("Description:"));
		}
	}
}

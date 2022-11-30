package br.com.gustavoleterio.model.product;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.gustavoleterio.dao.ProductDAO;

public class Delete {
	public static void main(String[] args) throws SQLException {
		// Start Connection
		try (Connection connection = new ConnectionFactory().getConnection()) {
			ProductDAO productDAO = new ProductDAO(connection);
			productDAO.delete(JOptionPane.showInputDialog("ID:"));
		}
	}
}

package br.com.gustavoleterio.model.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import br.com.gustavoleterio.dao.ProductDAO;

public class List {
	public static void main(String[] args) throws SQLException {
		// Start Connection
		Connection connection = new ConnectionFactory().getConnection();

		ProductDAO productDAO = new ProductDAO(connection);
		Map<String, Product> products = productDAO.list();
		
		// Iterar HashSet -> Entrada<Identificador, ValorDoConjunto> como "set" :
		// produtos.pegarConjunto
		for (Entry<String, Product> set : products.entrySet()) {
			Product product = set.getValue();
			System.out.println("ID(" + set.getKey() + ") = " + product);
		}

		connection.close();
	}
}

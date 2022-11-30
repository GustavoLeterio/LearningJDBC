package br.com.gustavoleterio.model.category;

import br.com.gustavoleterio.dao.CategoryDAO;
import br.com.gustavoleterio.model.product.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

public class List {
	public static void main(String[] args) throws SQLException {
		try (Connection connection = new ConnectionFactory().getConnection()) {
			CategoryDAO categoryDao = new CategoryDAO(connection);
			
			Map<String, Category> categories = categoryDao.list();
			
			// Iterar HashSet -> Entrada<Identificador, ValorDoConjunto> como "set" :
			// produtos.pegarConjunto
			for (Entry<String, Category> set : categories.entrySet()) {
				Category product = set.getValue();
				System.out.println("ID(" + set.getKey() + ") = " + product);
			}
		}
	}
}

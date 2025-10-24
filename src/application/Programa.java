package application;

import java.util.Date;

import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		
		Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		VendedorDAO vendedorDAO = DAOFactory.criarVendedorDAO();
		
		System.out.println(obj);
		System.out.println(vendedor);
		
		
		
	}

}

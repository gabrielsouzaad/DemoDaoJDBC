package application;

import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		
		VendedorDAO vendedorDAO = DAOFactory.criarVendedorDAO();
		
		Vendedor vendedor = vendedorDAO.encontrarPorId(3);
		
		System.out.println(vendedor);
		
		
		
	}

}

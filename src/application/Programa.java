package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		
		VendedorDAO vendedorDAO = DAOFactory.criarVendedorDAO();
		
		System.out.println("--- Teste número 1: encontrarPorId ---");
		
		Vendedor vendedor = vendedorDAO.encontrarPorId(3);
		
		System.out.println(vendedor);
		
		System.out.println("\n--- Teste número 2: encontrarPorDepartamento ---");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> list = vendedorDAO.encontrarPorDepartamento(departamento);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
	
	
	}

}

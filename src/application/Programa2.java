package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DAOFactory;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;

public class Programa2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		DepartamentoDAO departamentoDAO = DAOFactory.criarDepartamentoDAO();

		System.out.println("--- Teste número 1: encontrarPorId ---");
		Departamento dep = departamentoDAO.encontrarPorId(1);
		System.out.println(dep);
		
		System.out.println("\n--- Teste número 2: encontrarTodos ---");
		List<Departamento> list = departamentoDAO.encontrarTodos();
		for (Departamento d : list) {
			System.out.println(d);
		}

		System.out.println("\n--- Teste número 3: departamento inserir ---");
		Departamento newDepartment = new Departamento(null, "Music");
		departamentoDAO.inserir(newDepartment);
		System.out.println("Inserido! Novo Id: " + newDepartment.getId());

		System.out.println("\n--- Teste número 4: departamento atualizar ---");
		Departamento dep2 = departamentoDAO.encontrarPorId(1);
		dep2.setNome("Food");
		departamentoDAO.atualizar(dep2);
		System.out.println("Atualização completa!!!");
		
		System.out.println("\n--- Teste número 5: departamento deletado ---");
		System.out.print("Entre com o id para ser deletado: ");
		int id = sc.nextInt();
		departamentoDAO.deletarDoId(id);
		System.out.println("Deletado com sucesso!!!");

		sc.close();
	}
}
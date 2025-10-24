package model.dao;

import java.util.List;

import model.entities.Vendedor;

public interface VendedorDAO {
	
	void inserir(Vendedor obj);
	void atualizar(Vendedor obj);
	void deletarDoId(Integer id);
	Vendedor encontrarPorId(Integer id);
	List<Vendedor> encontrarTodos();

}

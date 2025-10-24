package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDAO {
	void inserir(Departamento obj);
	void atualizar(Departamento obj);
	void deletarDoId(Integer id);
	Departamento encontrarPorId(Integer id);
	List<Departamento> encontrarTodos();
}

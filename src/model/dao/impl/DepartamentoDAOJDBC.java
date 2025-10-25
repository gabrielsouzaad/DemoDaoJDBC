package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;

public class DepartamentoDAOJDBC implements DepartamentoDAO {
	
	private Connection conn;
	
	public DepartamentoDAOJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public void inserir(Departamento obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Id, Name ) "
					+ "VALUES "
					+ "(?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId());
			st.setString(2, obj.getNome());
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Nenhuma linha foi afetada!!!");
			}
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}
		
		
		
	}

	@Override
	public void atualizar(Departamento obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarDoId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento encontrarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> encontrarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}

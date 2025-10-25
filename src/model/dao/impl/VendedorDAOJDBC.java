package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDAOJDBC implements VendedorDAO {

	
	private Connection conn;
	
	public VendedorDAOJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void inserir(Vendedor obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
					
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setDouble(4, obj.getSalariobase());
			st.setInt(5, obj.getDepartamento().getId());
			
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
	public void atualizar(Vendedor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE id = ? ");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setDouble(4, obj.getSalariobase());
			st.setInt(5, obj.getDepartamento().getId());
			st.setInt(6, obj.getId());
			
			
			st.executeUpdate();
			
			
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}
	}

	@Override
	public void deletarDoId(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}
 
	@Override
	public Vendedor encontrarPorId(Integer id) {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement(
	            "SELECT seller.*, department.Name AS DepName "
	            + "FROM seller "
	            + "INNER JOIN department "
	            + "ON seller.DepartmentId = department.Id "
	            + "WHERE seller.Id = ?"
	        );
	        
	        st.setInt(1, id);
	        rs = st.executeQuery();
	        
	        if (rs.next()) {
	            Departamento dep = instantieteDepartamento(rs);
	            
	            Vendedor obj = instantieteVendedor(rs, dep);	            
	            return obj;
	        }
	        return null;
	        
	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	        DB.closeResultSet(rs);
	    }
	}


	private Vendedor instantieteVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setSalariobase(rs.getDouble("BaseSalary"));
        obj.setDataDeNascimento(rs.getDate("BirthDate"));
        obj.setDepartamento(dep);
        
        return obj;

	}

	private Departamento instantieteDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
		return dep;
	}
	
	

	@Override
	public List<Vendedor> encontrarTodos() {
		PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement(
	            "SELECT seller.*,department.Name AS DepName "
	            + "From seller INNER JOIN department "
	            + "ON seller.DepartmentId = department.Id "
	            + "ORDER BY Name "
	        );
	        
	    
	        rs = st.executeQuery();
	        
	        List<Vendedor> list = new ArrayList<>();
	        Map<Integer, Departamento> map = new HashMap<>();
	        
	        
	        while (rs.next()) {
	        	
	        	Departamento dep = map.get(rs.getInt("DepartmentId"));
	            Departamento dep1 = instantieteDepartamento(rs);
	            
	            if (dep1 == null) {
	            	dep1 = instantieteDepartamento(rs);
	            	map.put(rs.getInt("DepartmentId"), dep1);
	            }
	            
	            Vendedor obj = instantieteVendedor(rs, dep1);	            
	            list.add(obj);
	        }
	        return list;
	        
	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	        DB.closeResultSet(rs);
	    }
	}

	@Override
	public List<Vendedor> encontrarPorDepartamento(Departamento departamento) {
		PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement(
	            "SELECT seller.*,department.Name AS DepName "
	            + "From seller INNER JOIN department "
	            + "ON seller.DepartmentId = department.Id "
	            + "WHERE DepartmentId = ? "
	            + "ORDER BY Name "
	        );
	        
	        st.setInt(1, departamento.getId());
	        rs = st.executeQuery();
	        
	        List<Vendedor> list = new ArrayList<>();
	        Map<Integer, Departamento> map = new HashMap<>();
	        
	        
	        while (rs.next()) {
	        	
	        	Departamento dep = map.get(rs.getInt("DepartmentId"));
	            Departamento dep1 = instantieteDepartamento(rs);
	            
	            if (dep1 == null) {
	            	dep1 = instantieteDepartamento(rs);
	            	map.put(rs.getInt("DepartmentId"), dep1);
	            }
	            
	            Vendedor obj = instantieteVendedor(rs, dep1);	            
	            list.add(obj);
	        }
	        return list;
	        
	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	        DB.closeResultSet(rs);
	    }
	}

}

package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarDoId(Integer id) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}

package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
			
	@Override
	public void insert(Vendedor obj) {

		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES" 
					+ " ( ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, "Maryah Oliver");
			pst.setString(2, "maryaholiver38@gmail.com");
			pst.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			pst.setDouble(4, 3000.0);
			pst.setInt(5, obj.getDepartamento().getId());
			
			int nl = pst.executeUpdate();
			if( nl > 0 ) {
				rs = pst.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}else {
					throw new DbException("Falha, Nenhum registro foi inserido.");
				}
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
	}

	@Override
	public void update(Vendedor obj) {

		PreparedStatement pst = null;
		int rs = 0;
		
		try {
			pst = conn.prepareStatement("UPDATE seller SET " 
					+ "Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "Where Id = ?");
			
			pst.setString(1, obj.getName());
			pst.setString(2, obj.getEmail());
			pst.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			pst.setDouble(4, obj.getSalarioBase());
			pst.setInt(5, obj.getDepartamento().getId());
			pst.setInt(6, obj.getId());
			
			rs = pst.executeUpdate();			
			if( rs > 0) {
					int id = obj.getId();
					System.out.println("Registro inserido com sucesso." + id);
			}else {
				throw new DbException("Registro não encontrado para alteração.");				
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("delete from seller where id=?");
			
			pst.setInt(1, id);
			
			int rs = pst.executeUpdate();			
			if (rs > 0) {
				System.out.println("Registro removido com sucesso: " + id);																
			}else {
				throw new DbException("Não foi possivel remover registro agora! Tente outra vez.");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(pst);
		}
		
	}

	@Override
	public Vendedor findById(Integer id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " 
					+ "from seller inner join department "
					+ " on seller.DepartmentId = department.Id "
					+ " where seller.Id = ?"
					);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				Departamento dep = new Departamento(rs.getInt("DepartmentId"), rs.getString("DepName"));
				Vendedor ved = new Vendedor(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), dep) ;
				return ved;
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);			
		}
		return null;
	}

	@Override
	public List<Vendedor> findAll() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Vendedor> vedlist = new ArrayList<>();
		try {
			pst = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " 
					+ "from seller inner join department "
					+ "on seller.DepartmentId = department.Id "
					+ "order by Name"
					);			
			
			rs = pst.executeQuery();			
			while(rs.next()) {
				Vendedor ved = new Vendedor(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"),  new Departamento(rs.getInt("DepartmentID"), rs.getString("DepName"))) ;
				vedlist.add(ved);				
			}
			return vedlist;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);			
		}		

	}

	@Override
	public List<Vendedor> findByDepartment(Departamento departamento) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Vendedor> vedlist = new ArrayList<>();
		try {
			pst = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " 
					+ "from seller inner join department "
					+ "on seller.DepartmentId = department.Id "
					+ "where department.Id = ? "
					+ "order by Name"
					);
			pst.setInt(1, departamento.getId());
			
			rs = pst.executeQuery();			
			while(rs.next()) {
				Vendedor ved = new Vendedor(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), departamento) ;
				vedlist.add(ved);				
			}
			return vedlist;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);			
		}		
	}

}

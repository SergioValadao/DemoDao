package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {
	
	private Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Departamento obj) {
		
		try {
			pst = conn.prepareStatement("insert into department (Name) Values( ? )");
			pst.setString(1, obj.getName());
			
			int res = pst.executeUpdate();
			if(res > 0) {
				System.out.println("Registro " + obj.getName() + " inserido com sucesso.");
			}else {
				System.out.println("Não foi possivel inserir registro! Verifique.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Departamento obj) {
		
		try {
			pst = conn.prepareStatement("update department set Name = ? where Id = ?");
			
			pst.setString(1, obj.getName());
			pst.setInt(2, obj.getId());
			
			int res = pst.executeUpdate();
			if(res > 0) {
				System.out.println("Registro numero " + obj.getId() + " alterado com sucesso.");
			}else {
				System.out.println("Registro não encontrado! Verifique.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(pst);
		}

		
	}

	@Override
	public void deleteById(Integer id) {
				
		try {
			pst = conn.prepareStatement("delete from department where Id = ?");
			
			pst.setInt(1, id);
			
			int res = pst.executeUpdate();			
			if(res > 0) {
				System.out.println("Registro " +  id + " excluido com sucesso.");
			}else {
				System.out.println("Registro " +  id + " não pode ser excluido! Tente de novo.");
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			DB.closeStatement(pst);
		}
		
	}

	@Override
	public Departamento findById(Integer id) {
		
		Departamento dep = null;
		
		try{
			pst = conn.prepareStatement("select * from department where id = ?");
			
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				dep = new Departamento(rs.getInt("Id"), rs.getString("Name"));				
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());  
		}
		return dep;
			
	}

	@Override
	public List<Departamento> findAll() {
		
		List<Departamento> depLista = new ArrayList<>();		
		try{
			pst = conn.prepareStatement("select * from department order by Id");
			
			rs = pst.executeQuery();
			while(rs.next()){
				depLista.add(new Departamento(rs.getInt("Id"), rs.getString("Name")));
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());  
		}
		return depLista;

	}

}

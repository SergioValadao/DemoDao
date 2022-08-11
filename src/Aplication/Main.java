package Aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.dao.VendedorDao;
import model.entities.Departamento;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		Departamento obj = new Departamento(4, null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TimeZone.setDefault(TimeZone.getTimeZone("US"));
		
		VendedorDao vendedorDao = DaoFactory.createVendedoDao();
		DepartamentoDao departmentDao = DaoFactory.createDepartamentoDao();
		List<Departamento> deplista = new ArrayList<>();
		
		//Fução procura por um vendedor
		//Vendedor vendedor = vendedorDao.findById(2);
		//System.out.println(vendedor);
		
		/*
		 * função lista um ou todos os vendedores
		//List<Vendedor> lista = vendedorDao.findByDepartment(obj);
		
		List<Vendedor> lista = vendedorDao.findAll();		
		
		for(Vendedor l: lista) {
			System.out.println(l);
		}
		*/
		/*
		função insere registro na base de dados
		Vendedor vend = new Vendedor(null,"'Maryah Oliver'","'maryaholiver38@gmail.com'", sdf.parse("11/04/1981"), 3000.0 , obj);
		vendedorDao.insert(vend);
		System.out.println("Numero do registro inserido: " + vend.getId());
		*/
		/*
		Vendedor altvend = new Vendedor(23,"Maria Jose de Oliveira","mj_legal@hotmail.com", sdf.parse("11/04/1981"), 4500.0 , obj);
		vendedorDao.update(altvend);
		System.out.println("Numero do alterado: " + altvend.getId());
		*/
		
		//excluir registro
		//vendedorDao.deleteById(9);
		
		//Procurar um Dartamento
		
		//Departamento depart = departmentDao.findById(5);		//book
		//System.out.println(depart.toString());
		
		/*
		deplista = departmentDao.findAll();
		for(Departamento a: deplista) {
			System.out.println(a.toString());
		}
		*/
		//Excluir um registro
		//departmentDao.deleteById(7);
		
		//Alterar um registro
		obj.setId(4);
		obj.setName("Desktops");
		//departmentDao.update(obj);
		
		departmentDao.insert(obj);
	}

}

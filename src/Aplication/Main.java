package Aplication;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Main {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Books");
		
		VendedorDao vendedorDao = DaoFactory.createVendedoDao();
		
		Vendedor vendedor = vendedorDao.findById(2);
		
		System.out.println(vendedor);

	}

}

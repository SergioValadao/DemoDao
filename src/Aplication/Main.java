package Aplication;

import java.util.Date;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Main {

	public static void main(String[] args) {
		Departamento obj = new Departamento(1, "Books");
		
		Vendedor vendedor = new Vendedor(21, "bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		System.out.println(vendedor);

	}

}

package com.example.travelAgencyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelAgencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApiApplication.class, args);
	}


	//definir los metodos de PersonController, PersonService, repository etc...
	//levantar de nuevo la base de datos y asegurarse de que se han creado bien las tablas con las relaciones, y despues probar a intruducir datos de Persona, Ticket y Vuelo.
	//DONE    Comprobar notas en clases Reservation y Room.
	//Para futuros displays de datos por JSON para front, usar DTOs.
	//Cambiar el modelo de entidades, eliminar la clase Person, simplificar y añadir los datos como strings a cada clase y a tomar por culo.
	//DONE    ANTES DE BORRAR, Investigar si las variantes de findAll pueden tener alguna forma de prevenir bucles infinitos al imprimir JSONs.
	//DONE    Los DTOs deberian funcionar(?), comprobar de nuevo la transferencia de datos del objeto al DTO. Comprobar apuntes.
	//@ResponseBody?????????
	//DONE     revisar todas las clases y asegurarse de no dejar ni un solo tipo primitivo, luego borrar y levantar de nuevo la base de datos.
	//TODO averiguar por qué en la búsqueda con filtros los campos "priceBusiness" y "priceEconomy" se quedan null en el JSON, probablemente falta algo en el metodo convertidor en la clase utilities.
}

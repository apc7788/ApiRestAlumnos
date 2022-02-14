package com.testindividual.service;

import java.util.List;

import com.testindividual.entity.Alumnos;

public interface AlumnosService {

	//GUARDAR EN ARRAYLIST EL ALUMNO
	public List<Alumnos> findAll();
	
	//BUSCAR ID
	public Alumnos findById(Long id);
	
	//BORRAR ALUMNO
	public void delete (Long id);
		
	//GUARDAR ALUMNO PARA ACTUALIZAR
	public Alumnos save(Alumnos alumno);
	
}

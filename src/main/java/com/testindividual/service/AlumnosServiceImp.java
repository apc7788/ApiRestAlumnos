package com.testindividual.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testindividual.dao.AlumnosDao;
import com.testindividual.entity.Alumnos;

@Service
public class AlumnosServiceImp implements AlumnosService {

	private AlumnosDao alumnosDao;
	
	//LEER ALUMNOS
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumnos> findAll() {
		return (List<Alumnos>) alumnosDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Alumnos findById(Long id) {
		return alumnosDao.findById(id).orElse(null);
	}
	
	// BORRAR ALUMNOS

	@Override
	public void delete(Long id) {
		alumnosDao.deleteById(id);
		
	}
	
	//MODIFICAR ALUMNOS

	@Override
	public Alumnos save(Alumnos alumno) {
		return alumnosDao.save(alumno);
	}

}

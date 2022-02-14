package com.testindividual.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testindividual.entity.Alumnos;

@Repository
public interface AlumnosDao extends CrudRepository<Alumnos, Long> {

}

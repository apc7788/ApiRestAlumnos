package com.testindividual.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testindividual.entity.Alumnos;
import com.testindividual.service.AlumnosService;

@RestController
@RequestMapping("/api")
public class AlumnosControlles {

	@Autowired
	private AlumnosService servicio;
	
	@GetMapping("/alumnos")
	public List<Alumnos> alumnos(){
		return servicio.findAll();
	}
	
	// LEER ALUMNOS
	
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<?> alumnosShow(@PathVariable Long id){
		Alumnos alumno = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			alumno = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (alumno == null) {
			response.put("Mensaje, ","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Alumnos>(alumno, HttpStatus.OK);
		
	}
	
	// BORRAR ALUMNOS
	
	@DeleteMapping("/alumnos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteAlumnosMostrado(@PathVariable  Long id) {
		Alumnos alumnoBorrado= servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (alumnoBorrado == null) {
				response.put("Mensaje, ","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
			
		}
			catch (DataAccessException e) {
			response.put("Mensaje", "Error al borrar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		response.put("Mensaje","El cliente ha sido borrado :D");
		response.put("alumno", alumnoBorrado);
		 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// ACTUALIZAR ALUMNOS
	
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<?> updateAlumno(@RequestBody Alumnos alumno, @PathVariable Long id) {
		
		Alumnos alumnoUpdated = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			alumnoUpdated.setNombre(alumno.getNombre());
			alumnoUpdated.setApellido(alumno.getApellido());
			alumnoUpdated.setCp(alumno.getCp());
			alumnoUpdated.setTelefono(alumno.getTelefono());
			alumnoUpdated.setEmail(alumno.getEmail());
			alumnoUpdated.setDireccion(alumno.getDireccion());
			alumnoUpdated.setDni(alumno.getDni());
			
			servicio.save(alumnoUpdated);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El cliente ha sido actualizado :D");
		response.put("cliente", alumnoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	// INSERTAR ALUMNOS
	
	@PostMapping("/alumnos")
	public ResponseEntity<?> saveAlumno(@RequestBody Alumnos alumno) {
		Alumnos alumnoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			alumnoNew = servicio.save(alumno);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar insert a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El alumno ha sido creado correctamente :D");
		response.put("Alumno", alumnoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
}

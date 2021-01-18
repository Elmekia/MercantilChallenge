package com.ma.pedidos.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ma.pedidos.service.ICrudService;
import com.ma.pedidos.utils.response.ResponseError;

/**
 * 
 * @author matid
 * Esta clase abstracta debe ser extendida por todos los controller que realicen un CRUD
 * y deben implementar los metodos getService() y getLogger()
 *
 * @param <T> Este parametro es el que indica el objeto que maneja este CRUD
 * @param <K> Este parametro es el que indica el tipo de key del objeto de este CRUD
 */
public abstract class CrudController <T,K> {

	protected final Logger log =  LoggerFactory.getLogger(getClass());
	
	@PostMapping()
	public ResponseEntity<HttpStatus> create(@RequestBody T entity) {
		log.info("Se procede a crear el objeto: " + entity);
		try {
			if(!this.getService().validate(entity))
				return new ResponseEntity<HttpStatus>(HttpStatus.UNPROCESSABLE_ENTITY);
			
			this.getService().create(entity);
			log.info("Objeto creado con exito");
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
			
		} catch (DataIntegrityViolationException e) {
			
			log.error("Id repetido para el objeto: " + entity, e);
			return new ResponseEntity<HttpStatus>(HttpStatus.UNPROCESSABLE_ENTITY);
			
		} catch (Exception e) {

			log.error("Error al crear objeto", e);
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable ("id") K id){
		log.info("Se procede a crear el objeto: " + id);
		try {
			T entity = this.getService().get(id);
			if(entity != null)
				return new ResponseEntity<Object>((this.getService().get(id)), HttpStatus.OK);
			else
				return new ResponseEntity<Object>(new ResponseError(this.getNotFoundMessage()),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Error al crear objeto", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable ("id") K id, @RequestBody T entity){
		log.info("Se procede a actualizar el objeto: " + id);
		try {
			this.getService().update(id, entity);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Error al actualizar el objeto", e);
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable ("id") K id){
		log.info("Se procede a eliminar el objeto: " + id);
		try {
			this.getService().delete(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Error al eliminar el objeto", e);
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	protected abstract ICrudService<T,K> getService();
	
	protected abstract String getNotFoundMessage();
	
	
}

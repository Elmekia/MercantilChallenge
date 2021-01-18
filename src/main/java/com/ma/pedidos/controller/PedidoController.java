package com.ma.pedidos.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.dto.PedidoDTO;
import com.ma.pedidos.dto.PedidoResponseDTO;
import com.ma.pedidos.service.IPedidoService;
import com.ma.pedidos.utils.response.ResponseError;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

	protected final Logger log = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	IPedidoService pedidoService;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody PedidoDTO pedido) {
		try {
			log.info("Se procede a crear el pedido: " + pedido.toString());
			List<ResponseError> errores = this.pedidoService.validate(pedido);
			if (errores.size() != 0) {
				Map<String, List<ResponseError>> erroresMap = new HashMap<String, List<ResponseError>>();
				erroresMap.put("errores", errores);
				log.error("Faltan campos requeridos para el producto");
				return new ResponseEntity<Object>(erroresMap, HttpStatus.BAD_REQUEST);
			}
			PedidoResponseDTO pedidoResponse = this.pedidoService.create(pedido);
			log.info("Pedido creado con exito");
			return new ResponseEntity<Object>(pedidoResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error al crear el pedido");
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> getList(@RequestParam String fecha) {
		log.info("Se procede a listar los pedidos de la fecha: " + fecha);
		try {
			List<PedidoResponseDTO> pedidosResponse = this.pedidoService.findByFecha(fecha);
			log.info("Se encontraron " + pedidosResponse.size() + " pedidos para la fecha " + fecha);
			return new ResponseEntity<List<PedidoResponseDTO>>(pedidosResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error al listar pedidos por fecha", e);
			return new ResponseEntity<List<PedidoResponseDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
}

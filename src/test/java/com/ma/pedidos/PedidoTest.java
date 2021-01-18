package com.ma.pedidos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.ma.pedidos.utils.GlobalFunctions;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PedidoTest {

	@LocalServerPort
	private int port;
	
	@Test
    @Order(1)
    public void crearPedidoOK1() {
		JSONObject request = new JSONObject();
        request.put("id", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        request.put("nombre", "Jamón y morrones");
        request.put("descripcionCorta", "Pizza de jamón y morrones");
        request.put("descripcionLarga", "Mozzarella, jamón, morrones y aceitunas verdes");
        request.put("precioUnitario", "550.00");

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/productos");
        
        request = new JSONObject();
        request.put("id", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        request.put("nombre", "Palmito");
        request.put("descripcionCorta", "Pizza de Palmitos");
        request.put("descripcionLarga", "Mozzarella, palmitos y salsa golf");
        request.put("precioUnitario", "600.00");

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/productos");
        request = new JSONObject();
        List<JSONObject> detalle = new ArrayList<JSONObject>();
        request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        request.put("cantidad", "1");
        detalle.add(request);
        request = new JSONObject();
        request.put("producto", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        request.put("cantidad", "1");
        detalle.add(request);
        
        request = new JSONObject();
        request.put("direccion", "Dorton Road 80");
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        response.put("nombre", "Jamón y morrones");
        response.put("cantidad", 1);
        response.put("importe", 550.0F);
        detalleResponse.add(response);
        response = new JSONObject();
        response.put("producto", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        response.put("nombre", "Palmito");
        response.put("cantidad", 1);
        response.put("importe", 600.0F);
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(201).
                    body("fecha", equalTo("2021-01-18"),
                            "direccion", equalTo("Dorton Road 80"),
                            "email", equalTo("tsayb@opera.com"),
                            "telefono", equalTo("(0351) 48158101"),
                            "horario", equalTo("12:00"),
                            "detalle", equalTo(detalleResponse),
                            "total", equalTo(1150.0F),
                            "descuento", equalTo(false),
                            "estado", equalTo("PENDIENTE")).
                    log().all();
    }
	
	@Test
    @Order(2)
    public void crearPedidoOK2() {
		JSONObject request = new JSONObject();
               
       List<JSONObject> detalle = new ArrayList<JSONObject>();
        request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        request.put("cantidad", "1");
        detalle.add(request);
        
        request = new JSONObject();
        request.put("direccion", "Dorton Road 80");
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        response.put("nombre", "Jamón y morrones");
        response.put("cantidad", 1);
        response.put("importe", 550.0F);
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(201).
                    body("fecha", equalTo("2021-01-18"),
                            "direccion", equalTo("Dorton Road 80"),
                            "email", equalTo("tsayb@opera.com"),
                            "telefono", equalTo("(0351) 48158101"),
                            "horario", equalTo("12:00"),
                            "detalle", equalTo(detalleResponse),
                            "total", equalTo(550.0F),
                            "descuento", equalTo(false),
                            "estado", equalTo("PENDIENTE")).
                    log().all();
    }
	
	@Test
    @Order(3)
    public void crearPedidoOKConDescuento() {
		JSONObject request = new JSONObject();
               
       List<JSONObject> detalle = new ArrayList<JSONObject>();
        request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        request.put("cantidad", "2");
        detalle.add(request);
        request = new JSONObject();
        request.put("producto", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        request.put("cantidad", "2");
        detalle.add(request);
        
        request = new JSONObject();
        request.put("direccion", "Dorton Road 80");
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        response.put("nombre", "Jamón y morrones");
        response.put("cantidad", 2);
        response.put("importe", 550.0F);
        detalleResponse.add(response);
        response = new JSONObject();
        response.put("producto", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        response.put("nombre", "Palmito");
        response.put("cantidad", 2);
        response.put("importe", 600.0F);
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(201).
                    body("fecha", equalTo("2021-01-18"),
                            "direccion", equalTo("Dorton Road 80"),
                            "email", equalTo("tsayb@opera.com"),
                            "telefono", equalTo("(0351) 48158101"),
                            "horario", equalTo("12:00"),
                            "detalle", equalTo(detalleResponse),
                            "total", equalTo(1610.0F),
                            "descuento", equalTo(true),
                            "estado", equalTo("PENDIENTE")).
                    log().all();
    }
	
	@Test
    @Order(4)
    public void crearPedidoSinDireccionSinCantidadNoOk() {
		JSONObject request = new JSONObject();
               
       List<JSONObject> detalle = new ArrayList<JSONObject>();
       request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
       detalle.add(request);
        
        request = new JSONObject();
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("error", "la direccion no puede estar nula");
        detalleResponse.add(response);
        response = new JSONObject();
        response.put("error", "falta ingresar cantidad");
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(400).
                    body("errores", equalTo(detalleResponse)).
                    log().all();
    }
	
	@Test
    @Order(5)
    public void crearPedidoSinDireccionNoOk() {
		JSONObject request = new JSONObject();
               
       List<JSONObject> detalle = new ArrayList<JSONObject>();
       request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
       request.put("cantidad", "2");
       detalle.add(request);
        
        request = new JSONObject();
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("error", "la direccion no puede estar nula");
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(400).
                    body("errores", equalTo(detalleResponse)).
                    log().all();
    }
	
	@Test
    @Order(6)
    public void crearPedidoSinCantidadNoOk() {
		JSONObject request = new JSONObject();
               
       List<JSONObject> detalle = new ArrayList<JSONObject>();
       request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
       detalle.add(request);
        
        request = new JSONObject();
        request.put("direccion", "av siempre viva");
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("error", "falta ingresar cantidad");
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(400).
                    body("errores", equalTo(detalleResponse)).
                    log().all();
    }
	
	@Test
    @Order(7)
    public void crearPedidoConCantidadCeroNoOk() {
		JSONObject request = new JSONObject();
               
       List<JSONObject> detalle = new ArrayList<JSONObject>();
       request.put("producto", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
       request.put("cantidad", "0");
       detalle.add(request);
        
        request = new JSONObject();
        request.put("direccion", "av siempre viva");
        request.put("email", "tsayb@opera.com");
        request.put("telefono", "(0351) 48158101");
        request.put("horario", "12:00");
        request.put("detalle", detalle);
        
        List<JSONObject>detalleResponse= new ArrayList<JSONObject>();
        JSONObject response = new JSONObject();
        response.put("error", "falta ingresar cantidad");
        detalleResponse.add(response);
        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/pedidos").
                    then().statusCode(400).
                    body("errores", equalTo(detalleResponse)).
                    log().all();
    }
	
	@Test
    @Order(8)
    public void listarPorFechaOK() {
		LocalDate fecha = GlobalFunctions.getDate();
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/pedidos?fecha="+ fecha).
                    then().statusCode(200).
                    log().all();
	}
	
	@Test
    @Order(9)
    public void listarPorFechaNotFoundOK() {
		        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/pedidos?fecha=2020-01-18").
                    then().statusCode(200).
                    log().all();
	}
	
	@Test
    @Order(10)
    public void listarPorFechaNoOk() {
		        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/pedidos?fecha=").
                    then().statusCode(500).
                    log().all();
	}
	
	@Test
    @Order(11)
    public void listarPorFechaNoOk2() {
		        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/pedidos?fecha=asd").
                    then().statusCode(500).
                    log().all();
	}
	
	@Test
    @Order(12)
    public void listarPorFechaNoOk3() {
		        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/pedidos").
                    then().statusCode(400).
                    log().all();
	}
	
	@Test
    @Order(12)
    public void listarPorFechaNoOk4() {
		        
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/pedidos?fecha=2020-01-32").
                    then().statusCode(500).
                    log().all();
	}
}

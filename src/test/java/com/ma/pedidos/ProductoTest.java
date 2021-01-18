package com.ma.pedidos;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductoTest {

	@LocalServerPort
	private int port;
	
	
	@Test
    @Order(1)
    public void crearProductoOK1() {
        JSONObject request = new JSONObject();
        request.put("id", "e29ebd0c-39d2-4054-b0f4-ed2d0ea088v3");
        request.put("nombre", "provolone");
        request.put("descripcionCorta", "Pizza de provolone");
        request.put("descripcionLarga", "provolone, y aceitunas");
        request.put("precioUnitario", "685.56");

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:"+ port +"/productos").
                    then().statusCode(201).
                    log().all();
	}
	
	@Test
    @Order(2)
    public void crearProductoOK() {
        JSONObject request = new JSONObject();
        request.put("id", "89efb206-2aa6-4e21-8a23-5765e3de1g48");
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
                    post("http://localhost:"+ port +"/productos").
                    then().statusCode(201).
                    log().all();
	}
	
	
	@Test
    @Order(3)
    public void crearProductoOK2() {
        JSONObject request = new JSONObject();
        request.put("id", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089r2");
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
                    post("http://localhost:"+ port +"/productos").
                    then().statusCode(201).
                    log().all();
	}
	
	
	@Test
    @Order(4)
    public void crearProductoRepetidoNoOK() {
        JSONObject request = new JSONObject();
        request.put("id", "89efb206-2aa6-4e21-8a23-5765e3de1g48");
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
                    post("http://localhost:"+ port +"/productos").
                    then().statusCode(422).
                    log().all();
	}
	
	@Test
    @Order(5)
    public void crearProductoSinIdNoOK() {
        JSONObject request = new JSONObject();
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
                    post("http://localhost:"+ port +"/productos").
                    then().statusCode(422).
                    log().all();
	}
	
	@Test
    @Order(6)
    public void obtenerProductoOK() {
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/productos/89efb206-2aa6-4e21-8a23-5765e3de1g48").
                    then().statusCode(200).
                    body("id", equalTo("89efb206-2aa6-4e21-8a23-5765e3de1g48"),
                            "nombre", equalTo("Jamón y morrones"),
                            "descripcionCorta", equalTo("Pizza de jamón y morrones"),
                            "descripcionLarga", equalTo("Mozzarella, jamón, morrones y aceitunas verdes"),
                            "precioUnitario", equalTo(550.0F)).
                    log().all();
	}
	
	@Test
    @Order(7)
    public void obtenerProductoOK2() {
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/productos/e29ebd0c-39d2-4054-b0f4-ed2d0ea089r2").
                    then().statusCode(200).
                    body("id", equalTo("e29ebd0c-39d2-4054-b0f4-ed2d0ea089r2"),
                            "nombre", equalTo("Palmito"),
                            "descripcionCorta", equalTo("Pizza de Palmitos"),
                            "descripcionLarga", equalTo("Mozzarella, palmitos y salsa golf"),
                            "precioUnitario", equalTo(600.00F)).
                    log().all();
	}
	
	@Test
    @Order(8)
    public void obtenerProductoNotFound() {
        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                    get("http://localhost:"+ port +"/productos/123456789").
                    then().statusCode(404).
                    body("error", equalTo("Producto no encontrado")).
                    log().all();
	}
	
	@Test
    @Order(9)
    public void modificarProductoOK() {
	 JSONObject request = new JSONObject();
     request.put("nombre", "provolone");
     request.put("descripcionCorta", "Pizza de provolone");
     request.put("descripcionLarga", "provolone, y aceitunas");
     request.put("precioUnitario", "700.24");

     given().
             header("Content-type", "application/json").
             contentType(ContentType.JSON).
             accept(ContentType.JSON).
             body(request.toJSONString()).
             when().
                 put("http://localhost:"+ port +"/productos/e29ebd0c-39d2-4054-b0f4-ed2d0ea088v3").
                 then().statusCode(204).
                 log().all();
	}
	
	@Test
    @Order(10)
    public void modificarProductoOK2() {
	 JSONObject request = new JSONObject();
     request.put("nombre", "Anchoas");
     request.put("descripcionCorta", "Pizza con anchoas");
     request.put("descripcionLarga", "anchoas");
     request.put("precioUnitario", "815.00");

     given().
             header("Content-type", "application/json").
             contentType(ContentType.JSON).
             accept(ContentType.JSON).
             body(request.toJSONString()).
             when().
                 put("http://localhost:"+ port +"/productos/e29ebd0c-39d2-4054-b0f4-ed2d0ea088v3").
                 then().statusCode(204).
                 log().all();
	}
	
	@Test
    @Order(11)
    public void modificarProductoNoOK() {
	 JSONObject request = new JSONObject();
     request.put("nombre", "Anchoas");
     request.put("descripcionCorta", "Pizza con anchoas");
     request.put("descripcionLarga", "anchoas");
     request.put("precioUnitario", "815.00");

     given().
             header("Content-type", "application/json").
             contentType(ContentType.JSON).
             accept(ContentType.JSON).
             body(request.toJSONString()).
             when().
                 put("http://localhost:"+ port +"/productos").
                 then().statusCode(405).
                 log().all();
	}
	
	@Test
    @Order(12)
    public void modificarProductoNoOK2() {
	 
     given().
             header("Content-type", "application/json").
             contentType(ContentType.JSON).
             accept(ContentType.JSON).
             when().
                 put("http://localhost:"+ port +"/productos/e29ebd0c-39d2-4054-b0f4-ed2d0ea088v3").
                 then().statusCode(400).
                 log().all();
	}
	
	@Test
    @Order(13)
    public void eliminarProductoOK() {
	 
     given().
             header("Content-type", "application/json").
             contentType(ContentType.JSON).
             accept(ContentType.JSON).
             when().
                 delete("http://localhost:"+ port +"/productos/e29ebd0c-39d2-4054-b0f4-ed2d0ea088v3").
                 then().statusCode(204).
                 log().all();
	}
	
	@Test
    @Order(14)
    public void eliminarProductoNoExistenteNoOK() {
	 
     given().
             header("Content-type", "application/json").
             contentType(ContentType.JSON).
             accept(ContentType.JSON).
             when().
                 delete("http://localhost:"+ port +"/productos/abcd-1234").
                 then().statusCode(500).
                 log().all();
	}
	
}


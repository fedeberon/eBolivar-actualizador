package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tipo_impuesto")
public class TipoImpuesto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TI_ID")	
    private Integer codigo;
	
	@Column(name="TI_NOMBRE")
    private String nombre;
    
	@Column(name="TI_PATRON_BUSQUEDA")
	private String patronBusqueda;
	
	@Column(name="TI_ESTADO")
    private String estado;
	
	@Column(name="TI_COMENTARIOS")
    private String comentarios;
	
	@Column(name="TI_COLOR")
    private String color;
	
	@Column(name="TI_URL_IMG")
    private String urlImg;
    
    
	public TipoImpuesto(){}
	
    public TipoImpuesto(Integer codigo){
    	this.codigo = codigo;
    }
    
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPatronBusqueda() {
		return patronBusqueda;
	}
	public void setPatronBusqueda(String patronBusqueda) {
		this.patronBusqueda = patronBusqueda;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
 
    
    
}

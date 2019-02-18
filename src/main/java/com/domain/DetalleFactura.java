package com.domain;

import com.beans.DetalleFacturaPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DETALLE_FACTURA")
@IdClass(value = DetalleFacturaPK.class)
public class DetalleFactura implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer codigo;
	
	@Column(name="DET_TIPO_REGISTRO")
	private Integer tipoRegistro;
	
	@Id
	private String idFactura;
	
	@Id
	private Integer item;
	
	@Column(name="DET_DETALLE")
	private String detalle;
	
	public DetalleFactura(){}
	
	public DetalleFactura(Integer tipoRegistro, String idFactura, Integer item, String detalle){
		this.tipoRegistro = tipoRegistro;
		this.idFactura = idFactura;
		this.item = item;
		this.detalle = detalle;
		
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
	
}

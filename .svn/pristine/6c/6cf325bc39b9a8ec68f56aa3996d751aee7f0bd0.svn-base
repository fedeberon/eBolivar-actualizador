package com.domain;

import com.beans.NotificacionPorBienPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NOTIFICACION_POR_BIEN")
@IdClass(value = NotificacionPorBienPK.class)
public class NotificacionPorBien implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	
	@Column(name = "DET_TIPO_REGISTRO")
	private Integer tipoRegistro;
	
	@Id
	private long codigoElectronicoPago;
	
	@Id
	private Integer numeroNotificacion;
	
	@Column(name = "NPB_NOTIFICACION")
	private String notificacion;
	
	
	public NotificacionPorBien(){}

	public NotificacionPorBien(Integer tipoRegistro, long codigoElectronicoPago, Integer numeroNotificacion, String notificacion){
		this.tipoRegistro = tipoRegistro;
		this.codigoElectronicoPago = codigoElectronicoPago;
		this.numeroNotificacion = numeroNotificacion;
		this.notificacion = notificacion;
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

	public long getCodigoElectronicoPago() {
		return codigoElectronicoPago;
	}

	public void setCodigoElectronicoPago(long codigoElectronicoPago) {
		this.codigoElectronicoPago = codigoElectronicoPago;
	}

	public Integer getNumeroNotificacion() {
		return numeroNotificacion;
	}

	public void setNumeroNotificacion(Integer numeroNotificacion) {
		this.numeroNotificacion = numeroNotificacion;
	}

	public String getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}
	
	
}

package com.beans;

import javax.persistence.Column;
import java.io.Serializable;

public class DetalleFacturaPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="DET_ID_FACTURA")
	private String idFactura;
	
	@Column(name = "DET_ITEM")
	private Integer item;
}

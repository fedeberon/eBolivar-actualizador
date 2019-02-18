package com.batch.actualizadorTasas;

import com.domain.DetalleFactura;
import com.domain.Impuesto;
import com.domain.NotificacionPorBien;
import com.domain.TipoImpuesto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ActualizadorTasasProcessor implements ItemProcessor<String, Object> {
 
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@Resource
	Integer tipoRegistro;
	@Resource
	Integer modadalidad; 
	@Resource
	String rotuloDocumento;
	@Resource
	String observaciones; 
	@Resource
	Date fechaGeneracion;
 
	@Override
	public Object process(String item) throws Exception {
		Integer tipoRegistro = readTipoRegistro(item);

		switch (tipoRegistro) {
		
			case 0: //Impuesto.HEADER:
				cargarHEADER(item);
				return null;
				
			case 2:  //Impuesto.CARGAR_FACTURAS:
				return getImpuesto(item);

			case 3:	//Impuesto.DETALLE_FACTURAS:
				return getDetalleFactura(item);

			case 5: //Impuesto.NOTIFICACIONES:
				return getNotificacionPorBien(item);
		default:
			return null;
		}
	}
	
	private Integer readTipoRegistro(String line) {
		return Integer.parseInt(line.substring(0, 1));
	}

	private Integer cargarTipoRegistro(String line) {
		return line.substring(0, 1).trim().equalsIgnoreCase("") ? null
				: Integer.parseInt(line.substring(0, 1).trim());
	}
	 
	private String cargarNumeroFactura(String line) {
		return line.substring(1, 21).trim().equalsIgnoreCase("") ? null : line
				.substring(1, 21).trim();
	}

	private Integer cargarItem(String line) {
		return Integer.parseInt(line.substring(21, 24).trim()
				.equalsIgnoreCase("") ? null : line.substring(21, 24).trim());
	}

	private String cargarDetalleFactura(String line) {
		return line.substring(24, line.length()).trim().equalsIgnoreCase("") ? null : line.substring(24, line.length()).trim();
	}

	private Long cargarCodigoElectronicoPago(String line) {
		return Long
				.parseLong(line.substring(1, 15).trim().equalsIgnoreCase("") ? null
						: line.substring(1, 15).trim());
	}

	private Integer cargarNumeroNotificacion(String line) {
		return Integer.parseInt(line.substring(15, 19).trim()
				.equalsIgnoreCase("") ? null : line.substring(15, 19).trim());
	}

	private String cargarNotificacion(String line) {
		return line.substring(19, line.length()).trim().equalsIgnoreCase("") ? null
				: line.substring(15, line.length()).trim();
	}
  
	private DetalleFactura getDetalleFactura(String linea) {
		Integer tipoRegistro = cargarTipoRegistro(linea);
		String idFactura = cargarNumeroFactura(linea);
		Integer item = cargarItem(linea);
		String detalle = cargarDetalleFactura(linea);
		return new DetalleFactura(tipoRegistro, idFactura, item, detalle);
	}
	 
	private NotificacionPorBien getNotificacionPorBien(String linea) {
		Integer tipoRegistro = cargarTipoRegistro(linea);
		Long codigoElectronicoPago = cargarCodigoElectronicoPago(linea);
		Integer numeroNotificacion = cargarNumeroNotificacion(linea);
		String notificacion = cargarNotificacion(linea);
		return new NotificacionPorBien(tipoRegistro, codigoElectronicoPago, numeroNotificacion,	notificacion);
	}
	
	private Impuesto getImpuesto(String line) throws ParseException{
		 	Impuesto impuesto = new Impuesto();
		 	
			impuesto.setTipoRegistro_2(line.substring(0, 1).trim().equalsIgnoreCase("")?null:Integer.parseInt(line.substring(0, 1).trim()));
	    	
	     	impuesto.setIdFactura(getIdToLine(line));
	     	
	     	impuesto.setIdCliente(line.substring(21, 35).trim().equalsIgnoreCase("")?null:line.substring(21, 35).trim());
	     	
			impuesto.setNum_cuit_0(line.substring(35, 46).trim().equalsIgnoreCase("") ? null : line.substring(35, 46).trim()); 
			
			impuesto.setNro_documento(line.substring(46, 54).trim().equalsIgnoreCase("")?null:Integer.parseInt(line.substring(46, 54).trim()));
			
			impuesto.setTipoDocuento(line.substring(54, 59).trim().equalsIgnoreCase("")?null:Integer.parseInt(line.substring(54, 59).trim()));

			impuesto.setCodigoElectronicoPago(line.substring( 59, 73).trim().equalsIgnoreCase("")?null:line.substring(59, 73).trim());
			
			impuesto.setCodigoSeguridad(line.substring(73, 78 ).trim().equalsIgnoreCase("")?null:line.substring(73, 78).trim());
			
			impuesto.setVencimientoCodigoSeg(line.substring( 78, 86).trim().equalsIgnoreCase("")?null:dateFormat.parse(line.substring( 78, 86).trim()));
			
			impuesto.setEstado(line.substring(86, 87).trim().equalsIgnoreCase("")?null:Integer.parseInt(line.substring(86, 87).trim()));

			impuesto.setFiller_0(line.substring(87, 88).trim().equalsIgnoreCase("")?null:Integer.parseInt(line.substring(87, 88).trim()));
			
			impuesto.setUltimoDiaPago(line.substring(88, 96).trim().equalsIgnoreCase("")?null:dateFormat.parse(line.substring(88, 96).trim()));
			
			impuesto.setLeyendaTributo(line.substring(96, 176).trim().equalsIgnoreCase("")?null:line.substring(96, 176).trim());
			
			impuesto.setLeyendaBien(line.substring(176, 256).trim().equalsIgnoreCase("")?null:line.substring(176, 256).trim());
			
			impuesto.setLeyendaBienAdicional(line.substring(256, 336).trim().equalsIgnoreCase("")?null:line.substring(256, 336).trim());
			
			impuesto.setLeyendaConcepto(line.substring(336, 416 ).trim().equalsIgnoreCase("")?null:line.substring(336, 416 ).trim());
			
			impuesto.setLeyendaAdicional(line.substring(416, 496 ).trim().equalsIgnoreCase("")?null:line.substring(416, 496 ).trim());
			
			impuesto.setPrimerVencimiento(line.substring(496, 504 ).trim().equalsIgnoreCase("")?null:dateFormat.parse(line.substring(496, 504 ).trim()));
			
			impuesto.setImporte1reVencimiento(line.substring(504, 516 ).trim().equalsIgnoreCase("")?null:line.substring(504, 516 ).trim());
			
			impuesto.setSegundoVencimiento(line.substring(516, 524 ).trim().equalsIgnoreCase("")?null:dateFormat.parse(line.substring(516, 524 ).trim()));
			
			impuesto.setImporte2doVencimiento(line.substring(524, 536 ).trim().equalsIgnoreCase("")?null:line.substring(524, 536 ).trim());
			
			impuesto.setTercerVencimiento(line.substring(536, 544 ).trim().equalsIgnoreCase("")?null:dateFormat.parse(line.substring(536, 544 ).trim()));
			
			impuesto.setImporte3erVencimiento(line.substring(544, 556 ).trim().equalsIgnoreCase("")?null:line.substring(544, 556 ).trim());
			
			impuesto.setCodigoDeBarra(line.substring(556, 602 ).trim().equalsIgnoreCase("")?null:line.substring(556, 602 ).trim());
			
			impuesto.setNumeroPadron(impuesto.getCodigoDeBarra().substring(7, 15));
			
			TipoImpuesto tipoImpuesto = new TipoImpuesto(Integer.parseInt(impuesto.getCodigoDeBarra().substring(5, 7)));
			
			impuesto.setTipoImpuesto(tipoImpuesto);

			impuesto.setFiller_1(line.substring(602, 614 ).trim().equalsIgnoreCase("")?null:line.substring(602, 614 ).trim());
			
			impuesto.setObervacion(this.observaciones);
			impuesto.setModalidadArchivo(this.modadalidad);
			impuesto.setFechaGeneracion(this.fechaGeneracion);
			impuesto.setTipoRegistro0(this.tipoRegistro);
			impuesto.setRotuloArchivo(this.rotuloDocumento);
			
			return impuesto;
		}

	private String getIdToLine(String line) {
		return  line.substring(1, 21).trim();
	}

	private void cargarHEADER(String line) throws ParseException {
 		tipoRegistro = cargarTipoRegistro(line);
 		rotuloDocumento = cargarRoturo(line);
 		modadalidad = cargarModalidad(line);
 		observaciones = cargarObservaciones(line);
 		fechaGeneracion = cargarFechaGeneracionArchivo(line);
	}
	
	private String cargarRoturo(String line){
		return line.substring(1, 29).trim().equalsIgnoreCase("")?null:line.substring(1, 29).trim();
	}
	
	/** La modalidad 0 reemplaza el contenido de las tablas.
	 *  La modalidad 1 notifica nuevas altas/modificaciones de tipos de registro 2 y/o cancelaciones de facturas (tipo de registro 3)
	 *  @param {@code String} line
	 *  @return {@code Integer} modalidad */
	private Integer cargarModalidad(String line){
 		Integer modalidad = line.substring(39,40).trim().equalsIgnoreCase("") ? null : Integer.parseInt(line.substring(39,40).trim());
 		return modalidad;
	}
	
	private String cargarObservaciones(String line){
 		return line.substring(41, 160).trim().equalsIgnoreCase("") ? null : line.substring(41,160).trim();
	}

	private Date cargarFechaGeneracionArchivo(String line) throws ParseException{
		Date fechaGeneracion = line.substring(31,39).trim().equalsIgnoreCase("") ? null : dateFormat.parse(line.substring(31,39).trim());
		return fechaGeneracion;
	}

}

package com.batch.actualizadorTasas;

import com.domain.DetalleFactura;
import com.domain.Impuesto;
import com.domain.NotificacionPorBien;
import com.services.DetalleFacturaService;
import com.services.ImpuestoService;
import com.services.NotificacionPorBienService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActualizadorTasasWriter implements ItemWriter<Object> {

    @Autowired
    private ImpuestoService impuestoService;

    @Autowired
    private NotificacionPorBienService notificacionPorBienService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Override
    public void write(List<?> items) throws Exception {
        for (Object object : items) {
            if (object instanceof Impuesto) {
                Impuesto impuesto = (Impuesto) object;
                System.out.println("Impuesto: " + impuesto.getIdFactura());
                impuestoService.save(impuesto);
            } else if (object instanceof NotificacionPorBien) {
                NotificacionPorBien notificacionPorBien = (NotificacionPorBien) object;
                System.out.println("Notificacion: " + notificacionPorBien.getCodigoElectronicoPago());
                notificacionPorBienService.save(notificacionPorBien);
            } else if (object instanceof DetalleFactura) {
                DetalleFactura detalleFactura = (DetalleFactura) object;
                System.out.println("Detalle factura " + detalleFactura.getIdFactura());
                detalleFacturaService.save(detalleFactura);
            }
        }
    	
    	
    }
}
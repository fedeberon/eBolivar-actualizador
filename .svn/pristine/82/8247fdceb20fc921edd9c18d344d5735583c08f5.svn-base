package com.services;

import com.beans.TasasANotificar;
import com.domain.Impuesto;
import com.persist.ImpuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class ImpuestoService {

    @Autowired
    private ImpuestoRepository impuestoRepository;
    
	@PersistenceContext
	private EntityManager em;
	
    public void save(Impuesto impuesto) {
        impuestoRepository.saveAndFlush(impuesto);
    }

    @SuppressWarnings("unchecked")
	public List<TasasANotificar> tasasAnotificar(){
    	Query query = em.createNativeQuery("select i.IMP_ID_FACTURA, i.IMP_NUMERO_PADRON, n.NP_NOMBRE_APELLIDO, n.NP_DIRECCION_ENVIO,i.IMP_IMPORTE_1ER_VENCIMIENTO, t.TI_NOMBRE"
    			+ " from impuestos i"
    			+ " left join notificaciones_padrones n on i.IMP_NUMERO_PADRON = n.NP_PADRON"
                + " join tipo_impuesto t on i.TI_ID = t.TI_ID"
    			+ " where i.IMP_1ER_VENCIMIENTO > NOW()"
                + " and n.NP_DIRECCION_ENVIO is not null","tasasANotificarPorPadron");
    	List<TasasANotificar> tasas = query.getResultList();
    	return tasas;
    }
}
package com.services;

import com.domain.DetalleFactura;
import com.persist.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleFacturaService {

	@Autowired(required=true)
	private DetalleFacturaRepository detalleFacturaRepository;
	
	public void save(DetalleFactura detalleFactura){
		detalleFacturaRepository.saveAndFlush(detalleFactura);
	}
}

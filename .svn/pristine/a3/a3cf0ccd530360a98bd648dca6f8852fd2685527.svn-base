package com.services;

import com.domain.NotificacionPorBien;
import com.persist.NotificacionPorBienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionPorBienService {

	@Autowired(required=true)
	private NotificacionPorBienRepository notificacionPorBienRepository;
	
	public void save(NotificacionPorBien notificacionPorBien){
		notificacionPorBienRepository.saveAndFlush(notificacionPorBien);
	}
}

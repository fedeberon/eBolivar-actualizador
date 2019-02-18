package com.batch.sendMails;

import com.beans.TasasANotificar;
import com.services.ImpuestoService;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class SendMailReader implements ItemReader<TasasANotificar> {

	@Autowired
	ImpuestoService impuestoService;

	private Iterator<TasasANotificar> notificacionPadronIterator = null;

	@BeforeStep
	private void init() {
		List<TasasANotificar> notificacionPadron = impuestoService.tasasAnotificar();
		this.notificacionPadronIterator = notificacionPadron.iterator();
	}

	@Override
	public TasasANotificar read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if (notificacionPadronIterator.hasNext()) return this.notificacionPadronIterator.next();
		else return null;
	}
}

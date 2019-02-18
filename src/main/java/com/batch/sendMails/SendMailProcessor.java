package com.batch.sendMails;

import com.beans.TasasANotificar;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class SendMailProcessor implements ItemProcessor<TasasANotificar, MimeMessage> {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${jasper.report}")
	String path;
 	
	@Override
	public MimeMessage process(TasasANotificar item) throws Exception {
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(item.getEmail()));
        mimeMessage.setFrom(new InternetAddress(item.getEmail()));
        mimeMessage.setFileName(item.getNombreDeTasa() + TasasANotificar.EXTENSION_PDF);
        mimeMessage.setSubject("Tasa Municipal - Municipalidad de Bolivar");
        mimeMessage.setDataHandler(attachmentFile(item));
        return mimeMessage;
	}

	private DataHandler attachmentFile(TasasANotificar tasa) throws JRException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(createTasa(tasa), baos);
		final javax.activation.DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		return new DataHandler(dataSource);
	}
	
	public JasperPrint createTasa(TasasANotificar tasa) throws JRException {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("idFactura", tasa.getNumeroFactura());
		mapa.put("importe", parseImporte(tasa.getImporte()));
		File file = new File(path);
		JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(reporte, mapa, dataSource.getConnection());

		} catch (Exception e) {
			e.printStackTrace();

		}
		return jasperPrint;

	}
	
	private String parseImporte(Integer importe){
		String valueImporte = String.valueOf(importe);
		String entero = valueImporte.substring(0, valueImporte.length() - 2);
		if (entero.length() > 3) {
			entero = entero.substring(0, entero.length() - 3) + ","	+ entero.substring(entero.length() - 3, entero.length());
		}

		String decimal = valueImporte.substring(valueImporte.length() - 2, valueImporte.length());

		return "$ " + entero + "." + decimal;
	}

}

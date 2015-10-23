package lilas.web.report;

import java.util.List;

import lilas.ejb.entity.Prestation;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class Datasource implements JRDataSource{
	
	private List<Prestation> prestations;
	private int index=-1;
	

	public Datasource(List<Prestation> prestations) {
		super();
		this.prestations= prestations;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		String fieldName = jrf.getName();
        Prestation prestation = prestations.get(index);
        if ("libelle".equals(fieldName)) {
            return prestation.getLibelle();
        } else if ("montantPres".equals(fieldName)) {
            return prestation.getMontantPres();
        } else if ("remise".equals(fieldName)) {
            return prestation.getRemise().getPourcentage();
        }
        return "";
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++index < prestations.size();
	}
  
}

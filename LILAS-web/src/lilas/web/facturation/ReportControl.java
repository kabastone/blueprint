package lilas.web.facturation;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Window;

public class ReportControl  extends SelectorComposer<Component>  {
	
	private static final long serialVersionUID = 1L;





	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	

	
	
	public void onShowReport(ForwardEvent event) {
	    String type = event.getData().toString();
	    Window win = (Window) Executions.createComponents("report.zul", null, null);
	     
	    Map<String, Object> params = new HashMap<String, Object>();
	    // Add parameters used in this report
	    params.put("Titre", "My Food List");
	   // params.put("PatientDataSource", new ReportControl(patients));
	     
	    Jasperreport report = (Jasperreport) win.getFellow("report");
	    report.setType(type);
	    report.setParameters(params);
	}


}

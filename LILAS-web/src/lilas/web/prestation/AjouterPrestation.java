package lilas.web.prestation;

import java.util.HashMap;
import java.util.Map;

import lilas.ejb.entity.Prestation;
import lilas.ejb.entity.Remise;
import lilas.ejb.session.PrestationFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

public class AjouterPrestation extends SelectorComposer<Component> {

	/**
	 * 
	 */
	@Wire
	private Textbox txtLibelle;
	@Wire 
	private Doublebox txtMontant; 
	//@Wire
	//private Spinner spRemise;
	private Prestation prestation = new Prestation();
	
	
	private static final long serialVersionUID = 1L;
	private Map<String,Object> map = new HashMap<String,Object>();
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		map = (Map<String, Object>) Executions.getCurrent().getArg();
		  prestation = (Prestation) map.get("prestation");
		  if(prestation != null){
		  txtLibelle.setValue(prestation.getLibelle());
		  txtMontant.setValue(prestation.getMontantPres());
		  //spRemise.setValue(prestation.getRemise());
		  
		  /*if(prestation.getIsDomicile()){
			  rdFormule.setChecked(true);
		  }else{
			  rdPrestation.setChecked(true);
		  }*/
		}else{
			prestation = new Prestation();
			//spRemise.setValue(0);
		}
		
	}

	@Listen("onClick=#btnajouter")
	public void ajouter(){
		
		prestation.setLibelle(txtLibelle.getValue());
		prestation.setMontantPres(txtMontant.getValue());
		Remise remise = new Remise();
		remise.setPourcentage(0);
		prestation.setRemise(remise);
		//int remise = spRemise.getValue() != null ? spRemise.getValue() : null;
		//Remise r = ne;
		/*if(radioGroup.getSelectedItem().getLabel().equalsIgnoreCase("formule")){
			prestation.setIsDomicile(true);
			
		}else{
			prestation.setIsDomicile(false);
		} */
	    
		PrestationFacade pf = (PrestationFacade) JNDIUtils.lookUpEJB(EJBRegistry.PrestationFacade);
	    pf.edit(prestation);
	    Messagebox.show("Prestation ajoutée");
	    Events.postEvent("onAddPrestation", getSelf().getParent(), prestation);
	    getSelf().detach();
	}

}

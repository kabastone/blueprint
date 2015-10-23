package lilas.web.prestation;

import java.util.HashMap;
import java.util.Map;

import lilas.ejb.entity.Prestation;
import lilas.ejb.session.PrestationFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

public class PrestationControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox lsPrestation;
	private ListModelList<Prestation> prestationModel;
	private PrestationFacade pf = (PrestationFacade) JNDIUtils
			.lookUpEJB(EJBRegistry.PrestationFacade);

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		prestationModel = new ListModelList<Prestation>(pf.findAll());
		lsPrestation.setModel(prestationModel);
	}

	@Listen("onClick=#btAjouter")
	public void creerPrestation() {
		// TODO Auto-generated method stub
		
			Window win = (Window) Executions.createComponents(
					"/pages/prestations/creer_prestation.zul", getSelf(), null);
			win.setMode(Mode.MODAL);
		 
	}
	@Listen("onDoubleClick=#lsPrestation")
	public void modifierPrestation() {
		// TODO Auto-generated method stub
		
			Map<String, Object> param = new HashMap<String, Object>();
			Prestation p = lsPrestation.getSelectedItem().getValue();
			param.put("prestation", p);
			Window win = (Window) Executions
					.createComponents(
							"/pages/prestations/creer_prestation.zul",
							getSelf(), param);
			win.setMode(Mode.MODAL);
		
	}

	@Listen("onAddPrestation=window")
	public void refresh(Event event) {
		Prestation p = (Prestation) event.getData();
		if (lsPrestation.getSelectedItem() == null) {
			((ListModelList<Prestation>) prestationModel).add(p);
		}else{
			((ListModelList<Prestation>) prestationModel).remove(lsPrestation.getSelectedIndex());
			((ListModelList<Prestation>) prestationModel).add(p);
		}
	}
}

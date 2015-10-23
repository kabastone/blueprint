package lilas.web.facturation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lilas.ejb.entity.Facturation;
import lilas.ejb.entity.Prestation;
import lilas.ejb.session.FactureFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;
import lilas.web.report.Datasource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

public class FacturationControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Wire
	private Listbox lsFacture;
	private ListModelList<Facturation> factureModel = new ListModelList<Facturation>();
	FactureFacade ff = (FactureFacade) JNDIUtils
			.lookUpEJB(EJBRegistry.FactureFacade);
	private Facturation ft = new Facturation();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub

		super.doAfterCompose(comp);
		factureModel = new ListModelList<Facturation>(ff.findAll());
		lsFacture.setModel(factureModel);
		//tbFactureImpay.setDisabled(true);
		lsFacture.addEventListener("onDoubleClick", new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {

				Events.postEvent("onClickToolbar", getSelf(), null);
				// afficherFacture();
			}
		});

	}

	@Listen("onSelect=#lsFacture")
	public void pickFacture() {
		ft = lsFacture.getSelectedItem().getValue();

	}

	@Listen("onClickToolbar=window")
	public void afficherFacture(Event data) {
		String type = "pdf";
		// Facture ft = lsFacture.getSelectedItem().getValue();
		String path = "C:\\PROJET\\DEVELOPER\\report\\";

		Window win = (Window) Executions.createComponents(
				"/pages/facturation/report.zul", null, null);

		Map<String, Object> params = new HashMap<String, Object>();
		// Add parameters used in this report
		if (ft == null) {
			ft = (Facturation) data.getData();
		}
		params.put("client", ft.getClient());
		params.put("nbrPers", ft.getNbrePers()+"");
		
		params.put("image", ft.getParam().getLogo());
		params.put("ninea", ft.getParam().getNinea());
		params.put("dateFacture", ft.getDateFacture()+"");
		params.put("dateDeb", ft.getEntree()+"");
		params.put("dateFin", ft.getSortie()+"");
		params.put("numFacture", ft.getNumFacture().toString());
		params.put("phone", ft.getTelephone());
		
				//+ ft.getPatient().getTelephoneFixe());
		//params.put("adresse", ft.getPatient().getAdresse());
		//params.put("modePaie", ft.getModePaie());
		//params.put("solde", ft.getSolde() + "");
		//String titre = ft.getIsDevis() ? "DEVIS" : "FACTURE";
		//params.put("facture", titre);
		Double totalMontant = 0d;
		Double montantRemise = 0d;
		
		for (Prestation p : ft.getPrestations()) {
			if(p.getRemise().getPourcentage() != 0){
				montantRemise = (p.getMontantPres() * p.getRemise().getPourcentage()) / 100;
			}
			totalMontant += (p.getMontantPres()-montantRemise);
		}
		params.put("montantTotal", totalMontant+"");
		params.put("taxeJour", ft.getTaxeSejour()+"");
		Double montantTVA = 0d;
		if(ft.getTva() == 10){
			montantTVA = totalMontant * 0.1;
			params.put("tva10", montantTVA+"");
		}else{
			montantTVA = totalMontant * 0.18;
			params.put("tva18", montantTVA);
		}
		Double total = totalMontant + montantTVA + ft.getTaxeSejour();
		params.put("total", total+"");
		List<Prestation> listesP = new ArrayList<Prestation>();

		listesP.addAll(ft.getPrestations());
		//List<Reglement> reglements = new ArrayList<Reglement>();
		//reglements.addAll(ft.getReglements());
		if (!listesP.isEmpty()) {
			params.put("dataSource", new Datasource(listesP));
		}
		/*if (!reglements.isEmpty()) {
			params.put("reglementDatasource", new ReglementDataSource(
					reglements));

		}*/
		Jasperreport report = (Jasperreport) win.getFellow("report");
		report.setType(type);
		report.setSrc(path + "factureLilas.jasper");
		report.setParameters(params);
	}

	@Listen("onClick=#tbReglement")
	public void afficherReglement() {
		if (lsFacture.getSelectedItem() != null) {
			Facturation facture = new Facturation();
			facture = lsFacture.getSelectedItem().getValue();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("facture", facture);
			Window win = (Window) Executions.createComponents(
					"/pages/facturation/reglement.zul", getSelf(), map);
			win.setMode(Mode.MODAL);
		} else
			return;
	}

	@Listen("onClick=#tbSupprimer")
	public void suppReglement() {
		if (lsFacture.getSelectedItem() != null) {

			ft = lsFacture.getSelectedItem().getValue();
			if (ff.supprimer(ft) == null) {
				Clients.showNotification("Suppression impossible", true);
			}
			((ListModelList<Facturation>) factureModel).remove(lsFacture
					.getSelectedIndex());
		} else
			return;
	}

	/*@Listen("onClick=#tbFacturePay")
	public void afficherFacturePaye() {
		tbFactureImpay.setDisabled(false);
		tbFacturePay.setDisabled(true);
		factureModel = new ListModelList<Facturation>(ff.listeFacturePaye());
		lsFacture.setModel(factureModel);
	}

	@Listen("onClick=#tbFactureImpay")
	public void afficherFactureImPaye() {
		tbFacturePay.setDisabled(false);
		tbFactureImpay.setDisabled(true);
		factureModel = new ListModelList<Facturation>(ff.listeFactureImpaye());
		lsFacture.setModel(factureModel);
	}
*/
	@Listen("onSoldeFacture=window")
	public void refreshListeImpaye() {
		factureModel = new ListModelList<Facturation>(ff.listeFactureImpaye());
		lsFacture.setModel(factureModel);
	}

	/*@Listen("onClick=#tbDevis")
	public void afficherDevis() {

		factureModel = new ListModelList<Facturation>(ff.listeDevis());
		lsFacture.setModel(factureModel);
	}*/

	@Listen("onClick=#tbModifier")
	public void modifier() {
		if (lsFacture.getSelectedItem() != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			Facturation facture = new Facturation();
			facture = lsFacture.getSelectedItem().getValue();
			param.put("facture", facture);
			Window win = (Window) Executions
					.createComponents(
							"/pages/facturation/modifier_facture.zul",
							getSelf(), param);
			win.setMode(Mode.MODAL);
		}

	}

	@Listen("onUpdateFacture=window")
	public void updateEvent(Event event) {
		Facturation f = (Facturation) event.getData();
		/*if(!factureModel.isEmpty()){
		((ListModelList<Facturation>) factureModel).remove(lsFacture
				.getSelectedIndex());
		}*/
		((ListModelList<Facturation>) factureModel).add(f);
	}
	@Listen("onClick=#tbAjouter")
	public void ajouterFacture(){
		Window win = (Window) Executions.createComponents("/pages/facturation/creer_facture.zul", getSelf(),null);
		win.setMode(Mode.MODAL);
	}
}

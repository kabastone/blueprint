package lilas.web.facturation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lilas.ejb.entity.Facturation;
import lilas.ejb.entity.Prestation;
import lilas.ejb.session.FactureFacade;
import lilas.ejb.session.PrestationFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModelSet;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

public class AjouterFactureControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox lsPrestation, lsReglement;
	@Wire
	private Button btAddPrestation, btSupPrestation;
	@Wire
	private Combobox cbPrestation, cbModePaie;

	@Wire
	private Doublebox dbMontant, txtMontant;
	@Wire
	private Textbox txtObservation,txtNom,txtTelephone,chambre;
	@Wire
	private Datebox dateEntree,dateSortie;
	@Wire
	private Spinner nbrePersonne;
	Prestation selected = new Prestation();
	private ListModelList<Prestation> prestationModel = new ListModelList<Prestation>();
	private ListModelSet<Prestation> comboPrestationModel = new ListModelSet<Prestation>();
	//private ListModelList<Reglement> reglementModel = new ListModelList<Reglement>();
	private PrestationFacade prestationFacade = (PrestationFacade) JNDIUtils.lookUpEJB(EJBRegistry.PrestationFacade);
	private Boolean isDevis;
	private Double solde = 0d;
	private Double totalmontant = 0d;
	
	@Wire
	private Label llSolde;
	public static final String ACTION_TOOLBAR = "onClickToolbar";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		
			comboPrestationModel = new ListModelSet<Prestation>(
					prestationFacade.findPrestation());
		
		cbPrestation.setModel(comboPrestationModel);
		lsPrestation.setItemRenderer(new ListitemRenderer<Prestation>() {

			@Override
			public void render(Listitem item, Prestation data, int index)
					throws Exception {
				new Listcell(data.getLibelle()).setParent(item);
				new Listcell(data.getMontantPres() + "").setParent(item);

			}
		});
		lsPrestation.setModel(prestationModel);
		/*lsReglement.setItemRenderer(new ListitemRenderer<Reglement>() {

			@Override
			public void render(Listitem item, Reglement data, int index)
					throws Exception {
				item.setValue(data);
				new Listcell(data.getObservation()).setParent(item);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dateF = df.format(data.getDateReglement());
				new Listcell(dateF).setParent(item);
				new Listcell(data.getMontant() + "").setParent(item);

			}
		});
		lsReglement.setModel(reglementModel);*/

		solde = 0d;
		llSolde.setValue(solde + "");

	}

	@Listen("onSelect=#cbPrestation")
	public void afficherPrix() {
		Prestation p = cbPrestation.getSelectedItem().getValue();
		dbMontant.setValue(p.getMontantPres());
		dbMontant.setReadonly(true);

	}

	@Listen("onClick=#btAddPrestation")
	public void addPrestation() {

		Prestation prestation = cbPrestation.getSelectedItem().getValue();
		//prestation.setLibelle(cbPrestation.getSelectedItem().getLabel());
		//prestation.setPrix(dbMontant.getValue());
		((ListModelList<Prestation>) prestationModel).add(prestation);
		totalmontant += dbMontant.getValue();
		solde += dbMontant.getValue();
		llSolde.setValue(solde + "");
	}

	/*@Listen("onClick=#btAddRegl")
	public void addReglement() {
		if (solde < txtMontant.getValue()) {
			Messagebox.show("Montant supérieur au solde");
		} else {
			Reglement reglement = new Reglement();
			reglement.setObservation(txtObservation.getValue());
			reglement.setDateReglement(dtReglement.getValue());
			reglement.setMontant(txtMontant.getValue());
			((ListModelList<Reglement>) reglementModel).add(reglement);
			solde = solde - txtMontant.getValue();
			llSolde.setValue(solde + "");
		}
	}
*/
	@Listen("onSelect=#lstPrestation")
	public void selectPrestation() {

		selected = lsPrestation.getSelectedItem().getValue();
	}

	@Listen("onClick=#btSupPrestation")
	public void supprimerPrestation() {
		Prestation prestation = lsPrestation.getSelectedItem().getValue();
		((ListModelList<Prestation>) prestationModel).remove(lsPrestation
				.getSelectedIndex());
		if (prestationModel.isEmpty()) {
			solde = 0d;
			totalmontant = 0d;
			llSolde.setValue(0 + "");
			//reglementModel.removeAll(reglementModel.getInnerList());
		}

	}

	/*@Listen("onClick=#btSuppRegl")
	public void supprimerReglement() {
		Reglement r = lsReglement.getSelectedItem().getValue();
		solde = solde + r.getMontant();
		llSolde.setValue(solde + "");
		// prestation = lstPrestation.getSelectedItem().getValue();
		((ListModelList<Reglement>) reglementModel).remove(lsReglement
				.getSelectedIndex());
		if (reglementModel.isEmpty()) {
			solde = totalmontant;
			llSolde.setValue(totalmontant + "");

		}

	}*/

	@Listen("onClick=#btValider")
	public void valider() {
		Facturation facture = new Facturation();
		facture.setEntree(dateEntree.getValue());
		// facture.setEtat("Non payée");
		facture.setSortie(dateSortie.getValue());
		Set<Prestation> listPrest = new HashSet<Prestation>();
		listPrest.addAll(prestationModel.getInnerList());
		facture.setPrestations(listPrest);
		//Set<Reglement> listRgl = new HashSet<Reglement>();
		//listRgl.addAll(reglementModel.getInnerList());
		//facture.setReglements(listRgl);
		facture.setMontantLettre("");
		facture.setModePaie(cbModePaie.getSelectedItem().getLabel());
		Double mntTTC = totalmontant + 1000 + 200;
		facture.setMontantTTC(mntTTC);
		facture.setNbrePers(nbrePersonne.getValue());
		facture.setNumChambre(chambre.getValue());
		//facture.setIsDevis(isDevis);
		//facture.setIsDeleted(false);
		/*if (solde == 0) {
			facture.setEtat("Payée");
		} else {
			facture.setEtat("Non payée");
		}

		facture.setSolde(solde);*/
		FactureFacade ff = (FactureFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.FactureFacade);
		ff.edit(facture);
		Clients.showNotification("Facture enregistrée");
		Events.postEvent("onClickToolbar", getSelf().getParent(), facture);
		getSelf().detach();

	}

	/*@Listen("onClick=#btAnnuler")
	public void onCancel() {
		prestationModel = new ListModelList<Prestation>();
		reglementModel = new ListModelList<Reglement>();
		dbMontant.setValue(0d);
		txtObservation.setValue("");

	}*/
}

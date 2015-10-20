package lilas.web.facturation;

import java.util.HashMap;
import java.util.Map;

import lilas.ejb.entity.Facturation;
import lilas.ejb.entity.Prestation;
import lilas.ejb.session.PrestationFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModelSet;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

public class ModifierFactureControl extends SelectorComposer<Component>{

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
	private Doublebox dbMontant,txtMontant;
	@Wire
	private Textbox txtObservation;
	@Wire
	private Datebox dtReglement;
	private Prestation selected = new Prestation();
	private ListModelList<Prestation> prestationModel = new ListModelList<Prestation>();
	private ListModelSet<Prestation> comboPrestationModel = new ListModelSet<Prestation>();
	//private ListModelList<Reglement> reglementModel = new ListModelList<Reglement>();
	private Boolean isDevis;
    private Facturation facture = new Facturation();
	//private Patient patient;
	private Double totalmontant = 0d;
	private Double solde = totalmontant;
	@Wire
	private Label llSolde;
	private Comboitem selectedMode;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Map<String, Object> map = new HashMap<String, Object>();
		PrestationFacade prestationFacade = (PrestationFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.PrestationFacade);

		map = (Map<String, Object>) Executions.getCurrent().getArg();
		//patient = (Patient) map.get("patientP");
		//isDevis = (Boolean) map.get("isDevis");
         facture = (Facturation) map.get("facture");
         totalmontant = facture.getMontantTTC();
         //solde = facture.getSolde();
         //llSolde.setValue(solde+"");
		/*llTelephone.setValue(patient.getTelephoneMobile() + "/"
				+ patient.getTelephoneFixe());
		llAdresse.setValue(patient.getAdresse());
		llPrenom.setValue(patient.getPrenomPatient());
		llNom.setValue(patient.getNomPatient());*/
		
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
		prestationModel = new ListModelList<Prestation>(facture.getPrestations());
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
				new Listcell(data.getMontant()+"").setParent(item);

			}
		});*/
		//reglementModel = new ListModelList<Reglement>(facture.getReglements());
		//lsReglement.setModel(reglementModel);
		cbModePaie.setValue(facture.getModePaie());
		//selectedMode.setValue(facture.getModePaiement());
        
	}

	@Listen("onSelect=#cbPrestation")
	public void afficherPrix() {
		Prestation p = cbPrestation.getSelectedItem().getValue();
		dbMontant.setValue(p.getMontantPres());
		dbMontant.setReadonly(true);

	}

	@Listen("onClick=#btAddPrestation")
	public void addPrestation() {

		Prestation prestation = new Prestation();
		prestation.setLibelle(cbPrestation.getSelectedItem().getLabel());
		prestation.setMontantPres(dbMontant.getValue());
		totalmontant += dbMontant.getValue();
		solde +=dbMontant.getValue();
		llSolde.setValue(solde+"");
		((ListModelList<Prestation>) prestationModel).add(prestation);
		facture.getPrestations().add(prestation);
	}

	/*@Listen("onClick=#btAddRegl")
	public void addReglement() {
        // solde = totalmontant;
         if(solde < txtMontant.getValue()){
        	 Messagebox.show("Montant supérieur au solde");
         }else{
		Reglement reglement = new Reglement();
		reglement.setObservation(txtObservation.getValue());
		reglement.setDateReglement(dtReglement.getValue());
		reglement.setMontant(txtMontant.getValue());
		solde = solde - txtMontant.getValue();
		llSolde.setValue(solde+"");
		((ListModelList<Reglement>) reglementModel).add(reglement);
		facture.getReglements().add(reglement);
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
		if(prestationModel.isEmpty()){
			solde = 0d;
			totalmontant = 0d;
			llSolde.setValue(0+"");
			//reglementModel.removeAll(reglementModel.getInnerList());
		}

	}

	/*@Listen("onClick=#btSuppRegl")
	public void supprimerReglement() {
		// prestation = lstPrestation.getSelectedItem().getValue();
		Reglement r = lsReglement.getSelectedItem().getValue();
		solde = solde + r.getMontant();
		llSolde.setValue(solde+"");
		((ListModelList<Reglement>) reglementModel).remove(lsReglement
				.getSelectedIndex());
          if(reglementModel.isEmpty()){
        	  solde = totalmontant;
        	  llSolde.setValue(totalmontant+"");
        	  
          }
	}*/

	/*@Listen("onClick=#btValider")
	public void valider() {
		//Facturation facture = new Facturation();
		//facture.setDateFacture(new Date());
		//facture.setEtat("Non payée");
		//facture.setPatient(patient);
		Set<Prestation> listPrest = new HashSet<Prestation>();
		listPrest.addAll(prestationModel.getInnerList());
		facture.setPrestations(listPrest);
		//Set<Reglement> listRgl = new HashSet<Reglement>();
		//listRgl.addAll(reglementModel.getInnerList());
		facture.setEntree(dateEntree.getValue());
		
		facture.setSortie(dateSortie.getValue());
		facture.setReglements(listRgl);
		facture.setMontantLettre("");
		//facture.setPrestations();
		facture.setModePaiement(cbModePaie.getSelectedItem().getLabel());
		facture.setMontant(totalmontant);
		//facture.setIsDevis(isDevis);
		facture.setIsDeleted(false);
		if(solde == 0){
			facture.setEtat("Payé");
		}
		facture.setSolde(solde);
		FactureFacade ff = (FactureFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.FactureFacade);
		ff.edit(facture);
		Clients.showNotification("Facturation enregistrée");
		Events.postEvent("onUpdateFacture", getSelf().getParent(), facture);
		getSelf().detach();

	}
       */
	@Listen("onClick=#btAnnuler")
	public void onCancel() {
		prestationModel = new ListModelList<Prestation>();
		//reglementModel = new ListModelList<Reglement>();
		dbMontant.setValue(0d);
		txtObservation.setValue("");

	}

	public Comboitem getSelectedMode() {
		return selectedMode;
	}

	public void setSelectedMode(Comboitem selectedMode) {
		this.selectedMode = selectedMode;
	}

}

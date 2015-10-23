package lilas.web.parametre;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lilas.ejb.entity.Parametre;
import lilas.ejb.session.ParametreFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

public class ParametreControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox txtTel,txtAdress,txtRC,txtNinea,txtLogo;
    private File f;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);			
	}
	@Listen("onUpload=#btLogo")
	public void chargerLogo(UploadEvent evt) throws FileNotFoundException{
		try{
			 f  = new File("C:\\projet\\logo\\"+evt.getMedia().getName());
			InputStream inputStream = evt.getMedia().getStreamData();
			txtLogo.setValue(f.getAbsolutePath());
			OutputStream out = new FileOutputStream(f);
			byte buf[] = new byte[1024];
			int len;
			while((len=inputStream.read(buf))>0){
				out.write(buf, 0, len);	
			}
			out.close();
			inputStream.close();
			
		}catch(IOException io){
			io.printStackTrace();
		}
		
	}
	
    @Listen("onClick=#btValide")
    public void valider(){
    	Parametre param = new Parametre();
    	param.setLogo(txtLogo.getValue());
    	param.setNinea(txtNinea.getValue());
    	param.setAdresse(txtAdress.getValue());
    	param.setRc(txtRC.getValue());
    	param.setTelephone(txtTel.getValue());
    	ParametreFacade  pf = (ParametreFacade) JNDIUtils.lookUpEJB(EJBRegistry.ParametreFacade);
    	pf.create(param);
    	alert("Paramètres enregistrés");
     	
    }
}

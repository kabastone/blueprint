package lilas.web.authentification;

import java.io.Serializable;

import lilas.ejb.entity.Utilisateur;
import lilas.ejb.session.UtilisateurFacade;
import lilas.web.outils.EJBRegistry;
import lilas.web.outils.JNDIUtils;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;



// Referenced classes of package sn.pamecas.web.convertexcel.authentification:
//            AuthentificationService, UserInfo

public class AuthentificationServiceImpl
    implements AuthentificationService, Serializable
{

    private UtilisateurFacade uf;
    private static final long serialVersionUID = 0xfcfcd2746fc53af0L;

    public AuthentificationServiceImpl()
    {
        uf = (UtilisateurFacade)JNDIUtils.lookUpEJB(EJBRegistry.UtilisateurFacade);
    }

    public boolean login(String l, String pwd)
    {
        Utilisateur utilisateur = uf.findByLogin(l);
        if(utilisateur == null)
        {
            return false;
        }
        if(!utilisateur.getPassword().equals(pwd))
        {
            return false;
        } else
        {
            UserInfo userInfo = new UserInfo(utilisateur.getNumUtilisateur(), utilisateur.getIdentifiant(), utilisateur.getFonctions(), utilisateur.getMail());
            Session session = Sessions.getCurrent();
            session.setAttribute("userInfo", userInfo);
            return true;
        }
    }

    public void logout()
    {
        Session session = Sessions.getCurrent();
        session.removeAttribute("userInfo");
    }

    public UserInfo getUserInfo()
    {
        Session session = Sessions.getCurrent();
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        if(userInfo == null)
        {
            userInfo = new UserInfo();
            session.setAttribute("userInfo`", userInfo);
        }
        return userInfo;
    }
}

package lilas.web.sidebar;

import java.util.Set;
import java.util.TreeSet;

import lilas.ejb.entity.Acces;
import lilas.ejb.entity.Role;
import lilas.web.authentification.UserInfo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Include;

public class SidebarLeftControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Navbar navbar;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub

		super.doAfterCompose(comp);
		// récupération de l'utilisateur connecté
		UserInfo<?> userInfo = (UserInfo<?>) Sessions.getCurrent()
				.getAttribute("userInfo");
		// récupération du role de l'utilisateur connecté
		if (!(userInfo == null)) {

			if (userInfo.getFonctions().size() == 1) {
				userInfo.setProfil(userInfo.getFonctions().iterator().next());
				Sessions.getCurrent().setAttribute("userInfo", userInfo);
			}
			
			
			if (userInfo.getProfil() != null) {
				Set<Role> roles = new TreeSet<Role>();
				roles = userInfo.getProfil().getRoles();
				buildSidebar(roles);
			}

		}

	}

	private void buildSidebar(final Set<Role> roles) {

		for (final Role r : roles) {
			Nav nav = new Nav();
			if (r.getListAcces().size() == 0) {
				Navitem item = new Navitem();
				item.setLabel(r.getName());
				item.setIconSclass(r.getIcon());

				org.zkoss.zk.ui.event.EventListener<Event> onEventListener = new SerializableEventListener<Event>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -2017287956427058497L;

					@Override
					public void onEvent(Event event) throws Exception {
						// chargement de la page central
						Include include = (Include) Selectors
								.iterable(navbar.getPage(), "#mainInclude")
								.iterator().next();
						include.setSrc(r.getUrlRole());
						if (r.getName() != null) {
							getPage().getDesktop().setBookmark(
									"p_" + r.getName());
						}

					}
				};
				item.addEventListener(Events.ON_CLICK, onEventListener);
				item.setParent(navbar);

			} else {
				for (final Acces a : r.getListAcces()) {

					Navitem item = new Navitem();

					item.setLabel(a.getLibelleAcces());

					org.zkoss.zk.ui.event.EventListener<Event> onEventListener = new SerializableEventListener<Event>() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 858811395952057594L;

						@Override
						public void onEvent(Event event) throws Exception {
							// chargement de la page central
							Include include = (Include) Selectors
									.iterable(navbar.getPage(), "#mainInclude")
									.iterator().next();
							include.setSrc(a.getUrlAcces());
							if (a.getLibelleAcces() != null) {
								getPage().getDesktop().setBookmark(
										"p_" + a.getLibelleAcces());
							}

						}
					};
					item.addEventListener(Events.ON_CLICK, onEventListener);
					// item.setParent(navbar);
					item.setParent(nav);
					nav.setLabel(r.getName());
					nav.setIconSclass(r.getIcon());
					nav.setBadgeText(r.getListAcces().size() + "");
				}
				nav.setParent(navbar);
			}

		}

	}
}

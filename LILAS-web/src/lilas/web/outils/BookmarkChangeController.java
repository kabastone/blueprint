package lilas.web.outils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Include;

import lilas.ejb.entity.Acces;
import lilas.ejb.entity.Role;
import lilas.web.authentification.UserInfo;

public class BookmarkChangeController extends SelectorComposer {

	private static final long serialVersionUID = 1L;
	Session s = Sessions.getCurrent();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (comp.getParent() != null) {
			throw new RuntimeException(
					"A bookmark change listener can only apply on the root comp");
		}

		comp.addEventListener("onBookmarkChange",
				new SerializableEventListener<BookmarkEvent>() {
					private static final long serialVersionUID = 1L;

					public void onEvent(BookmarkEvent event) throws Exception {
						String bookmark = event.getBookmark();
						if (bookmark.startsWith("p_")) {
							String p = bookmark.substring("p_".length());
							UserInfo<?> u = (UserInfo<?>) s
									.getAttribute("userInfo");
							Acces page = new Acces();

							for (Role r : u.getFonctions().iterator().next()
									.getRoles()) {
								for (Acces d : r.getListAcces()) {
									if (p.equals(d.getLibelleAcces())) {
										page = d;
									}
								}
							}

							if (page != null) {
								// use iterable to find the first include only
								Include include = (Include) Selectors
										.iterable(getPage(), "#mainInclude")
										.iterator().next();
								include.setSrc(page.getUrlAcces());
							}
						}
					}
				});
	}

}

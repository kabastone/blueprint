INSERT INTO `acces` VALUES (1,'Liste Facture','/pages/facturation/liste_facture.zul'),
                           (2,'Liste Prestations','/pages/prestations/liste_prestations.zul'),
                           (3,'Liste Utilisateurs','/pages/page_utilisateurs/liste_utilisateur.zul');
                       
INSERT INTO `fonction` VALUES (1,'administrateur'),(2,'Secretaire');

INSERT INTO `fonctionnalites` VALUES (1,'dossier soin',NULL),(2,'dossier administratif',''),(3,'dossier medical',NULL),(4,'profil patient',NULL),(5,'nouveau',NULL),(6,'vuesoin',NULL),(7,'Facturer',NULL);

INSERT INTO `roles` VALUES (1,'Gestion Facturations','','z-icon-th-list'),(2,'Gestion Prestations',NULL,'z-icon-th-list'),(3,'Gestion Utilisateurs',NULL,'z-icon-th-list');

INSERT INTO `roles_acces` VALUES (1,1),(2,2),(3,3);

INSERT INTO `utilisateurs` VALUES (1,'admin','ol','admin','passer','admin'),(2,'kaba','kaba@mail.com','stone','passer','kaba'),(3,'secretaire','ol','secretaire','passer','secretaire');
INSERT INTO `utilisateurs_fonction` VALUES (1,1),(2,1),(3,2);

INSERT INTO `fonction_roles` VALUES (1,1),(1,2),(1,3),(2,1),(2,2);

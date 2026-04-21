public class Main {
// 0 = Case Départ 
// 1 = Propriété
// 2 = Caisse de communauté
// 3 = Impôts
// 4 = Gare
// 5 = Chance
// 6 = Compagnie (Palantír, Pierre du Voir)
// 7 = Case neutre (Prison, Parking, Aller en Prison)

  public static void main(String[] args) {
          Plateau p1 = new Plateau();
          Case c0 = new Case("Départ", 0);
          Case c1 = new Case("Bag End", "marron", 1);
          Case c2 = new Case("Fraternité", 2);
          Case c3 = new Case("Cul-de-Sac", "marron", 1);
          Case c4 = new Case("Impôt de Guerre", 3);
          Case c5 = new Case("Chemin de Bree", 4);
          Case c6 = new Case("Porte de Durin", "bleu clair", 1);
          Case c7 = new Case("L'Anneau", 5);
          Case c8 = new Case("Salle de Balin", "bleu clair", 1);
          Case c9 = new Case("Pont de Khazad-dûm", "bleu clair", 1);
          Case c10 = new Case("Prison (Mordor)", 0);
          Case c11 = new Case("Caras Galadhon", "rose", 1);
          Case c12 = new Case("Palantír", 6);
          Case c13 = new Case("Source de Nimrodel", "rose", 1);
          Case c14 = new Case("Miroir de Galadriel", "rose", 1);
          Case c15 = new Case("Chemin de Rohan", 4);
          Case c16 = new Case("Ents de Fangorn", "orange", 1);
          Case c17 = new Case("Fraternité", 2);
          Case c18 = new Case("Tour Orthanc", "orange", 1);
          Case c19 = new Case("Isengard", "orange", 1);
          Case c20 = new Case("Parking (Rivendell)", 0);
          Case c21 = new Case("Gouffre de Helm", "rouge", 1);
          Case c22 = new Case("L'Anneau", 5);
          Case c23 = new Case("Edoras", "rouge", 1);
          Case c24 = new Case("Plaines du Rohan", "rouge", 1);
          Case c25 = new Case("Chemin de Gondor", 4);
          Case c26 = new Case("Osgiliath", "jaune", 1);
          Case c27 = new Case("Pelennor", "jaune", 1);
          Case c28 = new Case("Pierre du Voir", 6);
          Case c29 = new Case("Minas Tirith", "jaune", 1);
          Case c30 = new Case("Aller en Prison", 0);
          Case c31 = new Case("Ithilien", "vert", 1);
          Case c32 = new Case("Henneth Annûn", "vert", 1);
          Case c33 = new Case("Fraternité", 2);
          Case c34 = new Case("Forteresse de Cirith", "vert", 1);
          Case c35 = new Case("Chemin du Mordor", 4);
          Case c36 = new Case("L'Anneau", 5);
          Case c37 = new Case("Barad-dûr", "bleu foncé", 1);
          Case c38 = new Case("Taxe de Sauron", 3);
          Case c39 = new Case("Montagne du Destin", "bleu foncé", 1);
          p1.ajouterCase(c0);
          p1.ajouterCase(c1);
          p1.ajouterCase(c2);
          p1.ajouterCase(c3);
          p1.ajouterCase(c4);
          p1.ajouterCase(c5);
          p1.ajouterCase(c6);
          p1.ajouterCase(c7);
          p1.ajouterCase(c8);
          p1.ajouterCase(c9);
          p1.ajouterCase(c10);
          p1.ajouterCase(c11);
          p1.ajouterCase(c12);
          p1.ajouterCase(c13);
          p1.ajouterCase(c14);
          p1.ajouterCase(c15);
          p1.ajouterCase(c16);
          p1.ajouterCase(c17);
          p1.ajouterCase(c18);
          p1.ajouterCase(c19);
          p1.ajouterCase(c20);
          p1.ajouterCase(c21);
          p1.ajouterCase(c22);
          p1.ajouterCase(c23);
          p1.ajouterCase(c24);
          p1.ajouterCase(c25);
          p1.ajouterCase(c26);
          p1.ajouterCase(c27);
          p1.ajouterCase(c28);
          p1.ajouterCase(c29);
          p1.ajouterCase(c30);
          p1.ajouterCase(c31);
          p1.ajouterCase(c32);
          p1.ajouterCase(c33);
          p1.ajouterCase(c34);
          p1.ajouterCase(c35);
          p1.ajouterCase(c36);
          p1.ajouterCase(c37);
          p1.ajouterCase(c38);
          p1.ajouterCase(c39);
          System.out.println("Nombre de cases sur le plateau : " + p1.getNombreCases());
          System.out.println("Nom de la case à l'index 5 : " + p1.getCase(5).getType();
        


        );
  }

}

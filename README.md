# Rapport de projet

## 2048

    Le but de ce projet consiste a confectionner le jeu du 2048 en java

### Comment y jouer

    Le jeu du 2048 ce place sur une grille, a chaque mouvement du joueur de nouvelle tuiles s'ajoutent sur la grille en plus des précedentes.

    Ces mêmes tuiles porte une valeur, lorsque 2 tuiles avec la même valeur s'entrechoquent elles fusionnes en additionnant leur valeurs.

    C'est ainsi que le joueur provoque cette mecanique, en glissant toutes les tuiles sur la grille d'un coté ou de l'autre, et aussi bien en haut qu'en bas.

    Le joueur perds lorsque la grille est remplie de tuile et qu'aucune fusion n'est possible.

### Les objectifs

  Fonctionnalités  | Réalisation
  ------------- | -------------
  Interface graphique  | 1 - Grille dans le terminale<br>2 - Interface graphique hors terminal<br>(global) - Score, Nombres de coups, Temps (bonus)
  Mécanique de jeu | - Apparition des tuiles <br>- Mouvement des tuiles<br>- Fusion des tuiles<br>- Condition de victoire / défaite
  Bonus  | - Classement des meilleures parties
<br></br>
> Brahima Dibassi / Quentin Epron

### Lancer Le Programme

- `git clone https://code.up8.edu/BrahimaDibassi/\2048-java`
- `A la racine faire javac $(find . -name '*.java')`
- `Lancer Le fichier Launch`
- `Choisir la Taille de la Grille`
- `Have Fun`

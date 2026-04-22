#Problèmes des Seaux (en Java ) : 

#Introduction : 

Ce programme résout automatiquement des problèmes de type « seaux » en Java. Il lit une instance de
problème, modélise les états nécessaires ou possibles et recherche si une solution est possible ou
non. On va donc ici, implémenter différents algorithmes de parcours afin de les comparer entre eux .

#Structure du Projet :
src/ : code source Java
• App.java : point d’entrée du programme (Main)
• LireFichier.java : lecture du fichier d’instance et traitement
• Problème.java : logique du problème et algorithmes de recherche
• Etat.java : représentation d’un état (configuration des seaux)
• Open.java : gestion des états à explorer
Instances/ : fichiers d’instances de problèmes au format .buck

#Execution et Compilation :
java App.java

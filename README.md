Ensimag 2A POO - TP 2015/16
============================

- src: contient les classes fournies par les enseignants 
  -> TestGUI.java : cree une fenetre graphique "Vide", pas de simulation
  Et c'est tout, en fait!

- bin/gui.jar: archive Java contenant les classes de l'interface graphique. Voir un exemple d'utilisation dans TestGUI.java

- doc_gui: la documentation (API) des classes de l'interface graphique contenues dans gui.jar. Point d'entrée: index.html

- Makefile: quelques explications sur la compilation en ligne, notamment la notion de classpath et l'utilisation de ihm.jar

Execution
=========

* **compilation** : `make jeuxGUI`
* **lancement** : `make exeJeuxGUI`

`make all` permet d'effectuer ces deux opérations à la suite.
`make clean` efface les .class de bin/

*Le menu vous laissera choisir parmis les différents jeuxi et leur options.*

# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testGUI testBalls testBallsSimulator testGUI1 testCellules

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java
testBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src/balles src/balles/TestBalls.java
testBallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src/balles src/balles/TestBallsSimulator.java
testGUI1:
	javac -d bin -classpath bin/gui.jar -sourcepath src/balles src/balles/TestGUI1.java
testCellules:
	javac -d bin -classpath bin/gui.jar -sourcepath src/jeuDeLaVie src/jeuDeLaVie/TestCellules.java
testGUICellules:
	javac -d bin -classpath bin/gui.jar -sourcepath src/jeuDeLaVie src/jeuDeLaVie/TestGUICellules.java
testCellules2:
	javac -d bin -classpath bin/gui.jar -sourcepath src/jeuImmigration src/jeuImmigration/TestCellules2.java
testGUICellules2:
	javac -d bin -classpath bin/gui.jar -sourcepath src/jeuImmigration src/jeuImmigration/TestGUICellules2.java
testHabitations:
	javac -d bin -classpath bin/gui.jar -sourcepath src/segregation src/segregation/TestHabitations.java
testGUIHabitations:
	javac -d bin -classpath bin/gui.jar -sourcepath src/segregation src/segregation/TestGUIHabitations.java
testGUIBoids:
	javac -d bin -classpath bin/gui.jar -sourcepath src/boids src/boids/TestGUIBoids.java

all: testGUI testBalls testBallsSimulator testGUI1 testCellules

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeTestGUI:
	java -classpath bin:bin/gui.jar TestGUI
exeTestBalls:
	java -classpath bin:bin/gui.jar TestBalls
exeTestBallsSimulator:
	java -classpath bin:bin/gui.jar TestBallsSimulator
exeTestGUI1:
	java -classpath bin:bin/gui.jar TestGUI1
exeTestCellules:
	java -classpath bin:bin/gui.jar TestCellules $(ARG1)
exeTestGUICellules:
	java -classpath bin:bin/gui.jar TestGUICellules $(ARG1)
exeTestCellules2:
	java -classpath bin:bin/gui.jar TestCellules2 $(ARG1)
exeTestGUICellules2:
	java -classpath bin:bin/gui.jar TestGUICellules2 $(ARG1)
exeTestHabitations:
	java -classpath bin:bin/gui.jar TestHabitations $(ARG1) $(ARG2)
exeTestGUIHabitations:
	java -classpath bin:bin/gui.jar TestGUIHabitations $(ARG1) $(ARG2)
exeTestGUIBoids:
	java -classpath bin:bin/gui.jar TestGUIBoids $(ARG1)

clean:
	rm -rf bin/*.class


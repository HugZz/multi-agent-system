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
	javac -d bin -classpath bin/gui.jar -sourcepath src/cellules src/cellules/TestCellules.java
testGUICellules:
	javac -d bin -classpath bin/gui.jar -sourcepath src/cellules src/cellules/TestGUICellules.java

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
	java -classpath bin:bin/gui.jar TestCellules
exeTestGUICellules:
	java -classpath bin:bin/gui.jar TestGUICellules

clean:
	rm -rf bin/*.class


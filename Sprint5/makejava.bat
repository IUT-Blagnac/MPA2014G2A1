rem echo off
set SPRINTDIR=%~dp0
set SRCDIR=./src
set BINDIR=./bin

set RUNTEST=1

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION de la librairie
@echo ///////////////////////////////////////////////////////

javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/donnees/*.java
javac -cp .;%BINDIR%;./tools/junit.jar -d %BINDIR% %SRCDIR%/Tests/*.java

@echo ///////////////////////////////////////////////////////
@echo // Exécution des Tests
@echo ///////////////////////////////////////////////////////

if "%RUNTEST%"=="1" (
 cd %BINDIR%
 java -cp .;../bin;../tools/junit.jar junit.textui.TestRunner Tests.EtudiantTest
 java -cp .;../bin;../tools/junit.jar junit.textui.TestRunner Tests.IntervenantTest
 java -cp .;../bin;../tools/junit.jar junit.textui.TestRunner Tests.GroupeTest
 java -cp .;../bin;../tools/junit.jar junit.textui.TestRunner Tests.ProjetTest
 java -cp .;../bin;../tools/junit.jar junit.textui.TestRunner Tests.ParticipationTest
 java -cp .;../bin;../tools/junit.jar junit.textui.TestRunner Tests.SujetTest
 
 cd %SPRINTDIR%
 java -cp .;./bin;./data;./tools/junit.jar junit.textui.TestRunner Tests.IOToolTest

 cd %SPRINTDIR%
)

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION de l'application WEB
@echo ///////////////////////////////////////////////////////

javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/web/MakeOPTIweb.java


@echo ///////////////////////////////////////////////////////
@echo // Création de la page WEB
@echo ///////////////////////////////////////////////////////

java -cp  .;./bin;./data; web.MakeOPTIweb



@echo ///////////////////////////////////////////////////////
@echo // COMPILATION de l'IHM
@echo ///////////////////////////////////////////////////////

javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/Intervenant.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/AssProjet.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/SaisieVoeux.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/Etudiant.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/Projet.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/Groupe.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/Sujet.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/APropos.java
javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/interfacv1/InterfaceLog.java



@echo ///////////////////////////////////////////////////////
@echo // EXECUTION de l'IHM
@echo ///////////////////////////////////////////////////////

java -cp  .;./bin;./data; interfacv1/InterfaceLog




pause
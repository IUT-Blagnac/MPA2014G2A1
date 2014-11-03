rem echo off
set ASCIIDOCDIR=.\tools\asciidoc869\
set PLANTUMLDIR=.\tools\
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin/
set SRCDOCDIR=./srcdoc/
set DOCDIR=./doc/

set MAKETEST=1

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des executables
@echo ///////////////////////////////////////////////////////
javac -d %BINDIR% %SRCDIR%HelloMonde.java

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations
@echo ///////////////////////////////////////////////////////
java -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %SRCDOCDIR%images %SRCDOCDIR%diag0.puml

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -a iconsdir=../%ASCIIDOCDIR%/images/icons -o %SRCDOCDIR%docUtilisateurSprint0.html %SRCDOCDIR%docUtilisateurSprint0.txt

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des tests
@echo ///////////////////////////////////////////////////////
if "%MAKETEST%"=="1" (
 javac -cp .;./tools/junit.jar -d %BINDIR% %SRCDIR%HelloMondeTest.java
)


@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests
@echo ///////////////////////////////////////////////////////
if "%MAKETEST%"=="1" (
 cd %BINDIR%
 java -cp .;../tools/junit.jar HelloMondeTest
 cd %SPRINTDIR%
)
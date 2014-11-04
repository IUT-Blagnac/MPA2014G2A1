rem echo off
set ASCIIDOCDIR=.\tools\asciidoc869\
set PLANTUMLDIR=.\tools\
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin/
set SRCDOCDIR=./srcdoc/
set DOCDIR=./doc/
set PYTHONDIR=c:\Python27\

set MAKETEST=1
set docUtil=docUtilisateurSprint4
set docTech=docTechniqueSprint4


@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations
@echo ///////////////////////////////////////////////////////

%PYTHONDIR%python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -a iconsdir=../%ASCIIDOCDIR%/images/icons -o %SRCDOCDIR%%docUtil%.html %SRCDOCDIR%%docUtil%.txt
%PYTHONDIR%python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -a iconsdir=../%ASCIIDOCDIR%/images/icons -o %SRCDOCDIR%%docTech%.html %SRCDOCDIR%%docTech%.txt
pause
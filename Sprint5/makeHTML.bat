rem echo off
set SPRINTDIR=%~dp0
set SRCDIR=./src
set BINDIR=./bin

set RUNTEST=1

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION de la librairie
@echo ///////////////////////////////////////////////////////

javac -cp .;%BINDIR% -d %BINDIR% %SRCDIR%/web/MakeOPTIweb.java

)
pause
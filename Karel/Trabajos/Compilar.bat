@echo off
echo Compilando...
del *.class
javac -d . -cp ".;KarelJRobot.jar" MiPrimerRobot.java Racer.java
pause
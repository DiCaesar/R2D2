echo off 
echo [INFO]������汾��: 
set /p newVersion= 
echo [INFO]����: %newVersion% ��ʼ�滻...
cd ../
call mvn clean versions:set -DnewVersion=%newVersion%
pause
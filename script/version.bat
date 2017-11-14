echo off 
echo [INFO]请输入版本号: 
set /p newVersion= 
echo [INFO]输入: %newVersion% 开始替换...
cd ../
call mvn clean versions:set -DnewVersion=%newVersion%
pause
Taskkill /fi "IMAGENAME eq  %JOB_NAME%" /f
copy "%JAVA_HOME%"\bin\java.exe "%JAVA_HOME%"\bin\%JOB_NAME%.exe
%JOB_NAME% -jar .\%JOB_NAME%\build\libs\%JOB_NAME%-1.0.jar


gradle :%JOB_NAME%:clean :%JOB_NAME%:bootRepackage

Taskkill /fi "IMAGENAME eq api-server-*" /f
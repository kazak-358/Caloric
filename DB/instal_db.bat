REM Not asking for any key!
@ECHO OFF

if NOT "%1"=="" goto OK

@ECHO USAGE:
@ECHO   instal_db ConnStr 
@ECHO WHERE:
@ECHO   ConnStr - Connect String
@ECHO EXAMPLE:
@ECHO   INSTAL_DB USER/PASS@ORCL 
exit

:OK

title Install DB...
@ECHO Install DB...

sqlplus %1 @instal.sql

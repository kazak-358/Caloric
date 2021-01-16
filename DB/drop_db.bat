REM Not asking for any key!
@ECHO OFF

if NOT "%1"=="" goto OK

@ECHO USAGE:
@ECHO   drop_db ConnStr 
@ECHO WHERE:
@ECHO   ConnStr - Connect String 
@ECHO EXAMPLE:
@ECHO   DROP_DB USER/PASS@ORCL 
exit

:OK

title Drop DB...
@ECHO Drop DB...

sqlplus %1 @drop.sql

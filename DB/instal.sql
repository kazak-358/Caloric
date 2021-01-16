prompt Install DB

spool create.log
prompt Create tables
prompt

@create
spool off

spool generate.log
prompt Generate data
prompt

@insert
spool off

exit

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

spool create_trigger.log
prompt Generate data
prompt

@create_trigger
spool off

exit

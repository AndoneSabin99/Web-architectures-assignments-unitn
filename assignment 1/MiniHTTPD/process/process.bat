cls
set text=Run http://localhost:8000/process/reverse?par1=string on your browser to get the reverse of string parameter
echo
set fn=Documents\process.html
echo
del %fn%
echo %text% >>%fn%
exit
cls
set text=The reversed string is: %1
echo
set fn=Documents\reverse.html
echo
del %fn%
echo %text% >>%fn%
exit
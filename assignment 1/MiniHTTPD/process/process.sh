#!/bin/bash
touch Documents/process.html

cat > Documents/process.html << EOF

<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
    <p>Run http://localhost:8000/process/reverse?par1=string on your browser to get the reverse of string parameter</p>
  </body>
</html>

EOF
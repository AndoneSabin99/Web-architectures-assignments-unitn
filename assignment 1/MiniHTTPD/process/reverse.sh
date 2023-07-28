#!/bin/bash
touch Documents/reverse.html

cat > Documents/reverse.html << EOF

<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
    <p>The reversed string is: $1</p>
  </body>
</html>

EOF
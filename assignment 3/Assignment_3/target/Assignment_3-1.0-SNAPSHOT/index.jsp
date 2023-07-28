<%@ page import="it.unitn.andone.Spreadsheet.SSEngine" %>
<%@ page import="it.unitn.andone.Spreadsheet.Cell" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mini SpreadSheet</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        table, th, td {
            border: 1px solid black;
            height: 36px;
        }
    </style>

</head>
<body>
<!-- creating the engine -->
<%
    SSEngine engine = SSEngine.getSSEngine();
    application.setAttribute("engine", engine);
%>
<div class="container">
    <!-- input text field -->
    <h1 class="bg-success text-light">Mini SpreadSheet</h1>
    <input name="txtField" id="txtField" type="text">

    <!-- grid -->
    <table class="table table-sm">
        <%
            for (int i = 1; i<= engine.getRows(); i++){
        %>
        <tr>
            <%
                for (String a : engine.getColumns()) {
                    String id=a+i;
            %>
            <td id=<%=id%>></td>
            <%
                    }
                }
            %>
        </tr>
    </table>
</div>



<!-- JS -->
<script src="./resources/cells.js"></script>
<script>

    //call this function when the page is loaded
    $(document).ready(function() {

        //when we load the page, the first cell is selected by default
        $("#A1").css('background-color', '#F0FFF0');
        $("#A1").css('border-color', '#008800');
        $("#A1").css('border-width', 2 +'px');
        let url = "HighlightServlet?idCell=A1";
        callHighLightServlet(url);

        //we need to load all the cells the first time
        loadAllCells();
    });

    //call this function when a key is pressed while we write on the input text field
    $('#txtField').on('keyup', function(){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        //keycode == 13 is the keycode for Enter key
        if(keycode == '13'){
            callModifyCellServlet($(this).val());
            //we use blur here because otherwise, when we click somewhere else we would
            //call again the callModifyCellServlet function due to the fact that it is
            //also called on focusout of the #txtField
            $(this).blur();
        }
        else {
            var txtValue = $(this).val();
            var idCell = "#" + currentCell.getId();
            $(idCell).text(txtValue);
        }
    })

    //call this function when we select a cell
    $('td').on('click', function(){

        $('td').css('background-color', '');
        $(this).css('background-color', '#F0FFF0');
        $('td').css('border-color', '#000000');
        $(this).css('border-color', '#008800');
        $('td').css('border-width', 1 +'px');
        $(this).css('border-width', 2 +'px');

        let url = "HighlightServlet?idCell=" +  this.id;
        callHighLightServlet(url);

    })

    //call this function when we click on the input text field
    $('#txtField').on('focusin', function(){
        var idCell = "#" + currentCell.getId();
        $(idCell).text(currentCell.getFormula());
    })

    //call this function when after we finish to write the formula and we click somewhere else
    $('#txtField').on('focusout', function(){
        callModifyCellServlet($(this).val());
    })

</script>
</body>
</html>
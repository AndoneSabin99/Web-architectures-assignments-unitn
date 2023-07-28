//cell Class
class Cell{
    constructor(id, formula, value) {
        this.id = id;
        this.formula = formula;
        this.value = value;
    }

    getId() {
        return this.id;
    }

    getFormula() {
        return this.formula;
    }

    getValue() {
        return this.value;
    }
}

var currentCell = null;

//call this function to load all the cells from the SSEngine
function loadAllCells() {
    var xhttp = new XMLHttpRequest();
    xhttp.responseType = "json";
    xhttp.onreadystatechange = function() {
        if(this.readyState===4 && this.status===200) {
            var list = this.response;
            updateGrid(list);
        }
    };
    var url = "GetAllCellsServlet";
    xhttp.open("GET", url, true);
    xhttp.send();
}

//function that updates the grid
function updateGrid(list){
    for(var i=0; i<list.length; i++) {
        var cellId = "#" + list[i].id;
        if (list[i].formula != null && list[i].formula != ""){
            $(cellId).text(list[i].value);
            if (currentCell.id == list[i].id){
                currentCell = new Cell(list[i].id, list[i].formula, list[i].value);
            }
        }
    }
}

//function that is called when we need to modify a cell
function callModifyCellServlet(txtVal){
    var txtFieldValue = txtVal;
    var time = new Date().getTime();
    time = Math.round(time/1000);
    let url = "ModifyCellServlet?value=" + txtFieldValue + "&t=" + time;

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        var done = 4, ok = 200;
        if (this.readyState === done && this.status === ok){
            var list = this.response;
            updateGrid(list);
        }
    };
    xhttp.send();
}

//function called when we select a cell in the grid
function callHighLightServlet(url){
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        var done = 4, ok = 200;
        if (this.readyState === done && this.status === ok){
            var jsonObj = this.response;
            currentCell = new Cell(jsonObj.idCell, jsonObj.formulaCell, jsonObj.valueCell);
            $("#txtField").val(currentCell.getFormula());
        }
    };
    xhttp.send();
}

//call this function every 0.25 seconds in order to get the updated grid in real time
//Note that 0.25 are enough to guarantee that the modifications will be propagated to all users
setInterval(checkGrid, 250);
function checkGrid() {
    //Make AJAX request
    var xhttp = new XMLHttpRequest();
    xhttp.responseType = "json";
    xhttp.onreadystatechange = function() {
        if(this.readyState===4 && this.status===200) {
            var res = this.response;
            if (res.up == "true"){
                console.log("Proceed with the loadAllCells")
                loadAllCells();
            }else{
                console.log("No modifications happened so far...");
            }
        }
    };
    var time = new Date().getTime();
    time = Math.round(time/1000);
    var url = "CheckGridServlet?t=" + time;
    xhttp.open("GET", url, true);
    xhttp.send();
}
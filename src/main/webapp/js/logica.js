
function cargarApp() {
  const xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
    document.getElementById("cont_app").innerHTML = 
    this.responseText;
  }
  
  xhttp.open("POST", "run");
  xhttp.send();
}


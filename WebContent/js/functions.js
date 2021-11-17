
// Script JavaScript per far vedere solo i parametri di ricerca del tipo di locazione cliccato
function visualizza(id, id1, id2, id3, id4) {
    document.getElementById(id1).style.display = 'none';
    document.getElementById(id2).style.display = 'none';
    document.getElementById(id3).style.display = 'none';
    document.getElementById(id4).style.display = 'none';

    if (document.getElementById) {
        if (document.getElementById(id).style.display == 'none')
            document.getElementById(id).style.display = 'block';
        else
            document.getElementById(id).style.display = 'none';
    }
}


//funzioni per Area viaggiatore
function visualizzaPerViaggiatore(id, id1, id2, id3, id4,btnStandard, btnAvanzata,formAvanzataAttuale,formAvanzata1,formAvanzata2,formAvanzata3,formAvanzata4) {

    if (document.getElementById) {
        if (document.getElementById(id).style.display == 'none' && document.getElementById(btnAvanzata).style.display == 'none' ) {
            document.getElementById(id).style.display = 'block';
            document.getElementById(btnStandard).style.display='block';
            document.getElementById(btnAvanzata).style.display = 'block';


            //nascondi tutti gli altri form
            document.getElementById(id1).style.display = 'none';
            document.getElementById(id2).style.display = 'none';
            document.getElementById(id3).style.display = 'none';
            document.getElementById(id4).style.display = 'none';
            document.getElementById(formAvanzataAttuale).style.display = 'none';
            document.getElementById(formAvanzata1).style.display = 'none';
            document.getElementById(formAvanzata2).style.display = 'none';
            document.getElementById(formAvanzata3).style.display = 'none';
            document.getElementById(formAvanzata4).style.display = 'none';
        }
        else {
            if(document.getElementById(btnAvanzata).value =="standard")
                document.getElementById(btnAvanzata).value ="avanzata";
            else
                document.getElementById(btnAvanzata).value ="standard";

            document.getElementById(id).style.display = 'none';
            document.getElementById(btnAvanzata).style.display = 'none';
            document.getElementById(formAvanzataAttuale).style.display = 'none';
        }
    }
}
function visualizzaRicercaAvanzata(bottoneRicerca,bottoneAvanzata,formStandard, formAvanzata) {
    if(document.getElementById) {
        if(document.getElementById(bottoneAvanzata).value =="avanzata")
            document.getElementById(bottoneAvanzata).value ="standard";
        else
            document.getElementById(bottoneAvanzata).value ="avanzata";

        if (document.getElementById(formAvanzata).style.display == 'none') {
            document.getElementById(formAvanzata).style.display = 'block';
            document.getElementById(bottoneRicerca).style.display = 'none';
            document.getElementById(formStandard).style.display = 'none';
        } else {
            document.getElementById(formStandard).style.display = 'block';
            document.getElementById(formAvanzata).style.display = 'none';
            if (document.getElementById(bottoneRicerca).style.display = 'none')
                document.getElementById(bottoneRicerca).style.display = 'block';
        }
    }
}
function nascondiBtnAvanzata(btn1,btn2,btn3,btn4){
    if(document.getElementById) {
        document.getElementById(btn1).style.display = 'none';
        document.getElementById(btn2).style.display = 'none';
        document.getElementById(btn3).style.display = 'none';
        document.getElementById(btn4).style.display = 'none';
    }
}

/*  Script JavaScript per cambiare la lingua della pagina corrente
 *  it -> IT, italiano
 *  en -> EN, english
 */
function changeLang(box, us, psw, pref) {
    var oldURL = window.location.href;
    var lang = String(box[box.selectedIndex].value);
    var paramsToSend = "";
    var newURL = "";

    var pos = oldURL.lastIndexOf("lang");
    if (pos != -1)
        newURL = oldURL.substring(0, pos - 1);
    if (arguments.length < 2) {
        paramsToSend += "lang=" + lang;
    } else {
        paramsToSend += "lang=" + lang + "&";
    }

    // Per mantenere l'utente loggato durante il cambio di lingua
    var params = ["username", "password", "pref"];
    for (var i = 1; i < arguments.length; ++i) {
        if (arguments[i] != null) {
            paramsToSend += params[i - 1] + "=" + arguments[i];
            if (arguments[i + 1] != null)
                paramsToSend += "&";
        }
    }

    var http = new XMLHttpRequest();
    http.open("POST", newURL, true);

    // Invia informazioni di header con la richiesta
    http.setRequestHeader("Content-type",
        "application/x-www-form-urlencoded");

    // Per sapere quando c'è un cambiamento di stato
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            // Per sostituire URL
            document.open();
            document.write(http.responseText);
            window.history.pushState({"html":http.html, "pageTitle":http.pageTitle}, "URL", newURL);
            document.close();
        }
    };

    http.send(paramsToSend);
}


function visualizzaRecensioni(numRecensioni){
    var r = document.getElementsByClassName("recensioni");
    if(document.getElementById) {
        for (var a = 0; a< numRecensioni; ++a) {
            if (r[a].style.display == 'none') {
                r[a].style.display = 'block';
            } else {
                r[a].style.display = 'none';
            }
        }
    }

}


function visualizzaRimozione(num) {

    var x = document.getElementsByClassName("btnRimozione");
    if (document.getElementById) {
        for (var i = 0; i < num; ++i) {
            if (x[i].style.display == 'none') {
                x[i].style.display = 'block';
            } else {
                x[i].style.display = 'none';
            }
        }

    }
}


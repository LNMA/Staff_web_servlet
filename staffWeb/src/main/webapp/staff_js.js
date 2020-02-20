function disable() {
    var lastName = document.getElementById("2");
    lastName.setAttribute("disabled", "");
    var firstName = document.getElementById("3");
    firstName.setAttribute("disabled", "");
    var mi = document.getElementById("4");
    mi.setAttribute("disabled", "");
    var add = document.getElementById("5");
    add.setAttribute("disabled", "");
    var city = document.getElementById("6");
    city.setAttribute("disabled", "");
    var status = document.getElementById("7");
    status.setAttribute("disabled", "");
    var telephone = document.getElementById("8");
    telephone.setAttribute("disabled", "");
    var email = document.getElementById("9");
    email.setAttribute("disabled", "");
}

function enable() {
    var lastName = document.getElementById("2");
    lastName.removeAttribute("disabled");
    var firstName = document.getElementById("3");
    firstName.removeAttribute("disabled");
    var mi = document.getElementById("4");
    mi.removeAttribute("disabled");
    var add = document.getElementById("5");
    add.removeAttribute("disabled");
    var city = document.getElementById("6");
    city.removeAttribute("disabled");
    var status = document.getElementById("7");
    status.removeAttribute("disabled");
    var telephone = document.getElementById("8");
    telephone.removeAttribute("disabled");
    var email = document.getElementById("9");
    email.removeAttribute("disabled");
}
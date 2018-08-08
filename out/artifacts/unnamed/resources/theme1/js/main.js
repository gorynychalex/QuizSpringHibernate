// jQuery(document).ready(function($) {
//
//     $('#msg').prepend("This is statistic for user : ")
//
// });


//Create link bootstrap
var link = document.createElement("link");

link.setAttribute("href","/resources/theme1/css/bootstrap.min.css");

link.setAttribute("rel", "stylesheet");

document.head.appendChild(link);


//Create head div

var div = document.createElement("div");

div.setAttribute("class","container");

div.style.background="darkcyan";

div.style.color = "lightcyan";

var p = document.createElement("p");

var node = document.createTextNode("Quiz System");

p.appendChild(node);

div.appendChild(p);

document.body.insertBefore(div, document.body.firstChild);


var arrFib = [];
for (var i=1; i<12; i++) {
    arrFib[i]=fib(i);
}
var table = document.createElement("table");
for (var i=1; i<=10; i++) {
    var tr = document.createElement("tr");
    for (j=1; j<=10; j++) {
        var td = document.createElement("td");
        var result = i*j;

        var text = document.createTextNode(result.toString());
        td.appendChild(text);
        tr.appendChild(td);
        for (var k=1; k<12; k++) {
            if (result===arrFib[k]) {
                td.style.backgroundColor = "red";
                break;
            }
            else {
                td.style.backgroundColor = "blue";
            }
        }
    }
    table.appendChild(tr);
}
document.body.appendChild(table);
function fib(n) {
    if (n==1) {
        return 1;
    }
    else if (n==2) {
        return 1;
    }
    else {
        return fib(n-2) + fib(n-1);
    }
}
console.log(arrFib);
var hui = document.getElementById("javaLinked");
var text = document.createTextNode("huiiiii");
// hui.appendChild();
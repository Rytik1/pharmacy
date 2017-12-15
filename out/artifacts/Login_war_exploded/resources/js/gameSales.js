function getObj(objID) {
    if (document.getElementById) {
        return document.getElementById(objID);
    }
    else if (document.all) {
        return document.all[objID];
    }
    else if (document.layers) {
        return document.layers[objID];
    }
}

cobj = getObj("button1");
var x1 = 300, y1 = 300;

function moveIt() {
    x = Math.floor(Math.random() * 401);
    y = Math.floor(Math.random() * 301);
    while (Math.abs(x1 - x) <= 70) {
        x = Math.floor(Math.random() * 401);
    }
    while (Math.abs(y1 - y) <= 50) {
        y = Math.floor(Math.random() * 301);
    }
    x1 = x;
    y1 = y;
    cobj.style.left = x + "px";
    cobj.style.top = y + "px";
}





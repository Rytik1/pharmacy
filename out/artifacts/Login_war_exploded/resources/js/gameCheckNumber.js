function gameCheckNumber() {
    try {
        var message, x, s;
        var count = document.getElementById("counter").value;
        var numb = document.getElementById("number").value;
        input = document.getElementById("number");

        if (0 === numb.length) {
            numb = Math.round(Math.random() * 10);
        }

        number.value = numb;
        message = document.getElementById("message");
        message.innerHTML = "";
        x = document.getElementById("enter").value;

        if (count == 5) {
            count = 0;
            counter.value = 0;
            tempNumb = numb;
            numb = Math.round(Math.random() * 10);
            number.value = numb;
            throw "The attempts ended it was " + tempNumb;
        }

        if (x == "") {
            count++;
            counter.value = count;
            throw "Empty number";
        }
        if (isNaN(x)) {
            count++;
            counter.value = count;
            throw "is not a number";
        }
        x = Number(x);
        count = Number(count);
        if (x > numb) {
            count++;
            counter.value = count;
            throw "is too high";
        }
        if (x < numb) {
            count++;
            counter.value = count;
            throw "is too low";
        }
        count = document.getElementById("counter").value;

        if (x == numb) {
            numb = Math.round(Math.random() * 10);
            number.value = numb;
            count = 0;
            counter.value = 0;
            throw  "<h3>You are win!</h3><br> <button   style=' background-color: initial;  border-radius: 74px;' >" +
            " <img src=../../resources/image/sale10.png style='width: 200'  alt='скидка'   > ";
        }
    }
    catch (err) {
        message.innerHTML = err;
    }
    finally {
        document.getElementById("enter").value = "";
    }
}
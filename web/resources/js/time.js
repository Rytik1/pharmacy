$(document).ready(function () {
    function time() {
        Data = new Date();
        Hour = Data.getHours();
        Minutes = Data.getMinutes();
        Seconds = Data.getSeconds();
        if (Seconds / 10 < 0.9) {
            Seconds = "0" + Data.getSeconds();

        }
        if (Minutes / 10 < 0.9) {
            Minutes = "0" + Data.getMinutes();

        }
        if (Hour / 10 < 0.9) {
            Hour = "0" + Data.getHours();

        }
        document.getElementById('time').innerHTML =
            Hour + ":" + Minutes + ":" + Seconds;
    }

    setInterval(time, 1000);

    $("#but1").click(function () {
        $("#time").toggle();
    });

});

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function uploadAlarm() {

    let form = $("#HtmlForm")[0];
    const data = new FormData(form);

    $.ajax({
        type: "POST",
        url: "/Upload/doUpload",
        data: data,
        contentType : false,
        processData : false,
        success: function (PageUri) {
            stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#movieName").val()}));
            window.location.replace(PageUri);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });

}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    // $( "#connectBtn" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    // $( "#send" ).click(function() { uploadAlarm(); });
});


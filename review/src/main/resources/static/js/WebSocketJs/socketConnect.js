let webSocket;
window.onload = function connect() {
    console.log("연결중");
    webSocket = new WebSocket("ws://localhost:8123/ConnectionUri");
    webSocketEvent();
}

function webSocketEvent() {
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;
}

function onMessage(data) {
    let msg = data.data;
    alert("data 왔음"+JSON.stringify(msg));
    let elementById = document.getElementById("greetings");
    elementById.innerHTML = elementById.innerHTML + "</br>" + msg;
}

function onClose() {

}

function onOpen() {
    // 여기에 연결시작하면서 값 받아올때 찍어준것들 초기화시켜야될듯
}

function send(newMovieName) {
    alert("업로드한 영화 제목 ="+newMovieName)
    webSocket.send(newMovieName);
}

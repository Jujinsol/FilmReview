let i = 0;
let tags = {
    'naturalTag': 0,
    'newTag': 0,
    'FigureMovieName': 0,
    'pizzaImgArea': 0
}


for (let i2 = 0; i2 < newVar.length; i2++) {
    eval("tags.newTag=" + "i")
    eval("tags.naturalTag=" + "i")
    eval("tags.pizzaImgArea=" + "i")
    eval("tags.FigureMovieName=" + "i")


    tags.FigureMovieName = document.getElementById("movieNames");
    tags.FigureMovieName.setAttribute("id", tags.FigureMovieName)
    // let elementById = document.getElementById("movieNames");
    // elementById.setAttribute("id", tags.movieNames);


    $(tags.FigureMovieName).append('<div id="' + tags.pizzaImgArea + '"></div>')


    console.log("부모태그" + tags.naturalTag)
    console.log("새로운태그" + tags.newTag)
    console.log("피자영역태그" + tags.pizzaImgArea)

    tags.naturalTag = document.getElementById(tags.pizzaImgArea);
    tags.newTag = document.createElement("Img");

    tags.newTag.setAttribute("id", "pizzaShapes");
    tags.newTag.setAttribute("src", newVar[i]);

    tags.naturalTag.appendChild(tags.newTag);
    console.log("반복종료")
    ++i;
}
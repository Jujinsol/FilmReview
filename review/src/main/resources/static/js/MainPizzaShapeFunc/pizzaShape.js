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


    $(tags.FigureMovieName).append('<div id="' + tags.pizzaImgArea + '"></div>')


    tags.naturalTag = document.getElementById(tags.pizzaImgArea);
    tags.newTag = document.createElement("Img");

    tags.newTag.setAttribute("id", "pizzaShapes");
    tags.newTag.setAttribute("src", newVar[i]);

    tags.naturalTag.appendChild(tags.newTag);
    ++i;
}
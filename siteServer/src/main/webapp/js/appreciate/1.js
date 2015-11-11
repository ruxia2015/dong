function $$(id){
    return document.getElementById(id);
}

function pageLoad(){
    var cnv = $$("cnvMain");
    var cxt = cnv.getContext("2d");
    cxt.fillStyle = "#ccc";
    cxt.fillRect(330,30,80,80);
    cxt.strokeRect(330,150,80,80);


}
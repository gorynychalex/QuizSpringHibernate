/**
 * File (image) upload handler script
 */

var fileup = document.getElementById('fileup');

var fileurl = document.getElementById('fileurl');

var fileInput = document.getElementById("fileupload");

var log = document.getElementById('log');

var inputText = document.getElementById("inputtext");
var outText = document.getElementById("outtext");



inputText.addEventListener("input", function() {
    outText.innerHTML = inputText.value;
});


fileInput.addEventListener("change", function () {

    var file = this.files[0];

    var fd = new FormData();

    fd.append("file", file);

    var xhr = new XMLHttpRequest();

    xhr.open('POST', '/file/upload1', true);

    xhr.upload.addEventListener('progress', onprogressHandler, false);

    xhr.addEventListener('readystatechange', onreadystatechangeHandler, false);

    xhr.onload = function () {
       if (this.readyState != 4) return;


       if(this.status == 200){

            console.log(this.response);

            // var resp = JSON.parse(this.response);
            var resp = this.response;

            var image = document.createElement('img');

            image.src = resp;

            document.body.appendChild(image);

            // fileurl.innerHTML = "success load";
        };
    };

    xhr.send(fd);


});

function onprogressHandler(evt) {
    var percent = evt.loaded/evt.total*100;
    log.innerHTML = 'Upload progress: ' + percent +'%';
}

function onloadfile(fileurl) {

    // if (this.readyState != 4) return;

    // if(this.status == 200){
        // var resp = JSON.parse(this.response);
        var resp = this.response;

        var image = document.createElement('img');

        image.src = resp;

        document.body.appendChild(image);

        // fileurl.innerHTML = resp;
    // }
}

function onreadystatechangeHandler(evt) {

    var status, text, readyState;

    try{
        readyState = evt.target.readyState;
        text = evt.target.responseText;
        status = evt.target.status;
    } catch (e){
        return;
    }

    if(readyState == 4 && status == 200 && text){
        fileurl.innerHTML = text;
    }
}

function previewFile() {

    //Var at INPUT tag
    var file = document.getElementById('inputimgprev').files[0];

    //FileUpload image
    var fileupload = document.getElementById('fileupload');
    // var preview = document.getElementById('preview');

    //Var for IMAGE SMALL
    var filethumb = document.getElementById('filethumb');
    var previewthumb = document.getElementById('previewthumb');

    //Var for READER
    var reader = new FileReader;

    reader.onloadend = function (event) {
        //Result to Preview window
        // preview.src = reader.result;

        console.log("file.name = " + file.name);
        console.log("file.valueOf().type = " + file.valueOf().type);
        console.log("file.valueOf().size = " + file.valueOf().size);

        fileurl.value = file.name;

        //Get less Image
        // var img1=new Image();
        convertBase64(event, 640, function (dat) {
            fileupload.value = dat;
            // preview.src=dat;
        },file.valueOf().type);

        //Get Thumb
        // var imgthumb = new Image();
        convertBase64(event, 150, function (dat) {
            filethumb.value = dat;
            previewthumb.src = dat;
        },file.valueOf().type);
    };

    if(file){
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
}

function convertBase64(event, max, cb, imagetype) {

        console.log("New image prepare")
        var image = new Image();

        image.onload = function () {

            var canvas = document.createElement('canvas');
            var octx = canvas.getContext('2d');

            if(image.width > max || image.height > max){


                if(image.width > image.height){
                    canvas.height = (image.height/image.width) * max;
                    canvas.width = max;
                } else {
                    canvas.width = (image.width/image.height) * max;
                    canvas.height = max;
                }

                // octx.drawImage(image, 0, 0);
                octx.drawImage(image, 0, 0, canvas.width, canvas.height);
            }
            cb(canvas.toDataURL(imagetype));
        };

        image.src = event.target.result;
};
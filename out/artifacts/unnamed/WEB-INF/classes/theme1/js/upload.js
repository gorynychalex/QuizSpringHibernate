/**
 * File (image) upload handler script
 */

var fileup = document.getElementById('fileup');

var fileurl = document.getElementById('fileurl');

var fileInput = document.getElementById("fileupload");

var log = document.getElementById('log');

var inputText = document.getElementById("inputtext");
var outText = document.getElementById("outtext");

fileInput.addEventListener('change', function () {

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

    //Var at IMG tag
    var preview = document.getElementById('imgprev');

    //Var for reader
    var reader = new FileReader;

    reader.onloadend = function () {
        preview.src = reader.result;
        fileurl.innerHTML = file.name;
    }

    if(file){
        fileup.onchange = reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
}


inputText.addEventListener("keyup", function() {
    outText.innerHTML = inputText.value;
});
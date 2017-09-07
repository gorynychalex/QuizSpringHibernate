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

    var filethumb = document.getElementById('filethumb');

    //Var at IMG tag
    // var preview = document.getElementById('imgprev');

    //Var for IMAGE SMALL
    var previewsmall = document.getElementById('imgprevsmall');

    //Var for READER
    var reader = new FileReader;

    reader.onloadend = function (event1) {
        //Result to Preview window
        // preview.src = reader.result;

        // console.log(file.valueOf().type);

        fileurl.value = file.name;
        // filethumbname.value = "thumb_" + file.name;

        var max = 150;

        var img = new Image();
        resizeImage(img, max, function (dat) {
            previewsmall.src = dat;
            filethumb.value = dat;
        },file.valueOf().type);
        img.src = event1.target.result;
    };

    if(file){
        fileup.onchange = reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
}

function resizeImage(img1, max, cb, imagetype) {

        var image = img1;
        image.onload = function () {
            if(image.width > max){
                var oc = document.createElement('canvas');
                var octx = oc.getContext('2d');

                // oc.width = image.width;
                // oc.height = image.height;

                // octx.drawImage(image, 0, 0);

                if(image.width > image.height){
                    oc.height = (image.height/image.width) * max;
                    oc.width = max;
                } else {
                    oc.width = (image.width/image.height) * max;
                    oc.height = max;
                }

                // octx.drawImage(oc, 0, 0, oc.width, oc.height);
                octx.drawImage(image, 0, 0, oc.width, oc.height);
            }
            cb(oc.toDataURL(imagetype));
        };
        // image.src = event.target.result;
};
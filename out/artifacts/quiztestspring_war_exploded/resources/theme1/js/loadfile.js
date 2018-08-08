/**
 * Created by gorynych on 06.08.17.
 */

function log(html) {
    document.getElementById('log').innerHTML = html;
}

document.forms.formaddfile.onsubmit = function () {
    var input = this.elements.file;
    var file = input.files[0];
    if(file) { upload(file); }
    return false;
}

function uploadForm(file) {

    var xhr = new XMLHttpRequest();

    var formData = new FormData();
    formData.append("file",file);
    xhr.send(formData);
}

function upload(file) {
    var xhr = new XMLHttpRequest();

    xhr.upload.onprogress = function (p1) {
        log(event.loaded + '/' + event.total);
    }

    xhr.onload = xhr.onerror = function () {
        if(this.status == 200){
            console.log("success");
        } else {
            console.log("error " + this.status);
        }
    }

    xhr.open("POST", "/upload1", true);

    xhr.send(file);
}
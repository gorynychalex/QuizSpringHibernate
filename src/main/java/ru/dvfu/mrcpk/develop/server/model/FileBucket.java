package ru.dvfu.mrcpk.develop.server.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gorynych on 03.08.17.
 */

public class FileBucket {
    MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}

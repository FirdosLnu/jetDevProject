package com.jetdev.app.services;

import com.jetdev.app.model.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface FileService {

    public String processFile(MultipartFile file);

    public Response getFile(String fileName, String auth);

    public Response getAllFile();


    public Response deleteFile(String fileName);



}

package com.jetdev.app.controller;


import com.jetdev.app.model.Response;
import com.jetdev.app.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public Response upload(@RequestParam("file") MultipartFile file) {
        Response response = new Response();

        try {
            byte[] bytes = file.getBytes();
            fileService.processFile(file);
            response.setMsg("You successfully uploaded '" + file.getOriginalFilename() + "'");
            response.setStatus(200l);
        } catch (Exception e) {
            response.setStatus(500l);
            log.error("Error while uploading file: {}", e.getMessage());

            response.setMsg("Error while uploading file'"+file.getOriginalFilename());
        }
        return response;
    }

    @GetMapping(path = "/get/{fileName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    private Response getFile(@PathVariable String fileName, @RequestHeader("Authorization") String authorization){
        return fileService.getFile(fileName, authorization);
    }

    @GetMapping(path = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    private Response getAll(){
        return fileService.getAllFile();
    }

    @GetMapping(path = "/delete/{fileName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    private Response deleteFile(@PathVariable String fileName){
        return fileService.deleteFile(fileName);
    }

}

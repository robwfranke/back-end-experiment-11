package nl.lotrac.bv.controller;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.message.ResponseMessage;
import nl.lotrac.bv.repository.FileDBRepository;
import nl.lotrac.bv.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/files")


@Slf4j

public class FileController {


    @Autowired
//    private FileStorageService storageService;
    private FileDBRepository fileDBRepository;
    @Autowired
    private FilesStorageService filesStorageService;


    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile files)throws Exception{
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile files) {
        log.debug("in Controller FilesController");
        log.debug("filename"+files.getOriginalFilename());
//        log.debug(""+files.length);
        String message = "";
//        log.debug(StringUtils.cleanPath(files.getOriginalFilename()));
//      return ResponseEntity.ok("Hello");
        try {
            List<String> fileNames = new ArrayList<>();


//            for (MultipartFile file : (files)) {
//                filesStorageService.save(file);
//                fileNames.add(file.getOriginalFilename());
//            }

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

//    @GetMapping("/files")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }
//
//    @GetMapping("/files/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//        FileDB fileDB = storageService.getFile(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
//    }
//


}

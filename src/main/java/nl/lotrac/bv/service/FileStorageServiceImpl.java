
package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;

import nl.lotrac.bv.model.FileDB;

import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.FileDBRepository;

import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.utils.ExtractUserName;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

import java.util.stream.Stream;

@Slf4j

@Service
public class FileStorageServiceImpl {

    @Autowired
    private FileDBRepository fileDBRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private ExtractUserName extractUserName;


    @Autowired
    private UserRepository userRepository;


    public FileDB store(MultipartFile file) throws IOException {


    log.debug("IN STORE FILE");
    String username = extractUserName.extractUserNameFromJwt();
    User user = userRepository.getUserByUsername(username);
    log.debug("userName" + username);





        System.out.println("IN store");
        log.debug("IN store");

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), file.getSize());

        FileDB.setUser(user);

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }


    @Transactional
    public void deleteFileById(String id) {

        log.debug("deleteFileById");


        fileDBRepository.deleteById(id);

    }


}



package nl.lotrac.bv.service;


import nl.lotrac.bv.model.FileDB;
import nl.lotrac.bv.repository.FileDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@Service
public class FilesStorageServiceImpl implements FilesStorageService {


  @Autowired
  private FileDBRepository fileDBRepository;


    @Override
    public void save(MultipartFile file) {
        log.debug("Start save file");

        try {

            FileDB fileEntity = new FileDB();


            fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            fileEntity.setContentType(file.getContentType());
            fileEntity.setData(file.getBytes());
            fileEntity.setSize(file.getSize());

            log.debug(fileEntity.getName());

            fileDBRepository.save(fileEntity);

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

}

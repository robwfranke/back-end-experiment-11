package nl.lotrac.bv.service;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.model.FileEntity;
import nl.lotrac.bv.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FileService{


    @Autowired
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
                this.fileRepository = fileRepository;
    }

    public void save(MultipartFile file) throws IOException {
        log.debug("FileService.save");
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());

        fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> getFile(String id) {
        return fileRepository.findById(id);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

}

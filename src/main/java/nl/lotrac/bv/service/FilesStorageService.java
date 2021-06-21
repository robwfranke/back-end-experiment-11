package nl.lotrac.bv.service;

import nl.lotrac.bv.model.FileDB;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  static void store(MultipartFile file) {

  }
  public void save(MultipartFile file);
  public abstract FileDB getFile(String id);
  public abstract Stream<FileDB> getAllFiles();
//  public abstract FileDB store(MultipartFile file);

//  public void init();
//
//  public void save(MultipartFile file);
//
//  public Resource load(String filename);
//
//  public void deleteAll();
//
//  public Stream<Path> loadAll();
}

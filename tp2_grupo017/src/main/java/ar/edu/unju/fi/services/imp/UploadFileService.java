package ar.edu.unju.fi.services.imp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.services.IUploadFileService;

@Service
public class UploadFileService implements IUploadFileService {

    private static final String UPLOADS_FOLDER = "src/main/resources/static/uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path path= getPath(filename);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error en path: " + path.toString());
        }

        return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String uniqueFilename = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);
        Files.copy(file.getInputStream(), rootPath);
        return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
        File file = rootPath.toFile();
        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
	}
    
    public Path getPath(String filename){
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}

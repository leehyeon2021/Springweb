package manager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    private String baseDir = System.getProperty("user.dir");
    private String uploadDir = baseDir + "/build/resources/main/static/upload/";

    public String upload(MultipartFile multipartFile){

        if(multipartFile == null || multipartFile.isEmpty() ){
            return null;
        }

        File uploadPath = new File( uploadDir );
        if(uploadPath.exists() == false ){
            uploadPath.mkdir();
        }

        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + multipartFile
                                        .getOriginalFilename()
                                        .replaceAll("_", "-");
        File uploadRealPath = new File( uploadDir + fileName );
        try{
            multipartFile.transferTo( uploadRealPath );
            return fileName;
        }catch (IOException e) {
            System.out.println("예외발생: "+e);
            return null;
        }
    }
}

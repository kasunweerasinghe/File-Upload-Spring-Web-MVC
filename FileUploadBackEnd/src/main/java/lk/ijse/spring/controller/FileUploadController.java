/**
 * created by kasun weerasinghe
 * Date: 2023-02-09
 * Time: 08:38
 * Project Name: FileUploadBackEnd
 */

package lk.ijse.spring.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("upload")
@CrossOrigin
public class FileUploadController {

    private static final ArrayList<String> allImages = new ArrayList<>();

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadFile(@RequestPart("myFile") MultipartFile myFile) {
        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            System.out.println(projectPath);
            uploadsDir.mkdir();
            myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            //save the path of the uploaded image in the temporary database
            allImages.add("uploads/" + myFile.getOriginalFilename());

            return  ResponseEntity.ok(HttpStatus.OK);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllImagesFromDatabase() {
        return new ResponseEntity(allImages, HttpStatus.OK);
    }

}

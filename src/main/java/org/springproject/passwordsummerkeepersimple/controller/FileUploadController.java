package org.springproject.passwordsummerkeepersimple.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springproject.passwordsummerkeepersimple.exception.NotFoundException;

import java.io.IOException;

@RestController
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws NotFoundException{
        if (file.isEmpty()) {
            throw new NotFoundException("File is empty");
            //return "Файл пуст";
        }

        try {
            // Получение имени файла
            String fileName = file.getOriginalFilename();

            // Прочитать содержимое файла (можно заменить на логику сохранения файла)
            byte[] fileContent = file.getBytes();
            // Вы можете сохранить файл в файловой системе или базе данных здесь
            log.info("FILE UPLOADED: " + fileName);
            return "Файл " + fileName + " успешно загружен";
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ERROR UPLOAD FILE", e);
            return "Ошибка загрузки файла: " + e.getMessage();

    }
}
}

package com.khaled.injest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FileController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String relative =  Paths.get("").toAbsolutePath().toString() + "\\uploadedFiles\\";
        File convertFile = new File(relative + file.getOriginalFilename());
        String convertFileName = convertFile.getName();
        String fileNameAsHTML = convertFileName + ".html";
        String convertAndStore = "cd " + relative + "&& pandoc " + convertFileName + " -t html -s -o " + fileNameAsHTML;
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);

        fout.write(file.getBytes());
        fout.close();




        // Conversion using pandoc
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", convertAndStore);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }

        // Save the original file
        String content = new String(Files.readAllBytes(Paths.get(relative + file.getOriginalFilename())), "UTF-8");
        bookRepository.save(new Book(convertFileName, content));

        // Save the original file
        String convertedContent = new String(Files.readAllBytes(Paths.get(relative + fileNameAsHTML)), "UTF-8");
        bookRepository.save(new Book(convertFileName, convertedContent));

    }
}


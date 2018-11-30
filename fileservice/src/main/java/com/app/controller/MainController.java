package com.app.controller;

import com.app.domain.FileUser;
import com.app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.io.IOException;

@RestController
public class MainController {
    private final FileService fileService;

    @Autowired
    public MainController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/code")
    public FileUser returnCodeJson(@NonNull @Email @RequestParam String email) throws IOException, InterruptedException {
        return fileService.getCodeAndReturnEntity(email);
    }

    @PostMapping("/verify")
    public boolean verify(@NonNull @Email @RequestParam String email){
        return fileService.verifyUserCode(email);
    }
}

package com.app.service;

import com.app.dao.CodeRepository;
import com.app.dao.FileUserRepository;
import com.app.domain.Code;
import com.app.domain.FileUser;
import com.app.utils.Base64Cifre;
import com.app.utils.NIOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {
    private final FileUserRepository fileUserRepository;
    private final CodeRepository codeRepository;

    @Autowired
    public FileService(FileUserRepository fileUserRepository, CodeRepository codeRepository) {
        this.fileUserRepository = fileUserRepository;
        this.codeRepository = codeRepository;
    }

    public FileUser getCodeAndReturnEntity(String email) throws IOException, InterruptedException {
        //TODO Call Generator, Save whole user to DB, Save Code to DB, Return whole user. Got it?
        String hashedCodeFromFile = NIOUtils.getHashedCodeFromFile();
        Code code = new Code(Base64Cifre.decode(hashedCodeFromFile));
        FileUser user = new FileUser(email,code);
        codeRepository.save(code);
        fileUserRepository.save(user);
        return user;
    }

    public boolean verifyUserCode(String email){
        return false;
    }

}

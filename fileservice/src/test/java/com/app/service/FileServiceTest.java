package com.app.service;

import com.app.dao.FileUserRepository;
import com.app.domain.FileUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileServiceTest {
    @Mock
    FileService fileService;

    @Test
    public void getCodeAndReturnEntity() throws IOException, InterruptedException {
        Mockito.when(fileService.getCodeAndReturnEntity("test@test"))
                .thenReturn(new FileUser());
    }

    @Test
    public void verifyUserCode() {
    }
}
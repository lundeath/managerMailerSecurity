package com.app.dao;

import com.app.domain.FileUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUserRepository extends MongoRepository<FileUser, Long> {
}

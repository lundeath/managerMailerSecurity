package com.app.dao;

import com.app.domain.Code;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeRepository extends MongoRepository<Code, Long>{
}

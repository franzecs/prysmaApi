package com.ikytus.prysma.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.prysma.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}

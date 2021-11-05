package com.sdp.project.repository;
import com.sdp.project.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {

        User findByUsername(String username);

    }


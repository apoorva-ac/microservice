package com.microservice.user.service;

import com.microservice.user.entity.User;
import com.microservice.user.exception.UserNotFoundException;
import com.microservice.user.repository.UserRepository;
import com.microservice.user.vo.Department;
import com.microservice.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("inside saveUser");
        return repository.save(user);
    }

    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    public ResponseTemplateVO getUserWithDepartment(Long id) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = repository.findByUserId(id);
        if(user==null){
            throw new UserNotFoundException("id-" + id);
        }
        Department department = restTemplate.getForObject("http://API-GATEWAY/departments/" + user.getDepartmentId(), Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}

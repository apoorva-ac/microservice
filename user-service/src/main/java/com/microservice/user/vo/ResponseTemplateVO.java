package com.microservice.user.vo;

import com.microservice.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO extends RepresentationModel {
    private User user;
    private Department department;
}

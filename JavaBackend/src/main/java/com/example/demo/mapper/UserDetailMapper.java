package com.example.demo.mapper;

import com.example.demo.dto.UserDetailDto;
import com.example.demo.entity.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDetailMapper {

    @Mapping(source = "user.name",  target = "userName")
    @Mapping(source = "user.email", target = "userEmail")
    UserDetailDto toDto(UserDetail entity);

    List<UserDetailDto> toDtoList(List<UserDetail> entities);
}

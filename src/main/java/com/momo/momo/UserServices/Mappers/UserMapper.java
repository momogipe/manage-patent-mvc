package com.momo.momo.UserServices.Mappers;

import com.momo.momo.Dto.UserDTo;
import com.momo.momo.EntitiesUser.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    User toEntity(UserDTo userDTo);
    UserDTo toDto(User user);

    void copy (UserDTo userDTo, @MappingTarget User user);

}

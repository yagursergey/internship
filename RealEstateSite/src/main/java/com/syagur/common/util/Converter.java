package com.syagur.common.util;

import com.syagur.realty.Realty;
import com.syagur.realty.RealtyDto;
import com.syagur.user.User;
import com.syagur.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Converter {

    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    @Mappings({
            @Mapping(target = "ownerFirstName", source = "realty.owner.firstName")
    })
    RealtyDto toRealtyDto(Realty realty);

    Realty toRealty(RealtyDto realtyDto);

}

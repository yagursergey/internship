package com.syagur.util;

import com.syagur.dto.RealtyDto;
import com.syagur.dto.UserDto;
import com.syagur.entity.Realty;
import com.syagur.entity.User;
import com.syagur.entity.enums.RealtyType;
import com.syagur.entity.enums.UserRole;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-12T18:23:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Ubuntu)"
)
@Component
public class ConverterImpl implements Converter {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setEmail( user.getEmail() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setSecondName( user.getSecondName() );
        if ( user.getRole() != null ) {
            userDto.setRole( user.getRole().name() );
        }
        userDto.setId( user.getId() );

        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFirstName( userDto.getFirstName() );
        user.setSecondName( userDto.getSecondName() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );
        if ( userDto.getRole() != null ) {
            user.setRole( Enum.valueOf( UserRole.class, userDto.getRole() ) );
        }

        return user;
    }

    @Override
    public RealtyDto toRealtyDto(Realty realty) {
        if ( realty == null ) {
            return null;
        }

        RealtyDto realtyDto = new RealtyDto();

        realtyDto.setOwnerFirstName( realtyOwnerFirstName( realty ) );
        realtyDto.setId( realty.getId() );
        realtyDto.setPrice( realty.getPrice() );
        realtyDto.setSquare( realty.getSquare() );
        if ( realty.getDateOfCreation() != null ) {
            realtyDto.setDateOfCreation( DateTimeFormatter.ISO_LOCAL_DATE.format( realty.getDateOfCreation() ) );
        }
        if ( realty.getType() != null ) {
            realtyDto.setType( realty.getType().name() );
        }
        realtyDto.setDescription( realty.getDescription() );

        return realtyDto;
    }

    @Override
    public Realty toRealty(RealtyDto realtyDto) {
        if ( realtyDto == null ) {
            return null;
        }

        Realty realty = new Realty();

        realty.setId( realtyDto.getId() );
        realty.setPrice( realtyDto.getPrice() );
        realty.setSquare( realtyDto.getSquare() );
        if ( realtyDto.getType() != null ) {
            realty.setType( Enum.valueOf( RealtyType.class, realtyDto.getType() ) );
        }
        realty.setDescription( realtyDto.getDescription() );
        if ( realtyDto.getDateOfCreation() != null ) {
            realty.setDateOfCreation( LocalDate.parse( realtyDto.getDateOfCreation() ) );
        }

        return realty;
    }

    private String realtyOwnerFirstName(Realty realty) {
        if ( realty == null ) {
            return null;
        }
        User owner = realty.getOwner();
        if ( owner == null ) {
            return null;
        }
        String firstName = owner.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }
}

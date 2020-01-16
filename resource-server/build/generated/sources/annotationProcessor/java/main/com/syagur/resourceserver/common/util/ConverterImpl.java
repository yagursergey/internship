package com.syagur.resourceserver.common.util;

import com.syagur.resourceserver.realty.Realty;
import com.syagur.resourceserver.realty.RealtyDto;
import com.syagur.resourceserver.realty.RealtyType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-16T13:55:20+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_232 (Private Build)"
)
@Component
public class ConverterImpl implements Converter {

    @Override
    public RealtyDto toRealtyDto(Realty realty) {
        if ( realty == null ) {
            return null;
        }

        RealtyDto realtyDto = new RealtyDto();

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
        realtyDto.setOwnerEmail( realty.getOwnerEmail() );
        if ( realty.getDateOfBuilding() != null ) {
            realtyDto.setDateOfBuilding( DateTimeFormatter.ISO_LOCAL_DATE.format( realty.getDateOfBuilding() ) );
        }
        realtyDto.setCity( realty.getCity() );
        realtyDto.setStreet( realty.getStreet() );
        realtyDto.setHouse( realty.getHouse() );

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
        if ( realtyDto.getDateOfCreation() != null ) {
            realty.setDateOfCreation( LocalDate.parse( realtyDto.getDateOfCreation() ) );
        }
        if ( realtyDto.getType() != null ) {
            realty.setType( Enum.valueOf( RealtyType.class, realtyDto.getType() ) );
        }
        realty.setDescription( realtyDto.getDescription() );
        realty.setOwnerEmail( realtyDto.getOwnerEmail() );
        if ( realtyDto.getDateOfBuilding() != null ) {
            realty.setDateOfBuilding( LocalDate.parse( realtyDto.getDateOfBuilding() ) );
        }
        realty.setCity( realtyDto.getCity() );
        realty.setStreet( realtyDto.getStreet() );
        realty.setHouse( realtyDto.getHouse() );

        return realty;
    }
}

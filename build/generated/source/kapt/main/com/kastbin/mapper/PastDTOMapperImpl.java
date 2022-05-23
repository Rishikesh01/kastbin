package com.kastbin.mapper;

import com.kastbin.dto.PastDTO;
import com.kastbin.model.PastDetailsModel;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T21:07:18+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.4.32.jar, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class PastDTOMapperImpl implements PastDTOMapper {

    @Override
    public PastDTO toPastDTO(PastDetailsModel pastDetailsModel) {
        if ( pastDetailsModel == null ) {
            return null;
        }

        String past = null;
        String pastType = null;
        String password = null;

        past = pastDetailsModel.getPast();
        pastType = pastDetailsModel.getPastType();
        password = pastDetailsModel.getPassword();

        PastDTO pastDTO = new PastDTO( past, pastType, password );

        return pastDTO;
    }

    @Override
    public PastDetailsModel toPastDetailsModel(PastDTO pastDTO) {
        if ( pastDTO == null ) {
            return null;
        }

        String past = null;
        String pastType = null;
        String password = null;

        past = pastDTO.getPast();
        pastType = pastDTO.getPastType();
        password = pastDTO.getPassword();

        Boolean isProtected = null;
        String pastURL = null;
        Date dateOfCreation = null;
        Long id = null;

        PastDetailsModel pastDetailsModel = new PastDetailsModel( past, pastType, isProtected, password, pastURL, dateOfCreation, id );

        return pastDetailsModel;
    }

    @Override
    public List<PastDetailsModel> toListPastDetailsModel(List<PastDTO> pastDto) {
        if ( pastDto == null ) {
            return null;
        }

        List<PastDetailsModel> list = new ArrayList<PastDetailsModel>( pastDto.size() );
        for ( PastDTO pastDTO : pastDto ) {
            list.add( toPastDetailsModel( pastDTO ) );
        }

        return list;
    }

    @Override
    public List<PastDTO> toListPastDTO(List<PastDetailsModel> pastDetailsModel) {
        if ( pastDetailsModel == null ) {
            return null;
        }

        List<PastDTO> list = new ArrayList<PastDTO>( pastDetailsModel.size() );
        for ( PastDetailsModel pastDetailsModel1 : pastDetailsModel ) {
            list.add( toPastDTO( pastDetailsModel1 ) );
        }

        return list;
    }
}

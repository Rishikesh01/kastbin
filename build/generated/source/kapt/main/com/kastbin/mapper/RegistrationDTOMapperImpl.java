package com.kastbin.mapper;

import com.kastbin.dto.UserRegistrationDTO;
import com.kastbin.model.PastDetailsModel;
import com.kastbin.model.UserModel;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T21:07:18+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.4.32.jar, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class RegistrationDTOMapperImpl implements RegistrationDTOMapper {

    @Override
    public UserRegistrationDTO toUserRegDTO(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        String userName = null;
        String password = null;
        String email = null;

        userName = userModel.getUserName();
        password = userModel.getPassword();
        email = userModel.getEmail();

        String confirmPassword = null;

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO( userName, password, confirmPassword, email );

        return userRegistrationDTO;
    }

    @Override
    public UserModel toUserModel(UserRegistrationDTO userRegistrationDTO) {
        if ( userRegistrationDTO == null ) {
            return null;
        }

        String userName = null;
        String email = null;
        String password = null;

        userName = userRegistrationDTO.getUserName();
        email = userRegistrationDTO.getEmail();
        password = userRegistrationDTO.getPassword();

        List<PastDetailsModel> pastModel = null;
        Boolean oauth = null;
        LocalDateTime dateAndTimeOfCreation = null;
        boolean isEnabled = false;
        boolean isNotLocked = false;
        Long id = null;

        UserModel userModel = new UserModel( userName, email, pastModel, oauth, password, dateAndTimeOfCreation, isEnabled, isNotLocked, id );

        return userModel;
    }
}

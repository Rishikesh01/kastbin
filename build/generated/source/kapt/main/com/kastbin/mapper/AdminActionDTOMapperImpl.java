package com.kastbin.mapper;

import com.kastbin.dto.AdminActionDTO;
import com.kastbin.model.ActionModel;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T21:07:18+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.4.32.jar, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class AdminActionDTOMapperImpl implements AdminActionDTOMapper {

    @Override
    public ActionModel toActionModel(AdminActionDTO adminActionDTO) {
        if ( adminActionDTO == null ) {
            return null;
        }

        String email = null;
        String action = null;
        String reason = null;

        email = adminActionDTO.getEmail();
        action = adminActionDTO.getAction();
        reason = adminActionDTO.getReason();

        String admin = null;
        long id = 0L;

        ActionModel actionModel = new ActionModel( email, action, reason, admin, id );

        return actionModel;
    }

    @Override
    public ActionModel toAdminActionDTO(ActionModel actionModel) {
        if ( actionModel == null ) {
            return null;
        }

        String admin = null;
        String email = null;
        String action = null;
        String reason = null;
        long id = 0L;

        admin = actionModel.getAdmin();
        email = actionModel.getEmail();
        action = actionModel.getAction();
        reason = actionModel.getReason();
        id = actionModel.getId();

        ActionModel actionModel1 = new ActionModel( email, action, reason, admin, id );

        return actionModel1;
    }
}

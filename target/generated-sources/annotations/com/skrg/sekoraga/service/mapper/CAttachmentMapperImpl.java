package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.domain.dto.CAttachmentDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:24:42+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class CAttachmentMapperImpl implements CAttachmentMapper {

    @Override
    public CAttachmentDTO toDto(CAttachment entity) {
        if ( entity == null ) {
            return null;
        }

        CAttachmentDTO cAttachmentDTO = new CAttachmentDTO();

        cAttachmentDTO.setId( CAttachmentMapper.longToString( entity.getAttachmentId() ) );
        cAttachmentDTO.setFileName( entity.getFileName() );
        cAttachmentDTO.setMimeType( entity.getMimeType() );
        cAttachmentDTO.setDocumentType( entity.getDocumentType() );
        cAttachmentDTO.setType( entity.getType() );

        return cAttachmentDTO;
    }

    @Override
    public CAttachment toEntity(CAttachmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CAttachment cAttachment = new CAttachment();

        cAttachment.setAttachmentId( CAttachmentMapper.stringToLong( dto.getId() ) );
        cAttachment.setFileName( dto.getFileName() );
        cAttachment.setMimeType( dto.getMimeType() );
        cAttachment.setDocumentType( dto.getDocumentType() );
        cAttachment.setType( dto.getType() );

        return cAttachment;
    }
}

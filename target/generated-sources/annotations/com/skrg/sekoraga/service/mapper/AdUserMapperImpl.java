package com.skrg.sekoraga.service.mapper;

import com.skrg.sekoraga.domain.AdAuthority;
import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.domain.dto.AdAuthorityDTO;
import com.skrg.sekoraga.domain.dto.AdUserDTO;
import com.skrg.sekoraga.domain.dto.CAttachmentDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T13:49:28+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (OpenLogic)"
)
@Component
public class AdUserMapperImpl implements AdUserMapper {

    @Autowired
    private AdAuthorityMapper adAuthorityMapper;

    @Override
    public AdUserDTO toDto(AdUser entity) {
        if ( entity == null ) {
            return null;
        }

        AdUserDTO adUserDTO = new AdUserDTO();

        adUserDTO.setId( AdUserMapper.adUserLongToString( entity.getUserId() ) );
        adUserDTO.setPassword( entity.getPassword() );
        adUserDTO.setUsername( entity.getUsername() );
        adUserDTO.setEmail( entity.getEmail() );
        adUserDTO.setAuthorities( adAuthoritySetToAdAuthorityDTOSet( entity.getAuthorities() ) );
        adUserDTO.setName( entity.getName() );
        adUserDTO.setAge( entity.getAge() );
        adUserDTO.setEducationalLevel( entity.getEducationalLevel() );
        adUserDTO.setAttachment( cAttachmentToCAttachmentDTO( entity.getAttachment() ) );

        return adUserDTO;
    }

    @Override
    public AdUser toEntity(AdUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AdUser adUser = new AdUser();

        adUser.setUserId( AdUserMapper.adUserStringToLong( dto.getId() ) );
        adUser.setPassword( dto.getPassword() );
        adUser.setUsername( dto.getUsername() );
        adUser.setEmail( dto.getEmail() );
        adUser.setName( dto.getName() );
        adUser.setAge( dto.getAge() );
        adUser.setEducationalLevel( dto.getEducationalLevel() );
        adUser.setAuthorities( adAuthorityDTOSetToAdAuthoritySet( dto.getAuthorities() ) );
        adUser.setAttachment( cAttachmentDTOToCAttachment( dto.getAttachment() ) );

        return adUser;
    }

    protected Set<AdAuthorityDTO> adAuthoritySetToAdAuthorityDTOSet(Set<AdAuthority> set) {
        if ( set == null ) {
            return null;
        }

        Set<AdAuthorityDTO> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( AdAuthority adAuthority : set ) {
            set1.add( adAuthorityMapper.toDto( adAuthority ) );
        }

        return set1;
    }

    protected CAttachmentDTO cAttachmentToCAttachmentDTO(CAttachment cAttachment) {
        if ( cAttachment == null ) {
            return null;
        }

        CAttachmentDTO cAttachmentDTO = new CAttachmentDTO();

        cAttachmentDTO.setFileName( cAttachment.getFileName() );
        cAttachmentDTO.setMimeType( cAttachment.getMimeType() );
        cAttachmentDTO.setDocumentType( cAttachment.getDocumentType() );
        cAttachmentDTO.setType( cAttachment.getType() );

        return cAttachmentDTO;
    }

    protected Set<AdAuthority> adAuthorityDTOSetToAdAuthoritySet(Set<AdAuthorityDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<AdAuthority> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( AdAuthorityDTO adAuthorityDTO : set ) {
            set1.add( adAuthorityMapper.toEntity( adAuthorityDTO ) );
        }

        return set1;
    }

    protected CAttachment cAttachmentDTOToCAttachment(CAttachmentDTO cAttachmentDTO) {
        if ( cAttachmentDTO == null ) {
            return null;
        }

        CAttachment cAttachment = new CAttachment();

        cAttachment.setFileName( cAttachmentDTO.getFileName() );
        cAttachment.setMimeType( cAttachmentDTO.getMimeType() );
        cAttachment.setDocumentType( cAttachmentDTO.getDocumentType() );
        cAttachment.setType( cAttachmentDTO.getType() );

        return cAttachment;
    }
}

package com.skrg.sekoraga.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.CAttachment;
import com.skrg.sekoraga.domain.dto.CAttachmentDTO;
import com.skrg.sekoraga.repository.CAttachmentRepository;
import com.skrg.sekoraga.service.mapper.CAttachmentMapper;

import java.util.Optional;

@Service
@Transactional
public class CAttachmentService {
    private final Logger log = LoggerFactory.getLogger(CAttachmentService.class);

    @Autowired
    private CAttachmentRepository cAttachmentRepository;

    @Autowired
    private CAttachmentMapper cAttachmentMapper;

    public CAttachmentDTO save(CAttachmentDTO dto) {
        log.debug("Request to save CAttachment : {}", dto);
        CAttachment entity = cAttachmentMapper.toEntity(dto);
        entity = cAttachmentRepository.save(entity);
        return cAttachmentMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<CAttachmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CAttachments");
        return cAttachmentRepository.findAll(pageable).map(cAttachmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<CAttachmentDTO> findOne(String id) {
        log.debug("Request to get CAttachment : {}", id);
        Long longId = parseId(id);
        return cAttachmentRepository.findById(longId).map(cAttachmentMapper::toDto);
    }

    public void delete(String id) {
        log.debug("Request to delete CAttachment : {}", id);
        Long longId = parseId(id);
        cAttachmentRepository.deleteById(longId);
    }

    private Long parseId(String id) {
        if (id == null || id.isEmpty())
            return null;
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + id, e);
        }
    }
}

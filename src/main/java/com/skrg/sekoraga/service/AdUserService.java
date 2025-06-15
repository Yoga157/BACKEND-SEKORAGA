package com.skrg.sekoraga.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.dto.AdUserDTO;
import com.skrg.sekoraga.repository.AdUserRepository;
import com.skrg.sekoraga.service.mapper.AdUserMapper;

import java.util.Optional;

@Service
@Transactional
public class AdUserService {
    private final Logger log = LoggerFactory.getLogger(AdUserService.class);

    @Autowired
    private AdUserRepository adUserRepository;

    @Autowired
    private AdUserMapper adUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdUserDTO save(AdUserDTO adUserDTO) {
        log.debug("Request to save AdUser : {}", adUserDTO);
        AdUser adUser = adUserMapper.toEntity(adUserDTO);
        // Jika update (userId tidak null), dan password null, ambil password lama dari DB
        if (adUser.getUserId() != null && (adUser.getPassword() == null || adUser.getPassword().isEmpty())) {
            Optional<AdUser> existing = adUserRepository.findById(adUser.getUserId());
            if (existing.isPresent()) {
                adUser.setPassword(existing.get().getPassword());
            }
        }
        // Hash password jika ada perubahan dan belum di-hash
        if (adUser.getPassword() != null && !adUser.getPassword().startsWith("$2a")
                && !adUser.getPassword().startsWith("$2b") && !adUser.getPassword().startsWith("$2y")) {
            adUser.setPassword(passwordEncoder.encode(adUser.getPassword()));
        }
        AdUser savedUser = adUserRepository.save(adUser);
        return adUserMapper.toDto(savedUser);
    }

    @Transactional(readOnly = true)
    public Page<AdUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdUsers");
        return adUserRepository.findAll(pageable).map(adUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<AdUserDTO> findOne(String id) {
        log.debug("Request to get AdUser : {}", id);
        Long longId = parseId(id);
        return adUserRepository.findById(longId).map(adUserMapper::toDto);
    }

    public void delete(String id) {
        log.debug("Request to delete AdUser : {}", id);
        Long longId = parseId(id);
        adUserRepository.deleteById(longId);
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

    public AdUserRepository getAdUserRepository() {
        return adUserRepository;
    }

    public AdUserMapper getAdUserMapper() {
        return adUserMapper;
    }
}

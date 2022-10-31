package com.ace.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ace.ecommerce.entity.Pengguna;
import com.ace.ecommerce.exception.BadRequestException;
import com.ace.ecommerce.exception.ResourceNotFoundException;
import com.ace.ecommerce.repository.PenggunaRepository;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    public List<Pengguna> findAll() {
        return penggunaRepository.findAll();
    }

    public Pengguna findById(String id) {
        return penggunaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pengguna dengan Id " + id + ", tidak ditemukan"));
    }

    public Pengguna create(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("Username harus di isi");
        }

        if (penggunaRepository.existsById(pengguna.getId())) {
            throw new BadRequestException("Username " + pengguna.getId() + ", Sudah terdaftar.");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email harus di isi");
        }

        if (penggunaRepository.existsByEmail(pengguna.getEmail())) {
            throw new BadRequestException("Email " + pengguna.getEmail() + ", sudah terdaftar");
        }

        pengguna.setIsAktif(true);
        return penggunaRepository.save(pengguna);
    }

    public Pengguna edit(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("Username harus di isi");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email harus di isi");
        }

        return penggunaRepository.save(pengguna);
    }

    public void deleteById(String id) {
        penggunaRepository.deleteById(id);
    }

}

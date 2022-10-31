package com.ace.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ace.ecommerce.entity.Produk;
import com.ace.ecommerce.exception.BadRequestException;
import com.ace.ecommerce.exception.ResourceNotFoundException;
import com.ace.ecommerce.repository.KategoriRepository;
import com.ace.ecommerce.repository.ProdukRepositroy;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepositroy produkRepositroy;

    @Autowired
    private KategoriRepository kategoriRepository;

    public List<Produk> findAll() {
        return produkRepositroy.findAll();
    }

    public Produk findById(String id) {
        return produkRepositroy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan Id" + id + " tidak ditemukan"));
    }

    public Produk create(Produk produk) {
        if (!StringUtils.hasText(produk.getNama())) {
            throw new BadRequestException("Nama produk tidak boleh kosong");
        }

        if (produk.getKategori() == null) {
            throw new BadRequestException("Kategori tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getKategori().getId())) {
            throw new BadRequestException("Kategori id tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Kategori ID " + produk.getKategori().getId() + " tidak ditemukan"));

        produk.setId(UUID.randomUUID().toString());
        return produkRepositroy.save(produk);
    }

    public Produk edit(Produk produk) {
        if (!StringUtils.hasText(produk.getId())) {
            throw new BadRequestException("Produk Id harus di isi");
        }

        if (!StringUtils.hasText(produk.getNama())) {
            throw new BadRequestException("Nama produk tidak boleh kosong");
        }

        if (produk.getKategori() == null) {
            throw new BadRequestException("Kategori tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getKategori().getId())) {
            throw new BadRequestException("Kategori id tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Kategori ID " + produk.getKategori().getId() + " tidak ditemukan"));

        return produkRepositroy.save(produk);
    }

    public Produk ubahGambar(String id, String gambar) {
        Produk produk = findById(id);
        produk.setGambar(gambar);
        return produkRepositroy.save(produk);
    }

    public void deleteById(String id) {
        produkRepositroy.deleteById(id);
    }

}

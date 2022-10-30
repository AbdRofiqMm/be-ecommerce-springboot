package com.ace.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.ecommerce.entity.Produk;
import com.ace.ecommerce.exception.ResourceNotFoundException;
import com.ace.ecommerce.repository.ProdukRepositroy;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepositroy produkRepositroy;

    public List<Produk> findAll() {
        return produkRepositroy.findAll();
    }

    public Produk findById(String id) {
        return produkRepositroy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan Id" + id + " tidak ditemukan"));
    }

    public Produk create(Produk produk) {
        produk.setId(UUID.randomUUID().toString());
        return produkRepositroy.save(produk);
    }

    public Produk edit(Produk produk) {
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

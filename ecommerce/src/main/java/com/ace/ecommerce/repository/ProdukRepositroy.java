package com.ace.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ace.ecommerce.entity.Produk;

public interface ProdukRepositroy extends JpaRepository<Produk, String> {

}

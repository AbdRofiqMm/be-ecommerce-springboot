package com.ace.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ace.ecommerce.entity.Keranjang;

public interface KeranjangRepository extends JpaRepository<Keranjang, String> {

}

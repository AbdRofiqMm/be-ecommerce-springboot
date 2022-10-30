package com.ace.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ace.ecommerce.entity.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, String> {

}

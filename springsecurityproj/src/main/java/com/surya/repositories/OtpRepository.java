package com.surya.repositories;

import com.surya.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Integer> {
    Otp findOtpByUsername(String uname);
}

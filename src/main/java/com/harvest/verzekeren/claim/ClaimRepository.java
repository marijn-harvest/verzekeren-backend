package com.harvest.verzekeren.claim;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ClaimRepository extends JpaRepository<Claim, Long>
{
    List<Claim> findByUserId(Long userId);
}

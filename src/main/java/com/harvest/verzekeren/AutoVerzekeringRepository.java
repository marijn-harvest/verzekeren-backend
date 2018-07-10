package com.harvest.verzekeren;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface AutoVerzekeringRepository extends JpaRepository<AutoVerzekering, Long>
{
}
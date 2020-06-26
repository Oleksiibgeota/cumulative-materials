package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.Assembly
import org.springframework.data.jpa.repository.JpaRepository

interface AssemblyRepository : JpaRepository<Assembly, Long> {
    fun existsByNameAndElevationAndMainSectionName(name: String, elevation: Int, mainSectionName: String): Boolean
    fun findOneByNameAndElevationAndMainSectionName(name: String, elevation: Int, mainSectionName: String): Assembly
}
package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.Part
import org.springframework.data.jpa.repository.JpaRepository

interface PartRepository : JpaRepository<Part, Long> {
    fun existsByNameAndAssemblyNameAndAssemblyMainSectionName(name: String,
                                                              assemblyName: String,
                                                              mainSectionName: String): Boolean

    fun findOneByNameAndAssemblyNameAndAssemblyMainSectionName(name: String,
                                                               assemblyName: String,
                                                               mainSectionName: String): Part
}
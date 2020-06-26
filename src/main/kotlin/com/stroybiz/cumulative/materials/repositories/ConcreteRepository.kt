package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.Concrete
import org.springframework.data.jpa.repository.JpaRepository

interface ConcreteRepository : JpaRepository<Concrete, Long> {
    fun findOneByNameAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(name: String,
                                                                                  partName: String,
                                                                                  assemblyName: String,
                                                                                  mainSectionName: String): Concrete

    fun existsByNameAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(name: String,
                                                                                 partName: String,
                                                                                 assemblyName: String,
                                                                                 mainSectionName: String): Boolean
}
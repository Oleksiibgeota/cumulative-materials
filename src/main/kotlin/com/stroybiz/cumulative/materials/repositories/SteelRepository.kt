package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.Steel
import org.springframework.data.jpa.repository.JpaRepository

interface SteelRepository : JpaRepository<Steel, Long> {
    fun existsByNameAndPartPositionAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(name: String,partPosition:Int, partName: String, assemblyName: String, mainSectionName: String): Boolean
    fun findOneByNameAndPartPositionAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(name: String, partPosition:Int, partName: String, assemblyName: String, mainSectionName: String): Steel
}
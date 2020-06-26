package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.MainSection
import org.springframework.data.jpa.repository.JpaRepository

interface MainSectionRepository : JpaRepository<MainSection, Long> {
    fun findAllByName(name: String): List<MainSection>
    fun existsByName(name: String): Boolean
    fun findByName (name: String): MainSection
}
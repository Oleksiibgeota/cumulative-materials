package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.LengthValue
import org.springframework.data.jpa.repository.JpaRepository

interface LengthValueRepository : JpaRepository<LengthValue, Long> {
}
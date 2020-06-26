package com.stroybiz.cumulative.materials.repositories

import com.stroybiz.cumulative.materials.domain.VolumeValue
import org.springframework.data.jpa.repository.JpaRepository

interface VolumeValueRepository : JpaRepository<VolumeValue, Long> {
}
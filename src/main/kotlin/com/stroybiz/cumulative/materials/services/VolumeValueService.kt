package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.VolumeValue
import com.stroybiz.cumulative.materials.repositories.VolumeValueRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VolumeValueService(private val volumeValueRepository: VolumeValueRepository) {
    fun existById(id: Long): Boolean = volumeValueRepository.existsById(id)

    fun create(volumeValues: List<VolumeValue>): List<VolumeValue> = volumeValues.map { save(it) }
    private fun save(volumeValue: VolumeValue): VolumeValue = volumeValueRepository.saveAndFlush(volumeValue.copy(dateCreate = Date()))
}
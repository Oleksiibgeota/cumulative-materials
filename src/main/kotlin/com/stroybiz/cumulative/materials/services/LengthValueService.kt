package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.LengthValue
import com.stroybiz.cumulative.materials.repositories.LengthValueRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class LengthValueService(private val lengthValueRepository: LengthValueRepository) {
    fun create(lengthValues: List<LengthValue>): List<LengthValue> = lengthValues.map { save(it) }
    fun save(lengthValue: LengthValue): LengthValue {
        return lengthValueRepository.saveAndFlush(lengthValue.copy(dateCreate = Date()))
    }
}
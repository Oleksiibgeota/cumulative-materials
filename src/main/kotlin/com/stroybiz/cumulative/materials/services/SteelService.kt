package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.Steel
import com.stroybiz.cumulative.materials.repositories.SteelRepository
import org.springframework.stereotype.Service

@Service
class SteelService(private val steelRepository: SteelRepository,
                   private val lengthValueService: LengthValueService) {
    fun create(steels: List<Steel>): List<Steel> = steels.map {
        if (exists(it)) {
            update(it)
        } else save(it)
    }

    private fun update(steel: Steel): Steel {
        val persistSteel: Steel = getSteel(steel)
        return steelRepository.saveAndFlush(persistSteel.copy(lengthValues = lengthValueService.create(steel.lengthValues
                .map { it.copy(steel = persistSteel) })))
    }

    fun save(steel: Steel): Steel {
        val newSteel: Steel = steelRepository.saveAndFlush(steel)
        return steelRepository.saveAndFlush(newSteel.copy(lengthValues = lengthValueService.create(steel.lengthValues
                .map { it.copy(steel = newSteel) })))
    }

    private fun exists(steel: Steel): Boolean =
            steelRepository.existsByNameAndPartPositionAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(
                    steel.name, steel.partPosition, steel.part!!.name, steel.part!!.assembly!!.name, steel.part!!.assembly!!.mainSection!!.name)

    private fun getSteel(steel: Steel): Steel =
            steelRepository.findOneByNameAndPartPositionAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(
                    steel.name, steel.partPosition, steel.part!!.name, steel.part!!.assembly!!.name, steel.part!!.assembly!!.mainSection!!.name)
}
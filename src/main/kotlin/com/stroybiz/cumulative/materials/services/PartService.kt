package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.Part
import com.stroybiz.cumulative.materials.repositories.PartRepository
import org.springframework.stereotype.Service

@Service
class PartService(private val partRepository: PartRepository,
                  private val concreteService: ConcreteService,
                  private val steelService: SteelService) {
    fun create(parts: List<Part>): List<Part> =
            parts.map {
                if (exists(it)) {
                    update(it)
                } else save(it)
            }

    private fun update(part: Part): Part {
        val newPart: Part = getOne(part)
        return partRepository.saveAndFlush(newPart.copy(
                concretes = concreteService.create(part.concretes.map { it.copy(part = newPart) }),
                steels = steelService.create(part.steels.map { it.copy(part = newPart) })))
    }

    fun save(part: Part): Part {
        println(exists(part))
        println("-------------PartService method save---------------")
        val newPart: Part = partRepository.saveAndFlush(part)
        return partRepository.saveAndFlush(newPart.copy(
                concretes = concreteService.create(part.concretes.map { it.copy(part = newPart) }),
                steels = steelService.create(part.steels.map { it.copy(part = newPart) })
        ))
    }

    private fun exists(part: Part): Boolean = partRepository.existsByNameAndAssemblyNameAndAssemblyMainSectionName(
            part.name, part.assembly!!.name, part.assembly!!.mainSection!!.name)

    private fun getOne(part: Part): Part = partRepository.findOneByNameAndAssemblyNameAndAssemblyMainSectionName(
            part.name, part.assembly!!.name, part.assembly!!.mainSection!!.name)
}
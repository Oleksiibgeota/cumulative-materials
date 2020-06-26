package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.Concrete
import com.stroybiz.cumulative.materials.repositories.ConcreteRepository
import org.springframework.stereotype.Service

@Service
class ConcreteService(private val concreteRepository: ConcreteRepository,
                      private val volumeValueService: VolumeValueService) {
    fun create(concretes: List<Concrete>): List<Concrete> = concretes.map {
        if (exists(it)) {
            update(it)
        } else save(it)
    }

    fun save(concrete: Concrete): Concrete {
        val newConcrete: Concrete = concreteRepository.saveAndFlush(concrete)
        return concreteRepository.saveAndFlush(newConcrete.copy(
                volumeValue = volumeValueService.create(concrete.volumeValue.map { it.copy(concrete = newConcrete) })))
    }

    fun update(concrete: Concrete): Concrete {
        val persistedConcrete: Concrete = getConcrete(concrete)
        return concreteRepository.saveAndFlush(persistedConcrete.copy(
                volumeValue = volumeValueService.create(concrete.volumeValue.map { it.copy(concrete = persistedConcrete) })))
    }

    fun getConcrete(concrete: Concrete): Concrete =
            concreteRepository.findOneByNameAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(
                    concrete.name,
                    concrete.part!!.name,
                    concrete.part!!.assembly!!.name,
                    concrete.part!!.assembly!!.mainSection!!.name)

    private fun exists(concrete: Concrete): Boolean = concreteRepository.existsByNameAndPartNameAndPartAssemblyNameAndPartAssemblyMainSectionName(
            concrete.name,
            concrete.part!!.name,
            concrete.part!!.assembly!!.name,
            concrete.part!!.assembly!!.mainSection!!.name)
}
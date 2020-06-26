package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.Assembly
import com.stroybiz.cumulative.materials.repositories.AssemblyRepository
import org.springframework.stereotype.Service

@Service
class AssemblyService(private val assemblyRepository: AssemblyRepository,
                      private val partService: PartService) {
    fun create(assembly: Assembly): Assembly = assemblyRepository.saveAndFlush(assembly)
    fun create(listAssembly: List<Assembly>): List<Assembly> =
            listAssembly.map {
                if (existsByNameAndElevation(it)) {
                    update(it)
                } else save(it)
            }


    private fun update(assembly: Assembly): Assembly {
        println("assemblyService method update")
        val persistAssembly = findAssemblyByNameElevationMainSectionName(assembly)
        return assemblyRepository.saveAndFlush(
                persistAssembly.copy(parts = partService.create(
                        assembly.parts.map { it.copy(assembly = persistAssembly) })))
    }

    private fun save(assembly: Assembly): Assembly {
        val newAssembly: Assembly = assemblyRepository.saveAndFlush(assembly)
        return assemblyRepository.saveAndFlush(
                newAssembly.copy(parts = partService.create(
                        assembly.parts.map { it.copy(assembly = newAssembly) })))
    }

    //    private fun save (assembly: Assembly):Assembly {
//
//    }
    private fun existsByNameAndElevation(assembly: Assembly): Boolean =
            assemblyRepository.existsByNameAndElevationAndMainSectionName(
                    assembly.name, assembly.elevation, assembly.mainSection!!.name)

    private fun findAssemblyByNameElevationMainSectionName(assembly: Assembly): Assembly =
            assemblyRepository.findOneByNameAndElevationAndMainSectionName(
                    assembly.name, assembly.elevation, assembly.mainSection!!.name)

    fun findAssemblyByName(mainSectionName: String, assemblyName: String, elevation: Int): Assembly =
            assemblyRepository.findOneByNameAndElevationAndMainSectionName(
                    assemblyName, elevation, mainSectionName)
}
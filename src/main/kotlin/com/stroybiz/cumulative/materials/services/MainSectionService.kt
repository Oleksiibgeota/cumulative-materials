package com.stroybiz.cumulative.materials.services

import com.stroybiz.cumulative.materials.domain.MainSection
import com.stroybiz.cumulative.materials.repositories.MainSectionRepository
import org.springframework.stereotype.Service

@Service
class MainSectionService(
        val mainSectionRepository: MainSectionRepository,
        val assemblyService: AssemblyService,
        val partService: PartService,
        val steelService: SteelService,
        val concreteService: ConcreteService,
        val volumeValueService: VolumeValueService,
        val lengthValueService: LengthValueService) {

    fun exists(mainSection: MainSection): Boolean {
//        println(mainSection.name)
//        println(mainSectionRepository.existsByName(mainSection.name))
        return mainSectionRepository.existsByName(mainSection.name)
    }

    fun getByName(mainSection: MainSection): MainSection = mainSectionRepository.findByName(mainSection.name)

    fun create(listMainSection: List<MainSection>): List<MainSection> =
            listMainSection.map { save(it) }
//            save(
//                    save(listMainSection)
//                            .map { entry ->
//                                entry.copy(listAssembly = assemblyService.create(listMainSection.single { it.id == entry.id }.listAssembly
//                                        .map { entryListAssembly ->
//                                            entryListAssembly.copy(mainSection = entry, parts = partService.create(entry.listAssembly.single { listAssemblyEntry ->
//                                                listAssemblyEntry.id == entryListAssembly.id
//                                            }.parts.map { partEntry ->
//                                                partEntry.copy(assembly = entryListAssembly,
//                                                        steels = steelService.create(entryListAssembly.parts.single { listPartForSteel ->
//                                                            listPartForSteel.id == partEntry.id
//                                                        }.steels.map { steelEntry ->
//                                                            steelEntry.copy(part = partEntry,
//                                                                    lengthValues = lengthValueService.create(partEntry.steels.single { listSteel ->
//                                                                        listSteel.id == steelEntry.id
//                                                                    }.lengthValues.map { lengthValueEntry -> lengthValueEntry.copy(steel = steelEntry) })
//                                                            )
//                                                        }),
//                                                        concretes = concreteService.create(entryListAssembly.parts.single { listPartForConcrete ->
//                                                            listPartForConcrete.id == partEntry.id
//                                                        }.concretes.map { concreteEntry ->
//                                                            concreteEntry.copy(part = partEntry,
//                                                                    volumeValue = volumeValueService.create(partEntry.concretes.single { listConcrete ->
//                                                                        listConcrete.id == concreteEntry.id
//                                                                    }.volumeValue.map { volumeValueEntry -> volumeValueEntry.copy(concrete = concreteEntry) })
//                                                            )
//                                                        })
//                                                )
//
//                                            }))
//                                        }))
//                            }).apply { mainSectionRepository.flush() }

    //    fun getAllByName(name: String): List<MainSection> = mainSectionRepository.findAllByName(name)
    fun getAll(): List<MainSection> = mainSectionRepository.findAll()

    private fun save(mainSection: MainSection): MainSection {
        return if (!exists(mainSection)) {
            println("mainSectionService save if !exists")
            val newMainSection: MainSection = mainSectionRepository.saveAndFlush(mainSection)
            mainSectionRepository.saveAndFlush(newMainSection.copy(
                    listAssembly = assemblyService.create(newMainSection.listAssembly
                            .map { it.copy(mainSection = newMainSection) })))
        } else update(mainSection)
    }

    private fun update(mainSection: MainSection): MainSection {
//        println("mainSectionService update id=" + getByName(mainSection).id + " name " + getByName(mainSection).name)
        return mainSectionRepository.saveAndFlush(getByName(mainSection).copy(listAssembly = assemblyService.create(mainSection.listAssembly
                .map { it.copy(mainSection = getByName(mainSection)) })))
    }
}

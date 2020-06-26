package com.stroybiz.cumulative.materials.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*

@Entity
@Table
data class MainSection(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @Column(name = "name_main_section")
        val name: String = "",
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "mainSection", fetch = FetchType.LAZY)
        @JsonManagedReference
        var listAssembly: List<Assembly> = mutableListOf()
)

@Entity
@Table
data class Assembly(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        val count: Int = 0,
        var elevation: Int =0,
        val report: Boolean = false,
        val supply: Boolean = false,
        @ManyToOne(cascade = [CascadeType.PERSIST])
        @JsonBackReference
        val mainSection: MainSection? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "assembly", fetch = FetchType.LAZY)
        @JsonManagedReference
        val parts: List<Part> = mutableListOf()
)

@Entity
@Table
data class Part(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "part", fetch = FetchType.LAZY)
        @JsonManagedReference
        val concretes: List<Concrete> = mutableListOf(),
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "part", fetch = FetchType.LAZY)
        @JsonManagedReference
        val steels: List<Steel> = mutableListOf(),
        @ManyToOne
        @JsonBackReference
        var assembly: Assembly? = null,
        val report: Boolean = false,
        val count: Int = 0)

@Entity
@Table
data class Steel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val partPosition: Int = 0,
        val name: String = "",
        val count: Int,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "steel", fetch = FetchType.LAZY)
        @JsonManagedReference
        val lengthValues: List<LengthValue> = mutableListOf(),
        @ManyToOne
        @JsonBackReference
        var part: Part? = null,
        val report: Boolean = false)

@Entity
@Table
data class LengthValue(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val description: String = "",
        val value: Double? = 0.0,
        @ManyToOne
        @JsonBackReference
        val steel: Steel? = null,
        val dateCreate: Date? = null,
        val dateChange: Date? = null)

@Entity
@Table
data class Concrete(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "concrete", fetch = FetchType.LAZY)
        @JsonManagedReference
        val volumeValue: List<VolumeValue> = mutableListOf(),
        @ManyToOne
        @JsonBackReference
        var part: Part? = null,
        val report: Boolean = false)

@Entity
@Table
data class VolumeValue(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val value: Double? = 0.0,
        val description: String = "",
        @ManyToOne
        @JsonBackReference
        val concrete: Concrete? = null,
        val dateCreate: Date? = null,
        val dateChange: Date? = null)





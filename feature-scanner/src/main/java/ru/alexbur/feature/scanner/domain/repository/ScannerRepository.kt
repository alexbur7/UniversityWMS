package ru.alexbur.feature.scanner.domain.repository

interface ScannerRepository {

    suspend fun inventory(barcode: String)
}
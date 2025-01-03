package com.example.api.repository

import com.example.api.model.Mahasiswa
import okio.IOException
import com.example.api.service.MahasiswaService

interface MahasiswaRepository {
    suspend fun getAllMahasiswa(): List<Mahasiswa>
    suspend fun insertMahasiswa (mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa (nim: String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa (nim: String)
    suspend fun getMahasiswaByNim (nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(
    private val mahasiswaApiService: MahasiswaService
) : MahasiswaRepository {
    override suspend fun getAllMahasiswa(): List<Mahasiswa> =
        mahasiswaApiService.getAllMahasiswa()

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        mahasiswaApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = mahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete mahasiswa. HTTP Status code: " +
                            "${response.code()}")
        } else {
            response.message()
            println(response.message())
        }
        } catch (e:Exception) {
            throw e
        }
    }

override suspend fun getMahasiswaByNim(nim: String): Mahasiswa {
    return mahasiswaApiService.getMahasiswaByNim(nim)
}
}
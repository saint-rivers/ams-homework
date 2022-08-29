package com.kshrd.amsfull.service.file

import org.springframework.beans.factory.annotation.Value
import org.springframework.util.StringUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

class StorageException(_message: String) : RuntimeException(_message)

@Service
class FileServiceImpl(
    @Value("\${files.storage.path}")
    private val rootLocation: String
) : FileService {


    fun save(file: MultipartFile) {
        val filename = file.originalFilename?.let { StringUtils.cleanPath(it) }
        val extension = File(filename!!).extension
        try {
            if (file.isEmpty) {
                throw StorageException("Failed to store empty file $filename")
            }
            if (filename != null && filename.contains("..")) {
                // This is a security check
                throw StorageException(
                    "Cannot store file with relative path outside current directory $filename"
                )
            }
        } catch (_: Exception) {
        }

        val path = Paths.get(rootLocation).resolve("${UUID.randomUUID()}.${extension}")
        Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
    }

    override fun upload(files: List<MultipartFile>) {
        files.forEach {
            save(it)
        }
    }
}
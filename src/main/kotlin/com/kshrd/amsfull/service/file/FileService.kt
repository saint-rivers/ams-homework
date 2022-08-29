package com.kshrd.amsfull.service.file

import org.springframework.web.multipart.MultipartFile

interface FileService {

    fun upload(files: List<MultipartFile>)
}
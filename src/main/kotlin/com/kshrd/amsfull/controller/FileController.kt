package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.file.FileService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/files")
class FileController(val fileService: FileService) {
    @GetMapping("/{filename:.+}")
    fun serveFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val file = fileService.loadAsResource(filename)
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
            .body(file)
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(@RequestPart files: List<MultipartFile>): ResponseEntity<ApiResponse> {
        fileService.upload(files)
        return ResponseEntity.ok().body(
            ApiResponse.Success(
                message = "",
                status = "201",
            )
        )
    }
}
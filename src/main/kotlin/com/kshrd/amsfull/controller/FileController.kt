package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.file.FileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.PathResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/files")
class FileController(
    val fileService: FileService,
    @Value("\${server.name}") val url: String,
    @Value("\${files.storage.path}") val storagePath: String,

    ) {
//    @GetMapping("/download/{filename:.+}")
//    fun serveFile(@PathVariable filename: String): ResponseEntity<Resource> {
//        val file = fileService.loadAsResource(filename)
//        return ResponseEntity
//            .ok()
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
//            .body(file)
//    }

    @GetMapping(
        value = ["/view/{filename}"],
        produces = [MediaType.IMAGE_JPEG_VALUE]
    )
    fun getImage(@PathVariable filename: String): ResponseEntity<InputStreamResource> {

        val imgFile = PathResource("${storagePath}/${filename}")

        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(InputStreamResource(imgFile.inputStream))
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(@RequestPart("files") files: Array<MultipartFile>): ResponseEntity<ApiResponse> {
        val data = fileService.upload(files.toList())

        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "successfully uploaded file",
                status = "201",
                payload = data.map { "${url}/api/v1/files/view/${it}" }
            )
        )
    }
}
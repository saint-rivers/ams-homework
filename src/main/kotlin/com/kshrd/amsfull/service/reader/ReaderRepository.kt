package com.kshrd.amsfull.service.reader;

import com.kshrd.amsfull.model.entity.Reader
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReaderRepository : JpaRepository<Reader, UUID> {
}
package com.kshrd.amsfull.exception

import org.springframework.dao.DuplicateKeyException

class UserAlreadyExistsException: DuplicateKeyException("user already exists") {
}
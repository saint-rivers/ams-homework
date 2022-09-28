package com.kshrd.amsfull.exception.user

import org.springframework.dao.DuplicateKeyException

class UserAlreadyExistsException: DuplicateKeyException("user already exists") {
}
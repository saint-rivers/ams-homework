package com.kshrd.amsfull.exception

import org.springframework.dao.DuplicateKeyException

class UserExistsException: DuplicateKeyException("user already exists") {
}
package com.kshrd.amsfull.exception.common

import org.webjars.NotFoundException

class CommonResourceAlreadyExistsException(_resourceName: String): NotFoundException("$_resourceName already exists")

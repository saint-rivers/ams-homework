package com.kshrd.amsfull.exception

import org.webjars.NotFoundException

class GeneralResourceAlreadyExistsException(_resourceName: String): NotFoundException("$_resourceName already exists")

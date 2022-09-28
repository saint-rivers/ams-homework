package com.kshrd.amsfull.exception.common

import org.webjars.NotFoundException

class CommonNotFoundException(_resourceName: String): NotFoundException("$_resourceName not found")

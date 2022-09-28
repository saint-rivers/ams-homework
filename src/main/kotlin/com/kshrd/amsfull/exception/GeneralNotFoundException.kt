package com.kshrd.amsfull.exception

import org.webjars.NotFoundException

class GeneralNotFoundException(_resourceName: String): NotFoundException("$_resourceName not found")

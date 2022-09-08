package com.kshrd.amsfull.service.validator

fun String.isUserName() = this.matches(Regex("^[a-zA-Z0-9 !@#$%^&*)(]{2,20}$"))

fun String.isEmail() = this.matches(Regex("^\\S+@\\S+\\.\\S+\$"))

//fun String.isTelephone() = this.matches(Regex("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}"))

fun String.isUri() = this.matches(Regex("^([a-z0-9+.-]+):(?://(?:((?:[a-z0-9-._~!\$&'()*+,;=:]|%[0-9A-F]{2})*)@)?((?:[a-z0-9-._~!\$&'()*+,;=]|%[0-9A-F]{2})*)(?::(\\d*))?(/(?:[a-z0-9-._~!\$&'()*+,;=:@/]|%[0-9A-F]{2})*)?|(/?(?:[a-z0-9-._~!\$&'()*+,;=:@]|%[0-9A-F]{2})+(?:[a-z0-9-._~!\$&'()*+,;=:@/]|%[0-9A-F]{2})*)?)(?:\\?((?:[a-z0-9-._~!\$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?(?:#((?:[a-z0-9-._~!\$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?"))

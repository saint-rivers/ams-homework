package com.kshrd.amsfull.service.mail

interface EmailService {

    fun sendEmail()

    fun sendSimpleMessage(to: String, subject: String, text: String)
}
package com.fcinar.account.dto

import javax.validation.constraints.NotBlank

data class CreateCustomerRequest(
    @field: NotBlank
    val name: String,
    @field: NotBlank
    val surname: String
)
package dev.queenter.splashscreen.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("phone_number") var phoneNumber: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("user_id") var userId: String,
    var email : String,






)

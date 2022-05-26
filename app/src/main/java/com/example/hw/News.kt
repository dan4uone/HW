package com.example.hw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

data class News(
    val title:String,
    val createdAt:Long,
) : Serializable

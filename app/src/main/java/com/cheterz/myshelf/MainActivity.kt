package com.cheterz.myshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cheterz.myshelf.ui.MainScreen
import com.cheterz.myshelf.ui.items.ItemListScreen
import com.cheterz.myshelf.ui.loans.LoansListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

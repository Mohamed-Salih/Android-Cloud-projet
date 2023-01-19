package com.med.inssetandroidcloud2022.randomUser.view

import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.med.inssetandroidcloud2022.databinding.ActivityRandomUserBinding
import com.med.inssetandroidcloud2022.firebase.view.FirebaseLoginActivity
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserUi
import com.med.inssetandroidcloud2022.randomUser.viewModel.RandomUserViewModel

class RandomUserActivity : AppCompatActivity() {

    private lateinit var viewModel: RandomUserViewModel
    private lateinit var binding: ActivityRandomUserBinding
    private lateinit var adapter: RandomUserAdapter

    private val observer = Observer<List<RandomUserUi>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RandomUserAdapter { item, view ->
            onItemClick(item, view)
        }

        viewModel = ViewModelProvider(this)[RandomUserViewModel::class.java]

        binding.randomUserRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.randomUserRv.adapter = adapter

        binding.randomUserRv

        binding.randomUserAdd.setOnClickListener {
            viewModel.fetchNewUser()
        }

        binding.randomUserDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.randomUserLiveData.observe(this, observer)
    }

    override fun onStop() {
        viewModel.randomUserLiveData.removeObserver(observer)
        super.onStop()
    }

    private fun onItemClick(randomUser: RandomUserUi, view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        with(CustomNotificationManager(this, randomUser)) {
            createNotificationCompatBuilder()
        }
    }
}
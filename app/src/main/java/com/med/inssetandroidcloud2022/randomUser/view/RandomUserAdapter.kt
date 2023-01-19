package com.med.inssetandroidcloud2022.randomUser.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.med.inssetandroidcloud2022.R
import com.med.inssetandroidcloud2022.databinding.ItemCustomRecyclerHeaderBinding
import com.med.inssetandroidcloud2022.databinding.ItemRandomUserBinding
import com.med.inssetandroidcloud2022.randomUser.model.MyObjectForRecyclerView
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserHeader
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserUi

private val diffItemUtils = object : DiffUtil.ItemCallback<MyObjectForRecyclerView>() {

    override fun areItemsTheSame(
        oldItem: MyObjectForRecyclerView,
        newItem: MyObjectForRecyclerView
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MyObjectForRecyclerView,
        newItem: MyObjectForRecyclerView
    ): Boolean {
        return oldItem == newItem
    }
}

class RandomUserAdapter(
    private val onItemClick: (randomUserUi: RandomUserUi, view: View) -> Unit,
) : ListAdapter<MyObjectForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            MyItemType.ROW.type -> {
                AndroidVersionViewHolder(
                    ItemRandomUserBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick
                )
            }
            MyItemType.HEADER.type -> {
                AndroidVersionHeaderViewHolder(
                    ItemCustomRecyclerHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as AndroidVersionViewHolder).bind(getItem(position) as RandomUserUi)
            MyItemType.HEADER.type -> (holder as AndroidVersionHeaderViewHolder).bind(
                getItem(
                    position
                ) as RandomUserHeader
            )
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RandomUserUi -> MyItemType.ROW.type
            is RandomUserHeader -> MyItemType.HEADER.type
        }
    }
}

class AndroidVersionViewHolder(
    private val binding: ItemRandomUserBinding,
    onItemClick: (objectDataSample: RandomUserUi, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: RandomUserUi

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }

    fun bind(randomUserUi: RandomUserUi) {
        ui = randomUserUi
        binding.itemRandomUserEmail.text = randomUserUi.email
        Glide.with(itemView.context)
            .load(randomUserUi.avatarUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.itemRandomUserAvatar)
    }
}

class AndroidVersionHeaderViewHolder(
    private val binding: ItemCustomRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(randomUserHeader: RandomUserHeader) {
        binding.itemRecyclerViewHeader.text = randomUserHeader.header
    }
}

enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1)
}
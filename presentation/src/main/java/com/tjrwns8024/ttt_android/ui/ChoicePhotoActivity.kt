package com.tjrwns8024.ttt_android.ui

import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityChoicePhotoBinding
import com.tjrwns8024.ttt_android.util.EventObserver
import com.tjrwns8024.ttt_android.viewmodel.PhotoViewModel
import splitties.activities.start

class ChoicePhotoActivity : BaseActivity<ActivityChoicePhotoBinding>() {

    override val layoutId: Int = R.layout.activity_choice_photo

    override val viewModel by lazy {
        ViewModelProvider(this).get(PhotoViewModel::class.java)
    }
    override fun observeEvent() {
        with(viewModel){
            backEvent.observe(this@ChoicePhotoActivity, EventObserver {
                this@ChoicePhotoActivity.finish()
            })

            nextEvent.observe(this@ChoicePhotoActivity, EventObserver {
                start<SendPhotoActivity>()
            })
        }
    }
}
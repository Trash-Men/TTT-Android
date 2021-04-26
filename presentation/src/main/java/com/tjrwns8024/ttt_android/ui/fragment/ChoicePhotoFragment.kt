package com.tjrwns8024.ttt_android.ui.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseFragment
import com.tjrwns8024.ttt_android.databinding.FragmentChoicePhotoBinding
import com.tjrwns8024.ttt_android.util.EventObserver
import com.tjrwns8024.ttt_android.viewmodel.PhotoViewModel

class ChoicePhotoFragment : BaseFragment<FragmentChoicePhotoBinding>() {

//    @Inject
//    lateinit var factory: PhotoViewModelFactory

    override val viewModel by lazy {
        ViewModelProvider(this).get(PhotoViewModel::class.java)
    }

    override val layoutId: Int = R.layout.fragment_choice_photo

    override fun observeEvents() {
        with(viewModel){
            backEvent.observe(this@ChoicePhotoFragment, EventObserver {
                requireActivity().finish()
            })

            nextEvent.observe(this@ChoicePhotoFragment, EventObserver {
                findNavController().navigate(R.id.action_choicePhoto_to_sendPhoto)
            })
        }
    }
}
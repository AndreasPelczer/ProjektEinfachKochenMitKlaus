package com.example.einloggohnegoogle.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.adapter.TipAdapter
import com.example.einloggohnegoogle.databinding.FragmentTipBinding
import com.example.einloggohnegoogle.viewModels.TipViewModel
import com.example.einloggohnegoogle.viewModels.TipViewModelFactory

class TipFragment : Fragment() {

    private val viewModel: TipViewModel by viewModels { TipViewModelFactory(requireActivity().application, 10) }
    private lateinit var binding: FragmentTipBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadData()

        // Lade das Video
        val videoView3 = createVideoView("https://youtu.be/0IxQ16bPzak?si=vcGyShf4njT0hkW6")

        binding = FragmentTipBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createVideoView(videoUrl: String): VideoView {
        val videoView = VideoView(requireContext())
        val videoUri = Uri.parse(videoUrl)
        videoView.setVideoURI(videoUri)
        videoView.start()
        return videoView
    }

    private fun extractVideoId(youtubeUrl: String): String {
        val pattern = "(?<=watch\\?v=|/videos/|embed/|youtu.be/|/v/|/e/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2Fvideos\u200C\u200B|youtu.be%2F)[^#&?\\n]*"
        val compiledPattern = Regex(pattern)
        val matcher = compiledPattern.find(youtubeUrl)
        return matcher?.value ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.tipBTN)
        imageView.setImageResource(R.drawable.ede9aab6043917b249a4aec7abc0ea5e)

        binding.refreshBTN.setOnClickListener {
            viewModel.loadData()
        }
        binding.itemRv.setHasFixedSize(true)

        viewModel.items.observe(viewLifecycleOwner) { items ->
            Log.d("Fragment Lifecycle", "onViewCreated invoked")
            binding.itemRv.adapter = TipAdapter(items)
            binding.itemRv.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.tipbackBTN.setOnClickListener {
            Log.d("Button Click", "Button clicked")
            findNavController().navigate(R.id.dataFragment)
        }
    }
}

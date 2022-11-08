package com.auf.cea.recyclerviewactivity.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.auf.cea.recyclerviewactivity.R
import com.auf.cea.recyclerviewactivity.databinding.FragmentDetailsBinding
import com.auf.cea.recyclerviewactivity.models.BooksModel

private const val ARG_BOOK_DETAILS = "book_model"

class DetailsFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var bookData: BooksModel?= null
    private lateinit var binding : FragmentDetailsBinding
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookData = it.getSerializable(ARG_BOOK_DETAILS) as? BooksModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtAuthor.text = String.format("by: %s",bookData?.author)
        binding.txtTitle.text = bookData?.name
        binding.txtYear.text = String.format("(%s)", bookData?.datePublished)
        binding.txtBookDescription.text = bookData?.shortDescription

        binding.btnDismiss.setOnClickListener{
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: BooksModel) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_BOOK_DETAILS, param1)
                }
            }
    }
}
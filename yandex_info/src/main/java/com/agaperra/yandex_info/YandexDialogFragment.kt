package com.agaperra.yandex_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agaperra.professionaldevelopment.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textview.MaterialTextView

class YandexDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.yandex_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<MaterialTextView>(R.id.yandexTV).text = resources.getString(R.string.yandexnv)
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): YandexDialogFragment {
            return YandexDialogFragment()
        }
    }
}

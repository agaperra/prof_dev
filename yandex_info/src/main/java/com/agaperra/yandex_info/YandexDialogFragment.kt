package com.agaperra.yandex_info

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class YandexDialogFragment : BottomSheetDialogFragment() {


    companion object {
        fun newInstance(): YandexDialogFragment {
            return YandexDialogFragment()
        }
    }
}

package com.mozhimen.scank.mlkit.text.chinese

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions
import com.mozhimen.basick.elemk.androidx.lifecycle.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.basick.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.basick.lintk.optins.OApiInit_ByLazy
import com.mozhimen.scank.mlkit.text.chinese.commons.ITextRecognizeListener

/**
 * @ClassName TextRecognizerProxy
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/2/13 22:57
 * @Version 1.0
 */
@OApiInit_ByLazy
class TextRecognizerProxy {
    private val _textRecognizer: TextRecognizer = TextRecognition.getClient(ChineseTextRecognizerOptions.Builder().build())
    private var _textRecognizeListener: ITextRecognizeListener? = null

    fun init(listener: ITextRecognizeListener) {
        _textRecognizeListener = listener
    }

    fun textRecognize(bitmap: Bitmap, degree: Int) {
        val taskText: Task<Text> = _textRecognizer.process(InputImage.fromBitmap(bitmap, degree))
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
                _textRecognizeListener?.invoke(visionText)
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                _textRecognizeListener?.invoke(null)
            }
    }
}
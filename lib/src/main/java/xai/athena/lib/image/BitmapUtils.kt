package xai.athena.lib.image

import android.content.ContentResolver
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

/**
 * Merges two bitmaps by drawing them on a canvas
 *
 * Source: https://stackoverflow.com/a/40546729
 */
internal fun mergeBitmaps(bmp1: Bitmap, bmp2: Bitmap): Bitmap {
    val merged = Bitmap.createBitmap(bmp1.width, bmp1.height, bmp1.config)
    val canvas = Canvas(merged)
    canvas.drawBitmap(bmp1, Matrix(), null)
    canvas.drawBitmap(bmp2, Matrix(), null)
    return merged
}

/**
 * Resizes a bitmap to the given height and width
 */
internal fun resizeBitmap(bmp: Bitmap, width: Int, height: Int): Bitmap {
    return Bitmap.createScaledBitmap(bmp, width, height, false)
}

/**
 * Resizes a bitmap to the given width and calculates height with respect to the aspect ratio
 *
 * Source: https://stackoverflow.com/a/28921075
 */
internal fun resizeBitmapWithAspect(bmp: Bitmap, width: Int): Bitmap {
    val aspectRatio: Float = bmp.width / bmp.height.toFloat()
    val height = (width / aspectRatio).roundToInt()
    return resizeBitmap(bmp, width, height)
}

/**
 * Loads a bitmap from an URI
 */
internal fun getBitmapFromUri(contentResolver: ContentResolver, imageUri: Uri): Bitmap {
    val b: Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(contentResolver, imageUri)
        ImageDecoder.decodeBitmap(source)
    } else {
        MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
    }
    return b
}


/**
 * If the bitmap is horizontal and its width is < resizeDimens, returns it at it is, if it is > resizeDimens resize it to resizeDimens with scaled height
 * If the bitmap is vertical and its height is < resizeDimens, returns it at it is, if it is > resizeDimens resize it to resizeDimens with scaled width
 * @param bm - original bitmap
 * @param resizeDimens - dimensions
 * @return resized bitmap
 */
fun getResizedBitmap(bm: Bitmap, resizeDimens: Int): Bitmap {
    val width = bm.width
    val height = bm.height
    val aspectRatio = width / height.toFloat()
    val scaleWidth: Int
    val scaleHeight: Int

    // horizontal image
    if (width > height) {
        if (width < resizeDimens) return bm
        scaleWidth = resizeDimens
        scaleHeight = (scaleWidth / aspectRatio).roundToInt()
    } else if (height > width) {
        if (height < resizeDimens) return bm
        scaleHeight = resizeDimens
        scaleWidth = (scaleHeight * aspectRatio).roundToInt()
    } else {
        if (width < resizeDimens) return bm
        scaleWidth = resizeDimens
        scaleHeight = resizeDimens
    }
    // Create resized bitmap
    return Bitmap.createScaledBitmap(bm, scaleWidth, scaleHeight, false)
}

/**
 * Loads a bitmap from resources
 */
internal fun getBitmapFromRes(res: Resources, id: Int, width: Int): Bitmap {
    val bmp = BitmapFactory.decodeResource(res, id)
    return resizeBitmapWithAspect(bmp, width)
}

/**
 * Compress bitmap with quality 0-100 (80 default)
 */
internal fun compressBitmap(bitmap: Bitmap, quality: Int = 80): Bitmap {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bytes)
    return BitmapFactory.decodeStream(ByteArrayInputStream(bytes.toByteArray()))
}

internal fun convertBitmapToBase64(imageBitmap: Bitmap): String {
    val stream = ByteArrayOutputStream()
    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val byteArray = stream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

internal fun convertBase64ToBitmap(base64String: String?): Bitmap? {
    val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}
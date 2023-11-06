package com.projectbyjanconnect.imageeditor.repositories

import android.content.Context
import android.provider.MediaStore.Images.Media
import com.projectbyjanconnect.imageeditor.model.GalleryImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GalleryRepository {

    private val imagesList = mutableListOf<GalleryImage>()

    suspend fun loadImages(context: Context) = withContext(Dispatchers.IO) {
        if (imagesList.isEmpty()) {
            val contentResolver = context.contentResolver
            val externalContentUrl = Media.EXTERNAL_CONTENT_URI
            val internalContentUrl = Media.INTERNAL_CONTENT_URI

            val projectionId = Media._ID
            val projectionDate = Media.DATE_TAKEN
            val projectionName = Media.DISPLAY_NAME
            val projectionUri = Media.DATA
            val arrayOfProjections =
                arrayOf(projectionId, projectionDate, projectionName, projectionUri)
            val cursor1 =
                contentResolver.query(externalContentUrl, arrayOfProjections, null, null, null)
            val cursor2 =
                contentResolver.query(internalContentUrl, arrayOfProjections, null, null, null)

            cursor1?.let {
                if (cursor1.moveToFirst()) {
                    do {
                        val columnIndexForId = cursor1.getColumnIndex(projectionId)
                        val columnIndexForName = cursor1.getColumnIndex(projectionName)
                        val columnIndexForDate = cursor1.getColumnIndex(projectionDate)
                        val columnIndexForUri = cursor1.getColumnIndex(projectionUri)
                        val id = cursor1.getInt(columnIndexForId)
                        val name = cursor1.getString(columnIndexForName)
                        val uri = cursor1.getString(columnIndexForUri)
                        val date = cursor1.getLong(columnIndexForDate)
                        val galleryImage = GalleryImage(
                            id = id,
                            name = name,
                            url = uri,
                            dateLong = date
                        )
                        imagesList.add(galleryImage)
                    } while (cursor1.moveToNext())
                }
            }

            cursor2?.let {
                if (cursor2.moveToFirst()) {
                    do {
                        val columnIndexForId = cursor2.getColumnIndex(projectionId)
                        val columnIndexForName = cursor2.getColumnIndex(projectionName)
                        val columnIndexForDate = cursor2.getColumnIndex(projectionDate)
                        val columnIndexForUri = cursor2.getColumnIndex(projectionUri)
                        val id = cursor2.getInt(columnIndexForId)
                        val name = cursor2.getString(columnIndexForName)
                        val uri = cursor2.getString(columnIndexForUri)
                        val date = cursor2.getLong(columnIndexForDate)
                        val galleryImage = GalleryImage(
                            id = id,
                            name = name,
                            url = uri,
                            dateLong = date
                        )
                        imagesList.add(galleryImage)
                    } while (cursor2.moveToNext())
                }
            }
            imagesList.sortBy { it.id }
            imagesList
        }else{
            imagesList
        }
    }


}
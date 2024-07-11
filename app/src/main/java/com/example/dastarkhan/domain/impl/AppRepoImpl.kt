package com.example.dastarkhan.domain.impl

import com.example.dastarkhan.data.FoodData
import com.example.dastarkhan.data.ImageData
import com.example.dastarkhan.data.TxtData
import com.example.dastarkhan.domain.AppRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepoImpl @Inject constructor() : AppRepo {
    private val fireStore = FirebaseFirestore.getInstance()
    override fun getFoodData(): Flow<Result<List<FoodData>>> = callbackFlow {
        val dataList = ArrayList<FoodData>()
        fireStore.collection("foods")
            .get()
            .addOnSuccessListener {
                it.documents.onEach {
                    dataList.add(
                        FoodData(
                            name = it.data!!.getOrDefault("name", "") as String,
                            img = it.data!!.getOrDefault("img", "") as String,
                            history = it.data!!.getOrDefault("history", "") as String,
                            ingredients = it.data!!.getOrDefault("ingredients", "") as String,
                            extra = it.data!!.getOrDefault("extra", "") as String,
                            preparation = it.data!!.getOrDefault("preparation", "") as String,
                            img2 = it.data!!.getOrDefault("img2", "") as String
                        )
                    )
                }
                trySend(Result.success(dataList))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getImages(): Flow<Result<List<ImageData>>> = callbackFlow {
        val imgList = ArrayList<ImageData>()
        fireStore.collection("images")
            .get()
            .addOnSuccessListener {
                it.documents.forEach {
                 //   imgList.add(ImageData(img = it.data!!.getOrDefault("img", "") as String))
                }
                trySend(Result.success(imgList))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()


    }

    override fun getTxt(): Flow<Result<List<TxtData>>> = callbackFlow {
        val list = ArrayList<TxtData>()
        fireStore.collection("fabric")
            .get()
            .addOnSuccessListener {
                it.documents.forEach {
                    list.add(
                        TxtData(
                            name = it.data!!.getOrDefault("name", "")as String,
                            img = it.data!!.getOrDefault("img", "") as String,
                            description = it.data!!.getOrDefault("description", "") as String,
                            history = it.data!!.getOrDefault("history", "") as String
                        ),
                    )

                }
                trySend(Result.success(list))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
}
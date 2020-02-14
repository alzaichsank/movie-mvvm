package id.alik.movie_mvvm.service

import id.alik.movie_mvvm.data.server.ApiService
import id.alik.movie_mvvm.data.server.ApiServiceManager

object RestRepository {

    fun createApiRepository(): ApiServiceManager {
        val repoApi = RestUtil.instance.retrofit.create(ApiService::class.java)
        return ApiServiceManager(repoApi)
    }
}


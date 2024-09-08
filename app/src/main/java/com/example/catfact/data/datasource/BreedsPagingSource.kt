package com.example.catfact.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catfact.data.model.catbread.Data
import com.example.catfact.data.repository.Repository
import javax.inject.Inject

class BreedsPagingSource @Inject constructor(
    private val repository: Repository
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 1
            val response = repository.getBreeds(page)

            if (response.isSuccessful) {
                val breads = response.body()
                LoadResult.Page(
                    data = breads!!.data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (breads.data.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
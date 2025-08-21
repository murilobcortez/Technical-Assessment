package com.example.comics.interactor

import com.example.comics.presenter.IPresenter
import com.example.comics.repository.Repository
import com.example.comics.util.data.safeRunDispatcher
import com.example.comics.util.data.Result.Success
import com.example.comics.util.data.Result.Failure

class Interactor(
    private val iPresenter: IPresenter,
    private val repository: Repository = Repository()
) : IInteractor {


    override suspend fun getComics() {
        when (val result = safeRunDispatcher {
            repository.getComics()
        }) {
            is Success -> iPresenter.setupList(result.data)
            is Failure -> iPresenter.error()
        }
    }

}
package com.riane.auth.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riane.auth.usecase.LoginByPasswordUseCase
import com.xiamu.wanandroid.mvvm.model.entry.LoginBean
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginByPasswordUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

//    val recentSearchQueriesUiState: StateFlow<RecentSearchQueriesUiState> =
//        recentSearchQueriesUseCase()
//            .map(RecentSearchQueriesUiState::Success)
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = RecentSearchQueriesUiState.Loading,
//            )

    fun login(username:String, password:String){
        _uiState.value = LoginUiState.Loading
        viewModelScope.launch() {
            loginUseCase(username, password).onSuccess{
                _uiState.value = LoginUiState.Success(it)
            }.onFailure{
                _uiState.value = LoginUiState.Error
            }
        }

    }

//    @AssistedFactory
//    interface Factory {
//        fun create(
//            topicId: String,
//        ): AuthViewModel
//    }

}

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data class Success(val loginBean: LoginBean) : LoginUiState
    data object Error : LoginUiState
    data object Loading : LoginUiState
}
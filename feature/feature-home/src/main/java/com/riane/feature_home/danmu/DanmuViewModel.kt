package com.riane.feature_home.danmu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class DanmuViewModel : ViewModel(){

    private val repo = DanmuRepository()
    private val _danmus = MutableStateFlow<List<DanmuItem>>(emptyList())
    val danmus : StateFlow<List<DanmuItem>> = _danmus.asStateFlow()

    init {
        viewModelScope.launch {
            while (true){
                delay((300 + Random.nextInt(100)).toLong())
                repo.emitServerDanmu(DanmuItem(text = randomDanmuText()))
            }
        }

        viewModelScope.launch {
            repo.getDanmuStream().collect{newDanmu ->
                _danmus.update { it + newDanmu }
            }
        }

        viewModelScope.launch {
            delay(16)
            _danmus.update { current->
                current.map {
                    it.copy(offsetX = it.offsetX - 0.002f * it.speed)
                }.filter { it.offsetX > -0.5f }
            }
        }
    }

    fun sendLocalDanmu(text: String){
        viewModelScope.launch {
            repo.emitLocalDanmu(DanmuItem(text = text))
        }
    }

    private fun randomDanmuText(): String{
        val greetings = listOf("来了来了", "前排", "打卡", "签到", "第一！", "强到没朋友")
        val reactions = listOf("666", "太强了", "哈哈哈", "awsl", "好活", "泪目")
        val questions = listOf("有人吗？", "这是哪？", "主播多大了？", "几点开播？")
        val emotes = listOf("(≧∇≦)ﾉ", "(╯‵□′)╯", "╮(╯▽╰)╭", "(❤ ω ❤)", "(￣▽￣*)ゞ")
        val memes = listOf("一键三连", "下次一定", "白嫖", "老板大气", "感谢飞机")

        return when((1..5).random()){
            1 -> greetings.random()
            2 -> reactions.random() + listOf("", "！！", "~").random()
            3 -> questions.random()
            4 -> emotes.random() + " " + reactions.random()
            else -> memes.random() + listOf("", "！", "！！！").random()
        }

    }
}
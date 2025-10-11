package com.riane.feature_home.danmu

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.merge

class DanmuRepository {
    private val serverChannel = Channel<DanmuItem> { Channel.UNLIMITED }
    private val localChannel = Channel<DanmuItem>{Channel.UNLIMITED}

    suspend fun emitServerDanmu(item: DanmuItem){
        serverChannel.send(item.copy(isLocal = false))
    }

    suspend fun emitLocalDanmu(item: DanmuItem){
        localChannel.send(item.copy(isLocal = true))
    }

    fun getDanmuStream() : Flow<DanmuItem>{
        return merge(serverChannel.consumeAsFlow(), localChannel.consumeAsFlow())
    }
}
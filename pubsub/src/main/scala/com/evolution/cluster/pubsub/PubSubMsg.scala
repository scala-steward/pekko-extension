package com.evolution.cluster.pubsub

import com.evolution.serialization.SerializedMsg

final case class PubSubMsg(serializedMsg: SerializedMsg, timestamp: Long)

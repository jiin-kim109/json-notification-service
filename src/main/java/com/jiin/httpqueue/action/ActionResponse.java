package com.jiin.httpqueue.action;

public abstract sealed class ActionResponse permits CreateQueue, ReceiveMessage {
}

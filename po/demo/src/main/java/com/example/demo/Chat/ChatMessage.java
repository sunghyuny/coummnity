package com.example.demo.Chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private String content;  // 메시지 내용
    private String sender;   // 메시지 보낸 사람
    private String roomId;   // 채팅방 ID

    public ChatMessage(String content, String sender, String roomId) {
        this.content = content;
        this.sender = sender;
        this.roomId = roomId;
    }

    public String getRoomId(){
        return roomId;
    }
}

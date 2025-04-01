package com.example.demo.Chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {

    private String roomId;   // 채팅방 ID (고유 식별자)
    private String name;     // 채팅방 이름
    private String createdBy; // 생성자 (사용자 이름)
    private int participantCount; // 참가자 수

    public ChatRoom(String roomId, String name, String createdBy) {
        this.roomId = roomId;
        this.name = name;
        this.createdBy = createdBy;
        this.participantCount = 1; // 최초 생성자는 참가자 수 1명
    }

    // 참가자 수를 증가시키는 메서드
    public void addParticipant() {
        this.participantCount++;
    }

    // 참가자 수를 감소시키는 메서드
    public void removeParticipant() {
        if (this.participantCount > 0) {
            this.participantCount--;
        }
    }
}

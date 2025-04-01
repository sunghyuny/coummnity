//package com.example.demo.Chat;
//
//import com.example.demo.Chat.ChatMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//
//@Controller
//public class ChatController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    public ChatController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    // 채팅방에 메시지 전송
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/messages")
//    public ChatMessage sendMessage(ChatMessage message) {
//        return message;
//    }
//
//    // 입력 중 상태 처리 (예: ... 표시)
//    @MessageMapping("/chat.typing")
//    @SendTo("/topic/typing")
//    public String typing(ChatMessage message) {
//        return message.getSender() + " is typing...";
//    }
//
//    // 클라이언트에게 메시지를 전송 (특정 채팅방에 메시지 보내기)
//    public void sendMessageToRoom(ChatMessage message) {
//        messagingTemplate.convertAndSend("/topic/room/" + message.getRoomId(), message);
//    }
//}

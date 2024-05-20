package com.codemon.securedoc.event.listener;

import com.codemon.securedoc.event.UserEvent;
import com.codemon.securedoc.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event){
        switch (event.getType()){
            case REGISTRATION -> emailService.sendNewAccountEmail(event.getUser().getFirstName(),
                    event.getUser().getEmail(), (String)event.getData().get("key"));
            case RESETPASSWORD -> emailService.sendPasswordResetEmail(event.getUser().getFirstName(),
                    event.getUser().getEmail(), (String)event.getData().get("key"));
            default -> {}
        }
    }
}

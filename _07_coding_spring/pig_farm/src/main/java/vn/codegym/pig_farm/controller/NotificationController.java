package vn.codegym.pig_farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.pig_farm.entity.Notification;
import vn.codegym.pig_farm.service.INotificationService;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    INotificationService iNotificationService;

    /**
     * Created by: DatLT
     * Date created: 08/09/2022
     * Function: Display all news list by keyword with pagination
     * @param pageable pageable
     * @param keyword keyword
     * @return HTTP status code 200 (OK): return Page<Notification> notifications
     * HTTP status code 204 (NO_CONTENT): return notifications is empty
     */

    @GetMapping
    public ResponseEntity<Page<Notification>> findAll(@PageableDefault(value = 5) Pageable pageable, @RequestParam Optional<String> keyword) {
        Page<Notification> notifications = iNotificationService.findAll(pageable, keyword.orElse(""));
        if (notifications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}

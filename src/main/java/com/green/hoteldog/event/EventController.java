package com.green.hoteldog.event;

import com.green.hoteldog.event.model.EventInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class EventController {
    private final EventService eventService;
    @GetMapping()
    public List<EventInfoVo> getEvent(){
        List<EventInfoVo> getHotelEvents;
        try{
            getHotelEvents=eventService.getEvent();
            int eventsListSize=getHotelEvents.size();
        }catch (Exception e){
            return null;//Exception 후처리 요함.
        }
        return getHotelEvents;
    }
    //승준
}

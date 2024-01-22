package com.green.hoteldog.event;
import com.green.hoteldog.event.model.EventInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventMapper eventMapper;
    public List<EventInfoVo> getEvent(){
        List<EventInfoVo> getHotelEvents=null;
        try{
            getHotelEvents=eventMapper.getEvents();
            int eventsListSize=getHotelEvents.size();
        }catch (Exception e){
            return null;//Exception 후처리 요함.
        }
        return getHotelEvents;
    }
    //승준
}

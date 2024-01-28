package com.green.hoteldog.hotel;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.hotel.model.HotelInfoEntity;
import com.green.hoteldog.hotel.model.HotelMainPageDto;
import com.green.hoteldog.hotel.model.HotelRoomEaByDate;
import com.green.hoteldog.user.models.UserHotelFavDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HotelControllerTest {
    @InjectMocks
    private HotelController hotelController;
    @Mock
    private HotelService hotelService;

    @Test
    public void testHotelEntity(){
        HotelMainPageDto dto=new HotelMainPageDto();
        dto.setHotelPk(1);
        dto.setUserPk(1);
        HotelInfoEntity expectEntity=new HotelInfoEntity();
        when(hotelService.getHotelDetail(dto)).thenReturn(expectEntity);

        HotelInfoEntity resultEntity=hotelController.getHotelDetail(dto);
        assertThat(resultEntity,is(equalTo(expectEntity)));
    }
    @Test
    public void testHotelDetail(){
        List<Integer> dogs= Arrays.asList(1,2,3,4);
        int hotelPk=1;
        String startDate="2023-01-01";
        String endDate="2023-02-25";

        List<HotelRoomEaByDate> expectList=Arrays.asList(new HotelRoomEaByDate(),new HotelRoomEaByDate());
        when(hotelService.whenYouChooseDatesAndDogs(hotelPk,startDate,endDate,dogs)).thenReturn(expectList);

        List<HotelRoomEaByDate> resultDates=hotelController.whenYouChooseDatesAndDogs(hotelPk,startDate,endDate,dogs);
        assertThat(resultDates,is(equalTo(expectList)));

        List<HotelRoomEaByDate> expectFinalDetail=Arrays.asList(new HotelRoomEaByDate(),new HotelRoomEaByDate());
        when(hotelService.whenYouChooseDates(hotelPk,startDate,endDate)).thenReturn(expectFinalDetail);
        List<HotelRoomEaByDate> resultFinalDetail=hotelController.whenYouChooseDates(hotelPk,startDate,endDate);
        assertThat(resultFinalDetail,is(equalTo(expectFinalDetail)));

    }
    @Test
    public void testToggleHotelBookMark() {

        UserHotelFavDto dto = new UserHotelFavDto();
        dto.setHotelPk(1);
        dto.setUserPk(1);
        ResVo expectedResult = new ResVo(1);
        when(hotelService.toggleHotelBookMark(dto)).thenReturn(expectedResult);

        ResVo result = hotelController.toggleHotelBookMark(dto);
        assertThat(result,is(equalTo(expectedResult)));
    }
}

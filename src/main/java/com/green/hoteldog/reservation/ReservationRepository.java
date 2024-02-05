package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ReservationRepository implements ReservationRepositoryRef{
    private final ReservationMapper reservationMapper;
    public Integer insHotelReservation(HotelReservationInsDto dto) {
        return reservationMapper.insHotelReservation(dto);
    }
    public Integer insHotelReservationDogInfo(DogInfo info) {
        return reservationMapper.insHotelReservationDogInfo(info);
    }
    public Integer insHotelReservationInfo(HotelReservationInsDto dto) {
        return reservationMapper.insHotelReservationInfo(dto);
    }
    public Integer updRemainedHotelRoom(List<HotelReservationUpdProcDto> dto) {
        return reservationMapper.updRemainedHotelRoom(dto);
    }
    public List<HotelReservationSelProcVo> selHotelReservation(HotelReservationDelDto dto) {
        return reservationMapper.selHotelReservation(dto);
    }
    public List<Integer> selHotelRoomPk(HotelReservationDelDto dto) {
        return reservationMapper.selHotelRoomPk(dto);
    }
    public Integer delHotelReservationInfo(HotelReservationDelDto dto) {
        return reservationMapper.delHotelReservationInfo(dto);
    }
    public Integer delHotelReservationDogInfo(HotelReservationDelDto dto) {
        return reservationMapper.delHotelReservationDogInfo(dto);
    }
    public Integer delHotelReservation(HotelReservationDelDto dto) {
        return reservationMapper.delHotelReservation(dto);
    }
    public Integer updRemainedHotelRoom2(HotelReservationUpdProc2Dto dto) {
        return reservationMapper.updRemainedHotelRoom2(dto);
    }
    public List<ResHotelPicVo> getHotelResPic(List<Integer> userPk){
        return reservationMapper.getHotelResPic(userPk);
    }
    public List<ResInfoVo> getUserReservation(int userPk, int perPage, int pages) {
        return reservationMapper.getUserReservation(userPk, perPage, pages);
    }
    public List<ResDogInfoVo> getDogInfoReservation(List<Integer> resPkList) {
        return reservationMapper.getDogInfoReservation(resPkList);
    }
    public List<Integer> getHotelPk(int userPk){
        return reservationMapper.getHotelPk(userPk);
    }
}

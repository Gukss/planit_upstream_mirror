package com.project.planit.storage.dto;

import com.project.planit.storage.entity.Category;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.storage.dto
 * fileName       : CreateStorageResponse
 * author         : dongk
 * date           : 2023-02-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-04        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class CreateStorageResponse {
    @NotNull
    private Long memberId;

    @NotNull
    private Long storagesId;

    @NotNull
    private String name;

    @NotNull
    private Boolean confirmed;

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

    @NotNull
    private String dayOrder;
    @NotNull
    private Long indexOrder;

    @NotNull
    private Category categoryName;

    @NotNull
    private Long roomId;

    public static CreateStorageResponse create(Long memberId, Long storagesId, String name, Boolean confirmed, Double lat, Double lng, String dayOrder,Long indexOrder, Category categoryName, Long roomId) {
        return CreateStorageResponse.builder()
                .memberId(memberId)
                .storagesId(storagesId)
                .name(name)
                .confirmed(confirmed)
                .lat(lat)
                .lng(lng)
                .dayOrder(dayOrder)
                .indexOrder(indexOrder)
                .categoryName(categoryName)
                .roomId(roomId)
                .build();
    }
}

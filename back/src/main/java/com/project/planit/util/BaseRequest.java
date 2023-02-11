package com.project.planit.util;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.util
 * fileName       : BaseDto
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Embeddable
@AllArgsConstructor
@Builder
public class BaseRequest {
    //생성자
    @NotNull
    private String constructor;

    //수정자
    @NotNull
    private String updater;
}

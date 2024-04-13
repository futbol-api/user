package com.co.futbolapi.user.models.dtos.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class GetUserRsDTO {
    private UUID id;
    private String nickname;
}

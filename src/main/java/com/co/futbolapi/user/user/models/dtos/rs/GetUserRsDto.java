package com.co.futbolapi.user.user.models.dtos.rs;


import lombok.Builder;

import java.util.UUID;

@Builder
public record  GetUserRsDto (UUID id, String nickname) {

}

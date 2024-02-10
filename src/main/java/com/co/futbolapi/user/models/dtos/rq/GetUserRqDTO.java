package com.co.futbolapi.user.models.dtos.rq;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@ToString
public class GetUserRqDTO {



    /**
     * unique nickname to identify the user.
     */
    private UUID id;
}

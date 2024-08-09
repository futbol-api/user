package com.co.futbolapi.user.team.models.dtos.rq;

import lombok.Builder;

@Builder
public record CreateTeamRqDto (String name){
    //Record es nuevo tipo de classes que se creo en JAVA 17 -> programación funcional
    //El resto de anotaciones no son necesarias puesto que esta nueva clase los toma por defecto

}

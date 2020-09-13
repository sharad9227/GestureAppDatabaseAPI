package com.example.demo.dto;

import com.example.demo.entities.UserdataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestObject {
    private UserdataEntity user;

}

package br.com.bnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasicRequestDTO  {

    protected LocalDateTime createAt;
    protected LocalDateTime updateAt;


}

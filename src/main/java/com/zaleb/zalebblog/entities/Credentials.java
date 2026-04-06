package com.zaleb.zalebblog.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Credentials {

    private String username;

    private String password;
}

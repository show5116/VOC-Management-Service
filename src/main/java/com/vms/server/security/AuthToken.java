package com.vms.server.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthToken {

    private String id;
    private String plant;
    //private boolean isSubcontractor;
}

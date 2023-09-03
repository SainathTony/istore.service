package com.istore.service.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class Error {
    private String errorMessage;
    private String moreInfo;
}

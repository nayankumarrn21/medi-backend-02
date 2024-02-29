package com.neudesic.MediAssistsPolicyService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@EqualsAndHashCode(callSuper = true)
@Getter
@Data
@AllArgsConstructor
public class BindingResultException extends RuntimeException {

    BindingResult bindingResult;

    public BindingResultException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public BindingResultException() {
       super();
    }
}

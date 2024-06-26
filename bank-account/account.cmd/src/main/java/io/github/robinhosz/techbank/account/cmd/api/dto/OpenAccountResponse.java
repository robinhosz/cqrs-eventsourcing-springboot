package io.github.robinhosz.techbank.account.cmd.api.dto;

import io.github.robinhosz.techbank.account.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id) {
       super(message);
       this.id = id;
    }
}

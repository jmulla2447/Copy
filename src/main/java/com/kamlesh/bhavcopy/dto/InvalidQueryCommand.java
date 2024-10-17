package com.kamlesh.bhavcopy.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvalidQueryCommand {
    private String errMsg;
    private String businessCode;
    private String uriInfo;

}

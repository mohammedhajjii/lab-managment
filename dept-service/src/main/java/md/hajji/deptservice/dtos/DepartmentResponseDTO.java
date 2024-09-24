package md.hajji.deptservice.dtos;

import lombok.*;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class DepartmentResponseDTO {
    private String id;
    private String name;
    private String code;
    private long creationTimestamp;
}

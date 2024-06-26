package fit.edu.tmdt.shoes_store_api.dto.User;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private Instant createdAt;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private String avatar;
    private SupportDTO status;
    private SupportDTO role;
}

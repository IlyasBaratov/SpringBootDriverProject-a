package democom.example.thymeleafdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "driver")
@NoArgsConstructor
@Getter
@Setter
public class  Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "First name is required")
    @Size(min = 2, message = "First name must have at least 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 2, message = "Last name must have at least 2 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Column(name = "dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob; // Changed to LocalDate

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(name = "phone_number")
    private String phoneNumber; // Changed to String to support leading zeros

    public Driver(String firstName, String lastName, LocalDate dob, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

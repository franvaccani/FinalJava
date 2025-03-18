package darktower.solarflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClientDTO {

    @NotBlank(message = "The name cannot be empty")
    @Size(min = 2, max = 75, message = "The name must be between 2 and 75 characters")
    private String name;

    @NotBlank(message = "The last name cannot be empty")
    @Size(min = 2, max = 75, message = "The last name must be between 2 and 75 characters long")
    private String lastname;

    @NotBlank(message = "The document number cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "The document number must have 11 digits")
    private String docnumber;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getDocnumber() { return docnumber; }
    public void setDocnumber(String docnumber) { this.docnumber = docnumber; }
}

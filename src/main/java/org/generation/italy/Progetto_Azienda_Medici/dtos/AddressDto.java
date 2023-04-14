package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Address;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    private long id;
    private String street;
    private String cap;
    private String city;
    private String province;
    private String country;

    public AddressDto(long id, String street, String cap, String city,
                      String province, String country) {
        this.id = id;
        this.street = street;
        this.cap = cap;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public static AddressDto fromAddress(Address address) {
        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getCap(),
                address.getCity(),
                address.getProvince(),
                address.getCountry()
        );
    }

    public Address toAddress() {
        return new Address(
                this.getId(),
                this.getStreet(),
                this.getCap(),
                this.getCity(),
                this.getProvince(),
                this.getCountry()
        );
    }
}

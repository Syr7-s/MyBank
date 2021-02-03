package org.kodluyoruz.mybank.creditcardshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.card.creditcard.entity.CreditCard;
import org.kodluyoruz.mybank.creditcardshopping.dto.ShoppingDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    private String productType;
    private String productName;
    private int productPrice;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate productReceiveDate;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "creditCard_no")
    private CreditCard creditCard;

    public ShoppingDto toShoppingDto(){
        return ShoppingDto.builder()
                .productID(this.productID)
                .productType(this.productType)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productReceiveDate(this.productReceiveDate)
                .creditCard(this.creditCard)
                .build();
    }



}
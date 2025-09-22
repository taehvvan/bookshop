package pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PayBagBook {
    private int b_id;      // b_id 매핑?��
    private String title;
    private String pic;
    private int quantity;
    private int price;
}
package api_practice.kuCoinApi;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder     // два конструктор , со всеми полями и пустой
@Getter
@Setter
public class TickerData {
    private String symbol;
    private String symbolName;
    private String buy;
    private String sell;
    private String changeRate;
    private String changePrice;
    private String high;
    private String low;
    public String vol;
    private String volValue;
    private String last;
    private String averagePrice;
    private String takerFeeRate;
    private String makerFeeRate;
    private String takerCoefficient;
    private String makerCoefficient;
}

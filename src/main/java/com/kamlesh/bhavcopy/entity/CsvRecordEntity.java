package com.kamlesh.bhavcopy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "csv_records")
@NamedStoredProcedureQuery(
        name = "handle_query",
        procedureName = "handle_query",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "query_type", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "param1", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "param2", type = Double.class)
        },
        resultClasses = {String.class}
)
public class CsvRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "MARKET")
    private String market;

    @Column(name = "SERIES")
    private String series;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "SECURITY")
    private String security;

    @Column(name = "PREV_CL_PR")
    private double prevClosePrice;

    @Column(name = "OPEN_PRICE")
    private double openPrice;

    @Column(name = "HIGH_PRICE")
    private double highPrice;

    @Column(name = "LOW_PRICE")
    private double lowPrice;

    @Column(name = "CLOSE_PRICE")
    private double closePrice;

    @Column(name = "NET_TRDVAL")
    private double netTradedValue;

    @Column(name = "NET_TRDQTY")
    private double netTradedQuantity;

    @Column(name = "CORP_IND")
    private String corpInd;

    @Column(name = "HI_52_WK")
    private double high52Week;

    @Column(name = "LO_52_WK")
    private double low52Week;

}

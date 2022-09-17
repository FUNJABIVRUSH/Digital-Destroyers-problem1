package com.destroyers.spaceallocation.entities;

import com.destroyers.spaceallocation.model.space.LayoutQueryResponse;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;

@javax.persistence.SqlResultSetMapping(name = "LayoutQuery", classes = {
        @ConstructorResult(targetClass = LayoutQueryResponse.class,
                columns = {
                        @ColumnResult(name = "buildingName", type = String.class),
                        @ColumnResult(name = "floorId", type = Long.class),
                        @ColumnResult(name = "floorName", type = String.class),
                        @ColumnResult(name = "zoneId", type = Long.class),
                        @ColumnResult(name = "zoneName", type = String.class),
                        @ColumnResult(name = "seatId", type = Long.class),
                        @ColumnResult(name = "seatNumber", type = String.class),
                        @ColumnResult(name = "seatType", type = String.class)
                }
        )
})
@Entity
public class CustomQueryEntity {
    @Id
    private Long id;
}
